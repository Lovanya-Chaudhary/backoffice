package com.rediron.platform.domain.report.constant;

public enum ExportType {
	PDF("Pdf"), XLS("Xls"), HTML("Html"), CSV("Csv");

	private final String value;

	ExportType(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static ExportType fromValue(String value) {
		ExportType[] values = ExportType.values();
		for (int i = 0; i < values.length; i++) {
			ExportType reportType = values[i];
			if (reportType.value().equals(value)) {
				return reportType;
			}
		}
		return null;
	}

}
