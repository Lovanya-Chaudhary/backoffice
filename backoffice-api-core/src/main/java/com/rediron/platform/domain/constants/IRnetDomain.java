package com.rediron.platform.domain.constants;

public interface IRnetDomain {
    	
	public static final String BASE_PATH = "/backoffice";

	//TODO - Make URL name in pure REST way (HATEOS)
	public static final String INVENTORY_API = "/v1/inventory";
	public static final String ITEMS = "/items";
	public static final String GET_ITEM_CREATE_DEFAULT_INFO = "/getitemcreatedefaultinfo";
	public static final String FIND_ITEM_DUPLICATES = "/finditemduplicates";
	public static final String CREATE_INVENTORY_ITEM = "/createItem";
	public static final String DEPT_GROUPS = "/deptGroups";
	public static final String GET_CLASSES = "/classes/{department_no}";
	public static final String GET_LINES = "/lines/{department_no}/{class_no}";
//	public static final String GET_SITE_GROUP = "/siteGroup";
	public static final String ITEM_TYPE_BY_CODE = "/item/{item_type_code}";
	
	public static final String SEARCH_SKU = "/search/{sku_no}/{site_no}";
	public static final String GET_ITEM_GLOBAL_INFO = "/search/global/{skuNumber}/{siteNumber}";
	public static final String GET_ITEM_KIT_INFO = "/search/kit/{skuNumber}";
	public static final String GET_ITEM_RESTRICTION_INFO = "/search/restriction/{skuNumber}/{siteNumber}";
	public static final String GET_ITEM_STATUS_INFO = "/search/status/{sku_no}/{site_no}";
	public static final String GET_ITEM_POS_INFO = "/search/pos/{skuNumber}/{siteNumber}";
	public static final String GET_ITEM_VENDOR_INFO = "/search/vendor/{skuNumber}/{siteGroup}/{vendorId}";
	public static final String GET_UM_CODES = "/umcodes";
	public static final String USER = "/user";

	public static final String PACK_UM_CODE_VALUES = "PACK UM CODES";
	public static final String AUTHENTICATION_API = "/v1/authenticate";
	public static final String AUTHENTICATE_URL = "/login";
	public static final String LOGOUT_URL = "/logout";
	public static final String USER_DOAMIN_NAME = "Retail.net";
	public static final  String CLIENT_DESCRIPTION = "springsecurity";

	public static final String GET_ITEM_CREATE_DEFAULTS = "/getitemcreatedefaults";
	public static final String GET_CUSTOMER_CATEGORY_CODES = "/getcustcategorycodes";
	public static final String GET_CUSTOMER_CATEGORY = "/getcustcategory";
	public static final String GET_CUSTOMER = "/getcustomer";
	public static final String CREATE_CUSTOMER = "/createcustomer";
	
	public static final String FIND_INVENTORY_ITEM = "/findinventory";
	public static final String FIND_ITEM = "/finditem";
	
	// changes start here
	
	// public static final String GET_SKU_INFO = "/skuInfo/{skuNumber}/{siteNumber}";

	public static final String GET_ITEM_NOTES = "/search/notes/{sku_no}/{site_no}";

	public static final String GET_ITEM_ATTR_VALUE = "/attribute/value/{sku_no}/{attrName}";

	public static final String GET_ITEM_ATTR_LIST = "/attribute/list/{sku_no}/{site_no}";

	public static final String GET_SITE = "/site";

	public static final String GET_ITEM_UPC = "/search/upc/type/{sku_no}";

	public static final String GET_ITEM_UPC_BY_SKU = "/search/upc/sku/{sku_no}";

	public static final String GET_ITEM_PRIMARY = "/primary/item/{siteNumber}/{itemId}";

	public static final String GET_MAIN_VEN_ITEM = "/main/vendor/item/{skuNumber}/{siteNumber}/{vendorId}";

	public static final String GET_PRIMARY_VEN_ITEM = "/primary/vendor/item/{skuNumber}/{siteNumber}";

