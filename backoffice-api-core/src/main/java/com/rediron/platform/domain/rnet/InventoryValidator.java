package com.rediron.platform.domain.rnet;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.rediron.platform.domain.entities.InvbysitEntity;
import com.rediron.platform.domain.entities.InvtoryEntity;
import com.rediron.platform.domain.entities.ItemupcEntity;
import com.rediron.security.common.domain.ConfigrecEntity;
import com.tomax.api.RNetIllegalArgumentException;
import com.tomax.api.RNetUnableToPerformException;
import com.tomax.commons.validator.RNetObjectValidator;

public class InventoryValidator {
	
	public static <T> void performBeanValidations(T t) {
		RNetObjectValidator.validate(t);
	}
	
	public static void validateInvbysitInsert(InvbysitEntity invbysitEntity) {
		validateOrManipulatePriceFieldsFromInvbysit(invbysitEntity);
		// performBeanValidations(invbysitEntity);
		validateInvbysitPriceLevels(invbysitEntity);
	}
	
	public static void validateItemUPCInsert(ItemupcEntity itemUPCEntity) {
		performBeanValidations(itemUPCEntity);
//		if (!isValidUPC(itemUPCEntity.getUpcId()))
//			throw new RNetIllegalArgumentException(itemUPCEntity.getUpcId() + " is an invalid UPC");
	}
	public static boolean isValidUPC(String code) {
		if (StringUtils.isBlank(code)) {
			throw new RNetIllegalArgumentException("Code can not be null");
		}
		/*
		 * This will be 14 digits for an EAN, JAN, or UCC-13, 12 or 13 digits
		 * for a UPC-A, or 8 digits for a UPC-E (which is supplied without the
		 * check digit).
		 */
		if (code.length() > 14) {
			return false;
		} else if (code.length() != 8 && code.length() < 12)
			return false;
		Pattern p = Pattern.compile("([0-9]*)");
		Matcher m = p.matcher(code);
		if (!m.matches()) {
			// can't contain non-numeric values
			return false;
		}
		// get the checkDigit
		String checkDigit = code.substring(code.length() - 1, code.length());
		// strip check digit from code
		code = code.substring(0, code.length() - 1);
		if (code.length() == 7) {
			code = convertUPCEToEAN(code);
		}
		System.out.println("calculateUPCCheckDigit(code) ========== "+calculateUPCCheckDigit(code));
		System.out.println("Integer.parseInt(checkDigit) ========== "+Integer.parseInt(checkDigit));
		return Integer.parseInt(checkDigit) == calculateUPCCheckDigit(code);
	}
	
	public static int calculateUPCCheckDigit(String code) {
		if (code.length() > 13) {
			return -1;
		} else if (code.length() != 7 && code.length() < 11)
			return -1;
		/* pad with zeros to lengthen to 13 digits to facilitate math against character array values */
		switch (code.length()) {
		case 7:
			code = "000000" + code;
			break;
		case 11:
			code = "00" + code;
			break;
		case 12:
			code = "0" + code;
			break;
		}
		// calculate check digit
		int[] a = new int[13];
		a[0] = Integer.parseInt(code.substring(0, 1)) * 3;
		a[1] = Integer.parseInt(code.substring(1, 2));
		a[2] = Integer.parseInt(code.substring(2, 3)) * 3;
		a[3] = Integer.parseInt(code.substring(3, 4));
		a[4] = Integer.parseInt(code.substring(4, 5)) * 3;
		a[5] = Integer.parseInt(code.substring(5, 6));
		a[6] = Integer.parseInt(code.substring(6, 7)) * 3;
		a[7] = Integer.parseInt(code.substring(7, 8));
		a[8] = Integer.parseInt(code.substring(8, 9)) * 3;
		a[9] = Integer.parseInt(code.substring(9, 10));
		a[10] = Integer.parseInt(code.substring(10, 11)) * 3;
		a[11] = Integer.parseInt(code.substring(11, 12));
		a[12] = Integer.parseInt(code.substring(12, 13)) * 3;
		int sum = a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + a[8] + a[9] + a[10] + a[11] + a[12];
		return (10 - (sum % 10)) % 10;
	}
	
