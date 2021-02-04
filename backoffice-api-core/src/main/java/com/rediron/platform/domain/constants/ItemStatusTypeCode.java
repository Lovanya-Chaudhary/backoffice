package com.rediron.platform.domain.constants;

public enum ItemStatusTypeCode {
	
	ITEM("item"),
	RESTRICT("restrict"),
	POS("pos"),
	ITEMIZER("itemizer");
	
	private final String value;
	
	ItemStatusTypeCode(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
	
	public static ItemStatusTypeCode fromValue(String value) {
		ItemStatusTypeCode[] values = ItemStatusTypeCode.values();
		for (int i = 0; i < values.length; i++) {
			ItemStatusTypeCode itemStatus = values[i];
			if (itemStatus.value().equals(value)) {
				return itemStatus;
			}
		}
		return null;
	}
}
