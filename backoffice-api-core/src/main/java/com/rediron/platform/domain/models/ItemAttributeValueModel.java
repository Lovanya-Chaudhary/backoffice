package com.rediron.platform.domain.models;

import lombok.Data;

@Data
public class ItemAttributeValueModel {
	
		private Integer attributeId;
		private String attributeValue;
		private String functionalAreaCd;
		private String operationType;

}