	public static String convertUPCEToEAN(String code) {
		if (code != null && code.length() == 7) {
			return "0" + code.substring(0, 3) + code.substring(code.length() - 1, code.length()) + "0000"
					+ code.substring(3, code.length() - 1);
		} else
			return null;
	}
	
	public static void validateInvbysit(InvbysitEntity invbysitEntity) {
		validateOrManipulatePriceFieldsFromInvbysit(invbysitEntity);
		performBeanValidations(invbysitEntity);
		validateInvbysitPriceLevels(invbysitEntity);
	}
	
	public static void validateInvtory(InvtoryEntity invtoryEntity) {
		if (invtoryEntity.getOwnerId() == null) {
			invtoryEntity.setOwnerId(1);
		}
		if (StringUtils.isBlank(invtoryEntity.getReceiptDesc())
				&& !StringUtils.isBlank(invtoryEntity.getDescription())) {
			invtoryEntity.setReceiptDesc(invtoryEntity.getDescription());
			if (invtoryEntity.getReceiptDesc().length() > 26) {
				invtoryEntity.setReceiptDesc(invtoryEntity.getReceiptDesc().substring(0, 26));
			}
		}

		performBeanValidations(invtoryEntity);
		
		// Validating Item Hierarchy
		if (invtoryEntity.getDeptNo() == null || invtoryEntity.getClassNo() == null || 
				invtoryEntity.getLineNo() == null){
			if (invtoryEntity.getDeptNo() == null)
				throw new RNetUnableToPerformException("Depertment number is required");
			if (invtoryEntity.getClassNo() == null)
				throw new RNetUnableToPerformException("Class number is required");
			if (invtoryEntity.getLineNo() == null)
				throw new RNetUnableToPerformException("Line number is required");
		}

//		if (invtoryEntity.getAttribute1Id() != null) {
//			AttributeValidator.doAttributeValidation(em, invtoryEntity.getAttribute1Id(),
//					invtoryEntity.getAttribute1Value());
//		}
	}
	
