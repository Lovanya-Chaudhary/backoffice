package com.rediron.platform.domain.todo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.core.Errors.ErrorInfo;
import com.rediron.platform.domain.entities.InvtoryEntity;
import com.rediron.platform.domain.model.request.AttributeModel;
import com.rediron.platform.domain.model.request.ChangeSiteInfoRequest;
import com.rediron.platform.domain.model.request.ConfigReqrdFieldModel;
import com.rediron.platform.domain.model.request.DescriptionModel;
import com.rediron.platform.domain.model.request.GeneralStatusFlagsInfoModel;
import com.rediron.platform.domain.model.request.InnerPackInfoModel;
import com.rediron.platform.domain.model.request.InvBySiteModel;
import com.rediron.platform.domain.model.request.InventoryModel;
import com.rediron.platform.domain.model.request.ItemCreationDefaultsInfoRequest;
import com.rediron.platform.domain.model.request.ItemNotesModel;
import com.rediron.platform.domain.model.request.ItemTypeFlagsInfoModel;
import com.rediron.platform.domain.model.request.ItemUpcModel;
import com.rediron.platform.domain.model.request.LabelInfoModel;
import com.rediron.platform.domain.model.request.MeasurementInfoModel;
import com.rediron.platform.domain.model.request.MenuItem;
import com.rediron.platform.domain.model.request.POSFlagsInfoModel;
import com.rediron.platform.domain.model.request.PricingModel;
import com.rediron.platform.domain.model.request.ProductModel;
import com.rediron.platform.domain.model.request.QuantityPricingModel;
import com.rediron.platform.domain.model.request.RegionalInfoModel;
import com.rediron.platform.domain.model.request.ReplenishmentInfoModel;
import com.rediron.platform.domain.model.request.ReportInfoModel;
import com.rediron.platform.domain.model.request.SerialItemsInfoModel;
import com.rediron.platform.domain.model.request.StatusInfoModel;
import com.rediron.platform.domain.model.request.SuggestedRetailInfoModel;
import com.rediron.platform.domain.model.request.SummaryInfoRequest;
import com.rediron.platform.domain.model.request.SummaryReorderPoints;
import com.rediron.platform.domain.model.request.UpdateInvbysitRequest;
import com.rediron.platform.domain.model.request.VendorItemFieldsToUpdate;
import com.rediron.platform.domain.model.request.VendorItemModel;
import com.rediron.platform.domain.model.request.VendorRequest;
import com.rediron.platform.domain.model.response.DomainRefCode;
import com.rediron.platform.domain.model.response.ItemCreationDefaultModel;
import com.rediron.platform.domain.response.SkuPromo;
import com.rediron.platform.domain.rnet.RnetDomainConnector;
import com.rediron.platform.domain.todo.PriceChangePojo;
import com.tomax.api.RNetIllegalArgumentException;

public class Tests {

