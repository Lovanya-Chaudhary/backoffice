package com.rediron.platform.domain.report.common;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.SplitTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;

/**
 * Generic jasper report builder. The base template is specified in
 * config/report.jrxml. The report will generate automatically the column
 * headers and detail section based on the annotations present in the generic
 * type, as well as the columns in the custom subset, if present.
 *
 * @param <T>
 */
public class GenericReport<T> {
	private static Logger LOGGER = LoggerFactory.getLogger(GenericReport.class);

	private final JasperReport jasperReport;

	public GenericReport(Class<T> clazz) throws JRException {
		this(clazz, new HashSet<>());
	}

	public GenericReport(Class<T> clazz, Set<String> columns) throws JRException {
		// JasperDesign
		JasperDesign jasperDesign = JRXmlLoader
				.load(getClass().getClassLoader().getResourceAsStream("reports/general/report.jrxml"));

		Map<String, JRStyle> styles = jasperDesign.getStylesMap();
		// Parameters
		JRDesignParameter parameter = new JRDesignParameter();
		parameter.setName("ReportTitle");
		parameter.setValueClass(java.lang.String.class);
		jasperDesign.addParameter(parameter);

		List<Field> fields1 = new ArrayList<>();
		List<Method> methods1 = new ArrayList<>();
		Class<?> current = clazz;
		while (current != null && current != Object.class) { // we don't want to process Object.class
			// add fields to a collection
			fields1.addAll(Arrays.asList(current.getDeclaredFields()));
			methods1.addAll(Arrays.asList(current.getDeclaredMethods()));
			current = current.getSuperclass();
		}

		Set<ReportField> fields = new TreeSet<>();
		for (Field f : fields1) {
			ReportColumn rc = f.getAnnotation(ReportColumn.class);
			if (rc != null && (columns.isEmpty() || columns.contains(f.getName()))) {
				ReportField rf = new ReportField();
				rf.setName(f.getName());
				rf.setDisplayName(rc.displayName().equals("") ? rf.getName() : rc.displayName());
				rf.setType(f.getType());
				rf.setOrder(rc.columnOrder());
				rf.setWidth((int) ((double) jasperDesign.getPageWidth() * rc.width()));
				fields.add(rf);
				jasperDesign.addField(rf.toJasperField());

			}
		}

		for (Method f : methods1) {
			ReportColumn rc = f.getAnnotation(ReportColumn.class);
			if (rc != null) {
				try {
					String property = getPropertyName(f);
					if (columns.isEmpty() || columns.contains(property)) {
						ReportField rf = new ReportField();
						rf.setName(property);
						rf.setDisplayName(rc.displayName().equals("") ? rf.getName() : rc.displayName());
						rf.setType(f.getReturnType());
						rf.setOrder(rc.columnOrder());
						rf.setWidth((int) ((double) jasperDesign.getPageWidth() * rc.width()));
						fields.add(rf);
						jasperDesign.addField(rf.toJasperField());
					}
				} catch (ReflectiveOperationException e) {
					throw new JRException("Unable to process report annotations", e);
				}
			}
		}

		// Column header
		final JRDesignBand cband = (JRDesignBand) jasperDesign.getColumnHeader();
		cband.setHeight(20);
		int runningWidth = 0;
		for (ReportField rf : fields) {
			JRDesignStaticText staticText = new JRDesignStaticText();
			staticText.setX(runningWidth);
			staticText.setY(0);
			runningWidth += rf.getWidth();
			staticText.setWidth(rf.getWidth());
			staticText.setStretchType(StretchTypeEnum.RELATIVE_TO_BAND_HEIGHT);
			staticText.setHeight(20);
			staticText.setStyle(styles.get("TableHead"));
			staticText.setText(rf.getDisplayName());
			cband.addElement(staticText);
		}

		final JRDesignBand dband = new JRDesignBand();
		dband.setHeight(18);
		dband.setSplitType(SplitTypeEnum.STRETCH);
		runningWidth = 0;
		// Detail
		for (ReportField rf : fields) {
			JRDesignTextField ctextField = new JRDesignTextField();
			ctextField.setX(runningWidth);
			ctextField.setHeight(18);
			ctextField.setStretchType(StretchTypeEnum.RELATIVE_TO_BAND_HEIGHT);
			ctextField.setWidth(rf.getWidth());
			ctextField.setStyle(styles.get("Table"));
			JRDesignExpression cexpression = new JRDesignExpression();
			cexpression.setText("$F{" + rf.getName() + "}");
			ctextField.setExpression(cexpression);
			dband.addElement(ctextField);
			runningWidth += rf.getWidth();
		}
		((JRDesignSection) jasperDesign.getDetailSection()).addBand(dband);

		jasperReport = JasperCompileManager.compileReport(jasperDesign);
	}

	public void runReport(Collection<T> rows, Map<String, Object> parameters, Exporter exporter) throws JRException {
		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters,
				convertReportData(rows));
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.exportReport();
	}

	private static final String getPropertyName(Method getter) throws ReflectiveOperationException {
		if (getter.getName().startsWith("get")) {
			return Introspector.decapitalize(getter.getName().substring(3));
		}
		if (getter.getName().startsWith("is")) {
			return Introspector.decapitalize(getter.getName().substring(2));
		}
		throw new ReflectiveOperationException("Provided method [" + getter.toGenericString() + "] is not a getter");
	}
	
	public static JRDataSource convertReportData(Object value) throws IllegalArgumentException {
		if (value instanceof JRDataSource) {
			return (JRDataSource) value;
		}
		else if (value instanceof Collection) {
			return new JRBeanCollectionDataSource((Collection<?>) value);
		}
		else if (value instanceof Object[]) {
			return new JRBeanArrayDataSource((Object[]) value);
		}
		else {
			throw new IllegalArgumentException("Value [" + value + "] cannot be converted to a JRDataSource");
		}
	}
}
