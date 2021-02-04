package com.rediron.platform.domain.todo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;

// In order to directly use record type out param we can ref JOOQ 3.9.0 
//import org.jooq.UDT;
//import org.jooq.impl.UDTRecordImpl;

import lombok.Data;
import lombok.NoArgsConstructor;

// For testing only
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdhdrModel{

	private String ACCT_TYPE;

	private String AD_ID;
	
	private BigDecimal AD_TYPE;

	private String DESCRIPTION;

	private String EXTERNAL_REF_ID;

	private Date EXTRACT_DT;

	private String GROUP_ID;

	private Date ORIGINAL_STOP_DT;

	private String PROMO_RECEIPT_DESC;

	private String RECEIPT_DESCRIPTION;

	private String RELEASE_CODE_FL;

	private int SITE_NO;

	private String SOURCE;

	private Date START_DT;

	private BigDecimal START_TIME;

	private String STATUS;

	private Date STOP_DT;

	private BigDecimal STOP_TIME;

	private BigDecimal WORKING_COUNTER;

	private String OP_CODE;

	private String EXTERNAL_REF2_ID;
	
	private String COL_MAP;
}