	public static final String GET_PRIMARY_VEN_ITEM_FOR_SKU = "/primary/vendor/item/sku/{skuNumber}/{siteNumber}";

	public static final String GET_ITEM_IDENTIFIERS = "/item/identifier/{identifierType}/{skuNumber}/{siteNumber}/{vendorId}";

	public static final String SEARCH_VEN_ITEM = "/search/vendor/item/{skuNumber}/{siteGroup}";

	public static final String GET_VEN_OF_ITEMS = "/items/vendors/{siteNumber}/{skuNumber}";

	public static final String GET_ACTIVE_VEN_OF_ITEMS = "/items/active/vendors/{siteNumber}/{skuNumber}";

	public static final String GET_DEALS = "/search/deals/{siteNumber}/{seqNo}/{dealNo}";

	

	public static final String GET_COSTS = "/search/cost/{skuNumber}/{siteNumber}";

	public static final String FIND_DUPLICATES_ITEMS = "/duplicate/items";
	
	// cloned apis 

	public static final String SEARCH_SKUS = "/skus/{skuNumber}/{siteNumber}";
	
	public static final String GET_ITEM_GLOBAL_INFO_V1 = "/search/global/{skuNumber}/{siteNumber}";

	public static final String GET_ITEM_KIT_INFO_V1 = "v1/search/kit/{skuNumber}";

	public static final String GET_ITEM_RESTRICTION_INFO_V1 = "v1/search/restriction/{skuNumber}/{siteNumber}";

	public static final String GET_ITEM_POS_INFO_V1 = "v1/search/pos/{skuNumber}/{siteNumber}";

	public static final String DEPT_GROUPS_V1 = "/v1/deptGroups";
	
	public static final String DEPTARTMENTS = "/departments/{department_group_no}";

	public static final String ITEMS_V1 = "/v1/items";

	public static final String GET_CLASSES_V1 = "/v1/classes/{deptNo}";
	
	public static final String GET_LINES_V1 = "/v1/lines/{deptNo}/{classNo}";
	
	public static final String GET_CODE_DETAILS = "/codeDetails";
	
	// cloned apis end here
	
	public static final String GET_ALL_SITES = "/allSites";
	
	public static final String GET_SITES = "/all";
	
	public static final String GET_SITES_VISIBLE = "/visibleSites/{siteNumber}";
	
	public static final String GET_GROUP = "/search/sitegroup/{groupId}";
	
	public static final String GET_SUPPLIER_ITEM = "/supplier/{skuNumber}";
	
	public static final String GET_SITE_DETAILS = "/site/{siteNumber}";
	
	public static final String GET_ATTRIBUTES_LIST_BY_OWNER_ATTRIBUTE_IDS = "/attributes/{attribute_id}/{owner_id}";
	
	public static final String GET_VENDOR_BY_ID = "/search/vendor/{vendorId}";

	public static final String SEARCH_TENDER_CERTIFICATES = "/certificates";

	public static final String SEARCH_GL_CATEGORIES = "/glcategories";

	public static final String GET_COUNTRIES = "/countries";
	
	public static final String GET_STATES = "/state/{countryCd}";
	
	public static final String GET_ATTRIBUTE_VALUES = "/attributes/list/{attributeId}";
	
	public static final String GET_ITEM_CREATE_ATTRIBUTE_DEFAULTS = "/itemcreatedefaults/{itemCreationId}";
	
	public static final String ALL_DEPTARTMENTS = "/alldepartments";

	public static final String ATTRIBUTE = "/global/attribute";

	public static final String ATTRIBUTE1_ID = "ATTRIBUTE1_ID";
	
	public static final String VENDOR_IMPORTS = "/vendorimports/{skuNumber}/{siteNumber}";


	public static final String ITEM_NOTES_BY_SKU = "/itemnotes/{sku_no}";

	public static final String CREATE_ITEM_NOTE = "/createitemnote";
	
	public static final String BACK_OFFICE_HANDLING_VALUES = "ITEM NOTE BACK OFFICE HANDLING VALUES";
	public static final String POS_HANDLING_VALUES = "ITEM NOTE POS HANDLING VALUES";
	public static final String TYPE_CODE_VALUES = "ITEM NOTE TYPE CODE VALUES";