	public static boolean isValidUPC(String code) {
		if (StringUtils.isBlank(code)) {
			throw new RNetIllegalArgumentException("Code can not be null");
		}

		String upcId = "4" + StringUtils.leftPad("60496", 11, '0');
		System.out.println("UPC ID ==== " + upcId);
		/*
		 * This will be 14 digits for an EAN, JAN, or UCC-13, 12 or 13 digits for a
		 * UPC-A, or 8 digits for a UPC-E (which is supplied without the check digit).
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
		System.out.println("calculateUPCCheckDigit(code) ========== " + calculateUPCCheckDigit(code));
		System.out.println("Integer.parseInt(checkDigit) ========== " + Integer.parseInt(checkDigit));
		return Integer.parseInt(checkDigit) == calculateUPCCheckDigit(code);
	}

	public static int calculateUPCCheckDigit(String code) {
		if (code.length() > 13) {
			return -1;
		} else if (code.length() != 7 && code.length() < 11)
			return -1;
		/*
		 * pad with zeros to lengthen to 13 digits to facilitate math against character
		 * array values
		 */
		switch (code.length()) {
		case 7:
			code = "00000" + code;
			break;
		case 11:
			code = "0" + code;
			break;
		case 12:
			code = "" + code;
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
//		a[12] = Integer.parseInt(code.substring(12, 13)) * 3;
		int sum = a[0] + a[1] + a[2] + a[3] + a[4] + a[5] + a[6] + a[7] + a[8] + a[9] + a[10] + a[11];// + a[12];
		System.out.println("SUM ======== " + sum);
		return (10 - (sum % 10)) % 10;
	}

	public static String convertUPCEToEAN(String code) {
		if (code != null && code.length() == 7) {
			return "0" + code.substring(0, 3) + code.substring(code.length() - 1, code.length()) + "0000"
					+ code.substring(3, code.length() - 1);
		} else
			return null;
	}

	private static void checkFactorOrNot(BigDecimal packQty, BigDecimal masterPackQty) {

		System.out.println("PackQty ==== " + packQty.intValue());
		System.out.println("Master PackQty ==== " + masterPackQty.intValue());
		System.out.println("Factor value ==== " + (masterPackQty.intValue() % packQty.intValue()));
//		System.out.println("Factor value ==== "+masterPackQty.toBigInteger().mod(packQty.toBigInteger()).signum());
		if (masterPackQty.intValue() % packQty.intValue() != 0)
			System.out.println("Throw exception and alert user to correct the values.");
		else
			System.out.println("Master pack are factors");

	}

	private static String fromDateToString(Date currentDate) {
		System.out.println(currentDate);
		return DateFormatUtils.format(currentDate, "MM/dd/yy");
	}

	public static void main(String[] args) {

		List<ItemUpcModel> upcModels = new ArrayList<>();
		ItemUpcModel m1 = new ItemUpcModel();
		m1.setTypeCd("U");
		m1.setUpcId("4567");
		m1.setIsPrimaryUPC(false);

		ItemUpcModel m2 = new ItemUpcModel();
		m2.setTypeCd("P");
		m2.setUpcId("4566");
		m2.setIsPrimaryUPC(false);

		ItemUpcModel m3 = new ItemUpcModel();
		m3.setTypeCd("P");
		m3.setUpcId("4565");
		m3.setIsPrimaryUPC(false);

		ItemUpcModel m4 = new ItemUpcModel();
		m4.setTypeCd("P");
		m4.setUpcId("4564");
		m4.setIsPrimaryUPC(false);

		ItemUpcModel m5 = new ItemUpcModel();
		m5.setTypeCd("E");
		m5.setUpcId("4563");
		m5.setIsPrimaryUPC(false);

		ItemUpcModel m6 = new ItemUpcModel();
		m6.setTypeCd("E");
		m6.setUpcId("4562");
		m6.setIsPrimaryUPC(false);

		ItemUpcModel m7 = new ItemUpcModel();
		m7.setTypeCd("C");
		m7.setUpcId("4561");
		m7.setIsPrimaryUPC(false);

		ItemUpcModel m8 = new ItemUpcModel();
		m8.setTypeCd("C");
		m8.setUpcId("4560");
		m8.setIsPrimaryUPC(false);

		upcModels.add(m1);
		upcModels.add(m2);
		upcModels.add(m3);
		upcModels.add(m4);
		upcModels.add(m5);
		upcModels.add(m6);
		upcModels.add(m7);
		upcModels.add(m8);

		checkItemUpcs(upcModels);
		
		List<String> upcIds = new ArrayList<>();
		
		upcModels.forEach(obj -> {
			if(obj.getUpcId() != null)
				upcIds.add(obj.getUpcId());
		});
		
		System.out.println(areUPCUnique(upcIds));

//		upcModels.forEach(obj -> {
//			if (obj.getTypeCd().equalsIgnoreCase("U") && obj.getIsPrimaryUPC()) {
//				System.out.println("UPC Id = " + obj.getUpcId());
//				System.out.println("Type code = " + obj.getTypeCd());
//				System.out.println("Is Primary = " + obj.getIsPrimaryUPC());
//			}
//		});

//		String upcId = "4" + StringUtils.leftPad("A96", 11, '0');
//		System.out.println("UPC ID is : = "+upcId+" with length : "+upcId.length());
//		if(!StringUtils.isNumeric(upcId))
//			System.out.println("length == "+"000000854751".length());

		// pQty1 == msrpQty in case epo val is entered
//		BigDecimal pQty1 = new BigDecimal(5);
//		
//		BigDecimal msrpQty = new BigDecimal(5);
//		BigDecimal msrpPrice = new BigDecimal(0);
//		BigDecimal epo = new BigDecimal(3);
//		
//		BigDecimal price1 = pQty1.multiply((msrpPrice.divide(msrpQty))
//				.subtract((msrpPrice.divide(msrpQty)).multiply(epo.divide(new BigDecimal(100)))));
//		
//		System.out.println("Price 1 ==== "+price1.setScale(2, BigDecimal.ROUND_HALF_UP));

//		BigDecimal packQty = new BigDecimal(5.25);
//		BigDecimal masterPackQty = new BigDecimal(10.26);
//		
//		System.out.println("Date in specific format === "+fromDateToString(new Date()));
//		
//		System.out.println("==============================================================");
//		
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date(System.currentTimeMillis()));
//		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
//		Date myDate1 = cal.getTime();
//		System.out.println("Date 1 ======== "+myDate1);
//		System.out.println("==============================================================");
//		
//		Calendar cal1 = Calendar.getInstance();
//		cal1.setTime(new Date(System.currentTimeMillis()));
//		cal1.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
//		Date myDate2 = cal1.getTime();
//		System.out.println("Date 2 ======== "+myDate2);
//		
//		if(myDate1.before(myDate2)) {
//			System.out.println("Date 1 appears before Date 2.");
//		}
//		if(myDate2.after(myDate1)) {
//			System.out.println("Date 2 appears after Date 1.");
//		}
//		if(myDate2.equals(myDate1)) {
//			System.out.println("Date 1 and Date 2 are same.");
//		}
//		checkFactorOrNot(packQty, masterPackQty);
//		
//		System.out.println("After fn call PackQty ==== "+packQty);
//		System.out.println("After fn call Master PackQty ==== "+masterPackQty);

//		List<String> packUMList = Arrays.asList(new String[] {"Tests1", "Tests2", "Tests3","Tests4", "Tests5"});
//		String um = "Tests";
//		if (StringUtils.isNotBlank(um)) {
//			if (!packUMList.isEmpty()) {
//				String packUM = um;
//				if (packUMList.stream().filter(obj -> StringUtils.isNotBlank(obj))
//						.anyMatch(o -> o.equalsIgnoreCase(packUM))) {
//					System.out.println("Exists in db");
//				} else {
//					System.out.println("Does not exist in db");
//				}
//			}
//		}

//		isValidUPC("2546987");

//		String seqNo = "abc12";
//		if(!StringUtils.isNotBlank(seqNo) || !StringUtils.isNumeric(seqNo))
//			System.out.println("checked");
//		else
//			System.out.println("false");
//		BigDecimal reorderTo = new BigDecimal(15);
//		System.out.println(reorderTo);
//
//		BigDecimal reorderPt = new BigDecimal(16.53645);
//		System.out.println(reorderPt.setScale(2, BigDecimal.ROUND_HALF_UP));
//		List<Integer> list = null;

//		if (!CollectionUtils.isEmpty(list))
//			System.out.println("Lovanya");
//		else {
//			list = new ArrayList<>();
//			list.add(5);
//			list.add(6);
//			list.add(10);
//			if(CollectionUtils.containsInstance(list, 5)) {
//				System.out.println("Contains value mentioned");
//			}else {
//				System.out.println("Does not contain value mentioned");
//			}
//		}
//			System.out.println("Chaudhary");
//		
//		if(reorderTo.compareTo(
//				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)) < 0
//				|| reorderTo.compareTo(reorderPt) < 0)
//			System.out.println("reorderTo == "+reorderTo+" and reorderPt == "+reorderPt);
//		
//		System.out.println("reorderTo.compareTo(reorderPt) =========== "+reorderTo.compareTo(reorderPt));
//		System.out.println(reorderTo.compareTo(
//				new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)));

//		String testDate = "06/17/20";
//		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
//		Date date = null;
//		try {
//			date = formatter.parse(testDate);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		System.out.println(date);

//		HashMap<Integer, String> hash_map = new HashMap<Integer, String>(); 
//		  
//        // Mapping string values to int keys 
//        hash_map.put(10, "Geeks"); 
//        hash_map.put(15, "4"); 
//        hash_map.put(20, "Geeks"); 
//        hash_map.put(25, "Welcomes"); 
//        hash_map.put(30, "You"); 
//  
//        // Displaying the HashMap 
//        System.out.println("Initial Mappings are: " + hash_map); 
//  
//        // Checking for the key_element '20' 
//        System.out.println("Is the key '20' present? " +  
//        hash_map.containsKey(20)); 

//		String name = null;
//		
//		if("Y".equalsIgnoreCase(name))
//			System.out.println("Checked");
//		else
//			System.out.println("Not checked");
//		String testDate = "19/12/2020";
//		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//		Date date = null;
//		try {
//			date = formatter.parse(testDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println("Using example ============= "+date);

//		BigDecimal price1 = new BigDecimal(5.25).setScale(2, RoundingMode.HALF_UP);
//		BigDecimal price2 = new BigDecimal(6.26).setScale(2, RoundingMode.HALF_UP);
//		System.out.println(price1.add(price2).setScale(2));
//		System.out.println("Double ========= "+(price1.doubleValue()+price2.doubleValue()));

//		final String SITES_INVBYSIT = "select distinct ibs.site_no, s.full_name from invbysit ibs, site s, sitgrp sg \r\n" + 
//				"where s.site_no = sg.site_no \r\n" + 
//				"and s.site_no = ibs.site_no \r\n" + 
//				"and ibs.sku_no = :skuNo order by 1";
//		
//		System.out.println(SITES_INVBYSIT);
//		String value = "545236987453";
//		System.out.println("LEN == "+value.length());

//		ObjectMapper mapper = new ObjectMapper();
//
//		// Java object to JSON file
//		try {
//			mapper.writeValue(new File("F:\\MenuItem.json"), new MenuItem());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// Java object to JSON string
//	    String jsonString = mapper.writeValueAsString(object);
//		String QUERY_AUTHENTICATION_DOMAIN = "select config_txt " + "from configrec "
//				+ "where config_name = 'IDM_AUTHENTICATION_DOMAIN' " + "and config_index = 0";
//		
//		System.out.println(QUERY_AUTHENTICATION_DOMAIN);
//		String qurey = "select supp.supplier_id, vend.name from SUPPLIER_ITEM supp join vendor vend on \r\n" + 
//				"vend.vendor_id = supp.supplier_id where sku_no = :skuNo";
//		System.out.println("Query ==>> "+qurey);

//		String permission = "select application from " + "APPLICATION" + " application";
//		
//		System.out.println("Permission ==>> "+permission);
//		
//		BigDecimal price1 = new BigDecimal(5);
//		BigDecimal price2 = new BigDecimal(15);
//		
//		if(price1.doubleValue() > price2.doubleValue())
//			System.out.println("price1");
//		else
//			System.out.println("price2");
//		
//		price2 = price1;
//		System.out.println("Price 1 in price 2 == "+price2);
//		List<Integer> list1 = null;//new ArrayList<Integer>();
//		list1.add(1);
//		list1.add(5);
//		List<Integer> list2 = null; //new ArrayList<Integer>();
//		list2.add(1);
//		list2.add(5);
//		List<Integer> list3 = null; //new ArrayList<Integer>();
//		list3.add(1);
//		list3.add(5);

//		System.out.println("Before");
//		List<Integer> reqList = Stream.of(list1, list2, list3)
//				.filter(obj -> obj != null)
//				.flatMap(Collection :: stream)
//				.collect(Collectors.toList());
//		System.out.println("After");
//		
//		reqList.stream().forEach(System.out :: println);

//		int pQty1 = 100;
//		BigDecimal msrpPrice = new BigDecimal(10);
//		int msrpQty = 2;
//		BigDecimal epo = new BigDecimal(1);
//		
//		BigDecimal price = (msrpPrice.divide(new BigDecimal(msrpQty)).multiply(epo.multiply(new BigDecimal(0.99)))).multiply(new BigDecimal(pQty1));
//		price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
//		System.out.println("Price 1 ==>> "+price);
//		
//		BigDecimal price1 = new BigDecimal(10);
//		BigDecimal price2 = new BigDecimal(3);
//		
//		if(price1.remainder(price2).compareTo(BigDecimal.ZERO) != 0)
//			System.out.println("Remainder is ==>> "+price1.remainder(price2).compareTo(BigDecimal.ZERO));
//		else
//			System.out.println("Else value ==>> "+price1.remainder(price2).compareTo(BigDecimal.ZERO));

//		String upcID = "400251562555875";
//		int length = upcID.length();
//		
//		if((length  > 5 && length < 9) || (length > 10 && length < 15))
//			System.out.println("Worked == "+length);
//		else
//			System.out.println("Not Worked == "+length);
//		BigDecimal value = new BigDecimal(25.33);
//		System.out.println(value);
//		System.out.println(value.intValue());
//		
//		value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
//		System.out.println(value);

//		String str = null;
//		System.out.println(""+str);
//		
//		Date utilDate = new Date();
//		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
//		
//		System.out.println("Date is ==>> "+new Date(System.currentTimeMillis()));
//		System.out.println("utilDate ==>> "+utilDate);
//		System.out.println("sqlDate ==>> "+sqlDate);
////		
//		utilDate = new Date(sqlDate.getTime());
//		System.out.println("utilDate from sqlDate ==>> "+utilDate);
//		
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(65);
//		list.add(75);
//		list.add(55);
//		list.add(45);
//		list.add(35);
//		list.add(53);
//		list.add(25);
//		list.add(51);
//		list.add(15);
//		
//		if(list.stream().filter(o -> o > 0).anyMatch(o -> o == 2))
//			System.out.println("Done");
//		else
//			System.out.println("Forget");

//		Date util = new Date(System.currentTimeMillis());
//		System.out.println("util date ==>> "+util);	
//		
//		long valueOfudtoken = 18000000L;
//		
//		System.out.println("UD token invalidate ==>> "+valueOfudtoken/(60*1000*60));
//		
//		
//		java.sql.Date sql = new java.sql.Date(System.currentTimeMillis());
//		System.out.println("sql date ==>> "+sql);
//		
//		System.out.println("===========================================================");
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date(System.currentTimeMillis()));
//		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+1);
//		Date myDate = cal.getTime();
//		System.out.println("new one === "+myDate);
//		
//		System.out.println("===========================================================");
//		
//		System.out.println("i am currently testing theg".length());
//		
//		String str = "i am currently testing theg".substring(0, 27);
//		System.out.println(str+"======== length is ======>> "+str.length());
//		
//		BigDecimal value = null;
//		System.out.println("bdc == "+value);

	}
	
	public static boolean areUPCUnique(List<String> list){
	    Set<String> set = new HashSet<>();
	    for (String t: list){
	        if (!set.add(t)) {
	        	System.out.println("UPC Id "+t+" is duplicate and not allowed.");
	        	return false;
	        }
	    }
	    return true;
	}
	
	public static <T> boolean areAllUnique(List<T> list){
	    Set<T> set = new HashSet<>();
	    for (T t: list){
	        if (!set.add(t))
	            return false;
	    }
	    return true;
	}

	private static void checkItemUpcs(List<ItemUpcModel> upcModels) {
		int upcPrimarycounter = 0;
		int pluPrimarycounter = 0;
		int eslPrimarycounter = 0;
		int uccPrimarycounter = 0;
		for (ItemUpcModel upc : upcModels) {
			if (upc.getTypeCd().equalsIgnoreCase("U") && upc.getIsPrimaryUPC()) {
				upcPrimarycounter++;
			}
			if (upc.getTypeCd().equalsIgnoreCase("P") && upc.getIsPrimaryUPC()) {
				pluPrimarycounter++;
			}
			if (upc.getTypeCd().equalsIgnoreCase("E") && upc.getIsPrimaryUPC()) {
				eslPrimarycounter++;
			}
			if (upc.getTypeCd().equalsIgnoreCase("C") && upc.getIsPrimaryUPC()) {
				uccPrimarycounter++;
			}
		}

		System.out.println("upcPrimarycounter value is : " + upcPrimarycounter);
		System.out.println("pluPrimarycounter value is : " + pluPrimarycounter);
		System.out.println("eslPrimarycounter value is : " + eslPrimarycounter);
		System.out.println("uccPrimarycounter value is : " + uccPrimarycounter);

		if (upcPrimarycounter == 0 || upcPrimarycounter > 1 || pluPrimarycounter > 0 || eslPrimarycounter > 0
				|| uccPrimarycounter > 0)
			System.out.println("Stop user and alert");
		else
			System.out.println("Continue");
	}

}
