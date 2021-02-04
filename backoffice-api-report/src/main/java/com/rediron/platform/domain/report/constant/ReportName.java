package com.rediron.platform.domain.report.constant;

public enum ReportName {
	CUST_COUNT_ALL_SUB_REPORT("CustCountALLSubReport"), CASH_ON_HAND("CashOnHandReport"),
	CASH_DRAWER_CONTENTS("CashDrawerContents"), CASH_MANAGEMENT_TRANSACTIONS("CashManagementTransactions"),
	PULL_SHEET("PullSheetReport"), PURCHASE_ORDER("PurchaseOrder");

	private final String value;

	ReportName(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static ReportName fromValue(String value) {
		ReportName[] values = ReportName.values();
		for (int i = 0; i < values.length; i++) {
			ReportName reportName = values[i];
			if (reportName.value().equals(value)) {
				return reportName;
			}
		}
		return null;
	}

}