	public static final String TYPE_CODES_NOTES = "/itemnotestype";

	public static final String POS_HANDLING_CODES = "/poshandlingcodes";
	
	public static final String BACKOFFICE_HANDLING_CODES = "/backofficecodes";

	public static final String DELETE_ITEM_NOTE = "/deleteitemnote/{note_id}";

	public static final String UPDATE_ITEM_NOTE = "/updateitemnote/{note_id}";
	
	public static final String ATTR_LIST = "/attrlist";
	
	public static final String FUNC_AREA_CODE = "INV";
	
	public static final String ITEM_ATTR_LIST = "/attributeslist/{sku_no}";

	public static final String CREATE_ATTRIBUTE = "/createattribute";

	public static final String UPDATE_ATTRIBUTE = "/updateattribute";

	public static final String DELETE_ATTRIBUTE = "/deleteattribute";

	// upc CRUD
	
	public static String UPC = "UPC_STORED_LEN";
	public static String UPC_NUMERIC = "NUMERIC_UPC";
	public static String PLU = "MAX_PLU";
	public static final String ITEM_IDENTIFIER_CODES = "ITEM IDENTIFIER CODES";

	public static final String CREATE_UPC = "/createupc/{sku_no}";
	
	public static final String UPC_TYPE = "/upctype";

	public static final String DELETE_UPC = "/deleteupc/{sku_no}/{upc_id}/{modifier}";

	public static final String UPDATE_UPC = "/updateupc/{sku_no}";

	public static final String EMPLOYEES = "/employees";

	public static final String DOMAIN = "/domainsdescription";

	public static final String DOMAINCODES = "/domaincodes";

	public static final String UPDATE_INVENTORY = "/updateinventory/{sku_no}/{site_no}";
	
	public static final String INVBYSIT = "/invbysit/{sku_no}/{site_no}";
	
	public static final String UPDATE_INVBYSIT = "/updateinvbysit/{sku_no}/{site_no}";
	
	// changes end here	
	public static final String ITEM_TYPE_CD = "itemTypeCd";
	
	public static final String IS_UPC_ID = "isUpcId";
	
	public static final String LOOKUP_CD = "lookUpCd";
	
	public static final String SITE_NO = "siteNo";
	
	public static final String SKU_NO = "skuNo";
	
	public static final String CUST_CATEGORY_CD = "custCatCd";
	
	public static final String CUST_CD = "custCd";

	public static final String SWAGGER= "/swagger-ui.html";
	
	public static final String ITEM_TYPE_CD_NOT_FOUND = "Item Type Code Not Found";
		
	public static final String ITEM_NOT_CREATED = "Item was not created";
	
	// changes
	public static final String INVENTORY_EXCEPTION = "Missing parameters in the inventory model";
	
	public static final String CUSTOMER_NOT_CREATED = "Customer was not created";
	
	
	public static final String INVALID_UDTOKEN = "User not in valid application session";
	
	
	/*** Permissions ***/
	//APPLICATION CODES
	public static final String RNETUI_BACKOFFICE_APPS_APPLICATION_CD = "MMJAVA";
	
	//MODULE CODES
	public static final String INV_MAINT_MODULE_CD = "INV_MAINTENANCE";
	public static final String CUSTOMER_MASTER_MODULE_CD = "CUSTOMER_MASTER";
	public static final String PRICE_LIST_MODULE_CD = "PL_MAINTENANCE";

	//PERMISSION CODES
	public static final String INV_MAINT_GLOBAL_EDIT_PERM_CD = "INVMAINT.GLOBAL_EDIT";
	public static final String CUSTOMER_MASTER_FULL_PERM_CD = "CUSTOMER_MASTER.FULL";
	public static final String PRICE_LIST_FULL_PERM_CD = "PL_MAINTENANCE.EDIT";

	public static final String GET_ALL_SITE_GROUPS = "/allsitegroups";

}
