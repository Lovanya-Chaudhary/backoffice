package com.rediron.platform.domain.report.common;

import net.sf.jasperreports.engine.design.JRDesignField;

public class ReportField implements Comparable<ReportField> {

	private int order;
	private int width;
	private String name, displayName;
	private Class type;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
	}

	@Override
	public int compareTo(ReportField t) {
		return this.order - t.order;
	}

	public JRDesignField toJasperField() {
		JRDesignField re = new JRDesignField();
		re.setName(name);
		re.setValueClass(type);
		return re;
	}
}