	private static void validateOrManipulatePriceFieldsFromInvbysit(InvbysitEntity invbysitEntity) {
		if (invbysitEntity.getUnitPrice1() != null) {
			if (invbysitEntity.getPrice1() != null) {
				if (!invbysitEntity.getUnitPrice1().equals(invbysitEntity.getPrice1())) {
					if (invbysitEntity.getPriceQty1() == null) {
						throw new RNetIllegalArgumentException("UnitPrice1 and Price1 don't match.");
					} else {
						if (invbysitEntity.getPriceQty1() == null || invbysitEntity.getPriceQty1() == 0) {
							invbysitEntity.setPriceQty1(1);
						}
						if (invbysitEntity.getPrice1()
								.compareTo(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)) > 0) {
							// price1 is populated, so setUnitPrice
							BigDecimal calculatedUnitPrice = invbysitEntity.getPrice1()
									.divide(new BigDecimal(invbysitEntity.getPriceQty1()))
									.setScale(2, BigDecimal.ROUND_HALF_UP);
							if (invbysitEntity.getUnitPrice1()
									.compareTo(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)) > 0
									&& !invbysitEntity.getUnitPrice1().equals(calculatedUnitPrice)) {
								throw new RNetIllegalArgumentException("UnitPrice1 and Price1 don't match.");
							}
							invbysitEntity.setUnitPrice1(calculatedUnitPrice);
						} else {
							// unitPrice1 is populated, so setPrice1
							invbysitEntity.setPrice1(invbysitEntity.getUnitPrice1());
							invbysitEntity.setPriceQty1(1);
						}
					}
				}
			} else {
				invbysitEntity.setPrice1(invbysitEntity.getUnitPrice1());
				invbysitEntity.setPriceQty1(1);
			}
		} else {
			// unitPrice1 is null, so go off price1
			if (invbysitEntity.getPrice1() != null) {
				if (invbysitEntity.getUnitPrice1() != null) {
					if (!invbysitEntity.getUnitPrice1().equals(invbysitEntity.getPrice1())) {
						if (invbysitEntity.getPriceQty1() == null) {
							throw new RNetIllegalArgumentException("UnitPrice1 and Price1 don't match.");
						} else {
							if (invbysitEntity.getPriceQty1() == null || invbysitEntity.getPriceQty1() == 0) {
								invbysitEntity.setPriceQty1(1);
							}
							BigDecimal calculatedUnitPrice = invbysitEntity.getPrice1()
									.divide(new BigDecimal(invbysitEntity.getPriceQty1()))
									.setScale(2, BigDecimal.ROUND_HALF_UP);
							if (!invbysitEntity.getUnitPrice1().equals(calculatedUnitPrice)) {
								throw new RNetIllegalArgumentException("UnitPrice1 and Price1 don't match.");
							}
						}
					}
				} else {
					invbysitEntity.setUnitPrice1(invbysitEntity.getPrice1());
				}
			}
		}
	}
	
	private static void validateInvbysitPriceLevels(InvbysitEntity invbysitEntity) {
		if ((invbysitEntity.getPrice2() != null && invbysitEntity.getPrice2().signum() != 0)
				|| (invbysitEntity.getPrice3() != null && invbysitEntity.getPrice3().signum() != 0)
				|| (invbysitEntity.getPrice4() != null && invbysitEntity.getPrice4().signum() != 0)
				|| (invbysitEntity.getPrice5() != null && invbysitEntity.getPrice5().signum() != 0)
				|| (invbysitEntity.getPrice6() != null && invbysitEntity.getPrice6().signum() != 0)
				|| (invbysitEntity.getPrice7() != null && invbysitEntity.getPrice7().signum() != 0)
				|| (invbysitEntity.getPrice8() != null && invbysitEntity.getPrice8().signum() != 0)
				|| (invbysitEntity.getPrice9() != null && invbysitEntity.getPrice9().signum() != 0)
				|| (invbysitEntity.getPrice10() != null && invbysitEntity.getPrice10().signum() != 0)) {
			if (invbysitEntity.getPrice1() == null || invbysitEntity.getPrice1().signum() == 0) {
				throw new RNetIllegalArgumentException(
						"If any INVBYSIT Price Levels 2-10 are non-zero, then Price Level 1 must be non-zero as well!");
			}
		}
	}
	
	public static void validateUPCID(ItemupcEntity itemUPCEntity, List<ConfigrecEntity> configrecEntityList) {
		performBeanValidations(itemUPCEntity);
		
		BigDecimal defaultMaxIdLengthPLU = BigDecimal.ZERO;
		Boolean isNumericUPC = false;

		if (CollectionUtils.isEmpty(configrecEntityList)) {
			throw new RNetUnableToPerformException("ConfigrecEntity is Empty.");
		} else {
			for (ConfigrecEntity el : configrecEntityList) {
				if ("UPC_STORED_LEN".equals(el.getConfigrecEntityId().getConfigName())) {
					defaultMaxIdLengthPLU = el.getConfigNo();
				} else if ("NUMERIC_UPC".equals(el.getConfigrecEntityId().getConfigName())){
					if ("Y".equals(el.getConfigFl())){
						isNumericUPC = true;
					}
				}
			}
		}

		String idValue = itemUPCEntity.getUpcId();
		String error = "UPC Id should be Numeric. ";

		// It the id have to be numeric then we will use this pattern
		if (itemUPCEntity.getUpcId().length() > defaultMaxIdLengthPLU.intValue() ) {
			throw new RNetUnableToPerformException("Unable to create UPC. Maximun Length for the ID is " + 
		                                                                            defaultMaxIdLengthPLU.intValue());
		}

		// if id is not numeric then throw the error
		if (StringUtils.isNotEmpty(idValue) && isNumericUPC && !StringUtils.isNumeric(idValue)) {
			throw new RNetUnableToPerformException("Unable to create UPC. Invalid format." + error);
		}
		
		if(itemUPCEntity.getUcc14Qty() != null){
			throw new RNetUnableToPerformException("UCC14 Qty. should be empty for type UPC");
		}
	}
	
	public static ItemupcEntity validateUPCIDByType(ItemupcEntity itemUPCEntity,
			List<ConfigrecEntity> configrecEntityList) {
		performBeanValidations(itemUPCEntity);
		// Will be change by configrecEntityList
		BigDecimal defaultMinIdLengthUPC = BigDecimal.ZERO;
		BigDecimal defaultMaxIdLengthPLU = BigDecimal.ZERO;
		Boolean isNumericUPC = false;

		if (CollectionUtils.isEmpty(configrecEntityList)) {
			throw new RNetUnableToPerformException("ConfigrecEntity is Empty.");
		} else {
			for (ConfigrecEntity el : configrecEntityList) {
				if ("UPC_STORED_LEN".equals(el.getConfigrecEntityId().getConfigName())) {
					defaultMinIdLengthUPC = el.getConfigNo();
				} else if ("MAX_PLU".equals(el.getConfigrecEntityId().getConfigName())) {
					defaultMaxIdLengthPLU = el.getConfigNo();
				} else if ("NUMERIC_UPC".equals(el.getConfigrecEntityId().getConfigName())) {
					if ("Y".equals(el.getConfigFl())){
						isNumericUPC = true;
					}
				}
			}
		}

		String idValue = itemUPCEntity.getUpcId();
		String error = "UPC Id should be Numeric. ";

		// It the id have to be numeric then we will use this pattern
		if (itemUPCEntity.getUpcId().length() > 18) {
			throw new RNetUnableToPerformException("Unable to create UPC. Maximun Length for the ID is 18. ");
		}

		if ("U".equals(itemUPCEntity.getTypeCd())) {

			// if id is not numeric then throw the error
			if (StringUtils.isNotEmpty(idValue) && isNumericUPC && !StringUtils.isNumeric(idValue)) {
				throw new RNetUnableToPerformException("Unable to create UPC. Invalid format." + error);
			}
			
			if(itemUPCEntity.getUcc14Qty() != null){
				throw new RNetUnableToPerformException("UCC14 Qty. should be empty for type UPC");
			}

			// checking for length, and add necessary zero
			if (defaultMinIdLengthUPC != null && (defaultMinIdLengthUPC.intValue() != 0) && (idValue.length() < defaultMinIdLengthUPC.intValue())) {
				String newUpcId = StringUtils.leftPad(idValue, defaultMinIdLengthUPC.intValue(), "0");

				itemUPCEntity.setUpcId(newUpcId);
				return itemUPCEntity;
			} else {
				return itemUPCEntity;
			}
		}

		else if ("C".equals(itemUPCEntity.getTypeCd())) {

			// if id is not numeric then throw the error
			if (StringUtils.isNotEmpty(idValue) && isNumericUPC && !StringUtils.isNumeric(idValue)) {
				throw new RNetUnableToPerformException("Unable to create UPC. Invalid format." + error);
			}
			
			if(itemUPCEntity.getUcc14Qty() == null){
				throw new RNetUnableToPerformException("Unable to create UPC. UCC14 Qty is required to create UCC14 type ID.");
			}

			return itemUPCEntity;
		} else if ("E".equals(itemUPCEntity.getTypeCd())) {

			// if id is not numeric then throw the error
			if (StringUtils.isNotEmpty(idValue)  && isNumericUPC && !StringUtils.isNumeric(idValue)) {
				throw new RNetUnableToPerformException("Unable to create UPC. Invalid format." + error);
			}
			
			if(itemUPCEntity.getUcc14Qty() != null){
				throw new RNetUnableToPerformException("UCC14 Qty. should be empty for type ESL.");
			}

			return itemUPCEntity;

		} else if ("P".equals(itemUPCEntity.getTypeCd())) {

			if (defaultMaxIdLengthPLU != null && defaultMaxIdLengthPLU.intValue() != 0) {
				// checking for length, and add necessary zero
				if (idValue.length() > defaultMaxIdLengthPLU.intValue()) {
					throw new RNetUnableToPerformException("Unable to create UPC. Maximun Length for the ID is "
							+ defaultMaxIdLengthPLU.intValue() + ". ");
				}
			}
			
			if(itemUPCEntity.getUcc14Qty() != null){
				throw new RNetUnableToPerformException("UCC14 Qty. should be empty for type PLU.");
			}

			return itemUPCEntity;
		} else {
			return itemUPCEntity;
		}
	}
}
