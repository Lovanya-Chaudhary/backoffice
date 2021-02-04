package com.rediron.platform.domain.constants;

public enum UserDefinedTypeCode {
	
	GALLON("gallon"),
	STOCK("stock"),
	SHELF("shelf");
	
	private final String value;
	
	UserDefinedTypeCode(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
	
	public static UserDefinedTypeCode fromValue(String value) {
		UserDefinedTypeCode[] values = UserDefinedTypeCode.values();
		for (int i = 0; i < values.length; i++) {
			UserDefinedTypeCode itemStatus = values[i];
			if (itemStatus.value().equals(value)) {
				return itemStatus;
			}
		}
		return null;
	}
}
