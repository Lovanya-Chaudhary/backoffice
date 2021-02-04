package com.rediron.platform.domain.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rediron.platform.domain.RnetDomainApiApplication;
import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.platform.domain.model.request.InvBySiteModel;
import com.rediron.platform.domain.model.request.InventoryModel;
import com.rediron.platform.domain.model.request.ItemCreationDefaultsInfoRequest;
import com.rediron.platform.domain.model.request.ItemUpcModel;
import com.rediron.platform.domain.model.request.ProductModel;
import com.rediron.platform.domain.model.request.VendorItemModel;
import com.rediron.platform.domain.models.ItemAttributeValueModel;
import com.rediron.platform.domain.models.ItemLookUpModel;
import com.rediron.platform.domain.services.InventoryService;
import com.rediron.security.common.ISecurity;
import com.tomax.api.UserIdentity;
import com.tomax.inventory.dto.ItemCreationDefaultsDtlDTO;
import com.tomax.inventory.dto.ItemCreationDefaultsHdrDTO;
import com.tomax.inventory.dto.ItemCreationDefaultsHdrDTOId;
import com.tomax.inventory.dto.custom.InventorySearchResultsDTO;
import com.tomax.inventory.dto.custom.ItemCreationDefaultsInfoDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RnetDomainApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "testAPI")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RnetDomainAPIControllerInventoryTest {
	
	@Autowired
    private WebApplicationContext webApplicationContext;
		
    private MockMvc mockMvc;
    
    // will be used if security is turned on.
    private String udToken = "0-50E88BDF5CF5B582327115A85E35C9D94BE311C46A4FF310-1";
    
    // This is used to mock out the Authorization service if SpringSecurity is turned on for tests.
    // Mock AuthorizationService
    //@MockBean
    //AuthorizationService authServiceMock;
 	
    // Mock DomainInventoryService
	@MockBean
	InventoryService domainservice;

	@Before
    public void setupMockMvc() {
		
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(webApplicationContext)
        		.apply(springSecurity())
        		.build();
	}
		
	@Test
	public void test01_getItemCreationDefaults() throws Exception {
				
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD)).thenReturn(true);
		
		List<ItemCreationDefaultsHdrDTO> testList = buildTestItemCreationDefaultsHdrList();
		when(domainservice.getItemCreationDefaults(any(UserIdentity.class))).thenReturn(testList);	
			
		mockMvc.perform(MockMvcRequestBuilders.get(IRnetDomain.BASE_PATH + "/getitemcreatedefaults")
			.contentType(MediaType.APPLICATION_JSON).header(ISecurity.UD_TOKEN, udToken))
			//.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
			.andExpect(MockMvcResultMatchers.status().isOk()) 
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[0].itemTypeCd", equalTo("TEST1")))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[2].itemTypeCd", equalTo("TEST3")));
	}
		
	@Test
	public void test02_findItemDuplicates() throws Exception {
		
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD)).thenReturn(true);
		
		List<InventorySearchResultsDTO> testList = buildTestInventorySearchResultsList();		
		when(domainservice.findDuplicates( any(UserIdentity.class), eq(false), eq("1212"), eq(20))).thenReturn(testList);	
		
		mockMvc.perform(MockMvcRequestBuilders.get(IRnetDomain.BASE_PATH + "/finditemduplicates")
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param("lookUpCd", "1212")
				.param("isUpcId", "false")
				.param("siteNo", "20")) 
				//.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].mfgCd", equalTo("1212")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].itemDescription", equalTo("Testing Inventory Item")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].skuNo", nullValue()));
	}
	
	@Test
	public void test03_createItem() throws Exception {
		
		List<ItemAttributeValueModel> iaModelList = new ArrayList<ItemAttributeValueModel>();
		ItemAttributeValueModel itemAttrModel = new ItemAttributeValueModel();
		itemAttrModel.setAttributeId(500);
		itemAttrModel.setAttributeValue("Test 500");
		iaModelList.add(itemAttrModel);
		itemAttrModel = new ItemAttributeValueModel();
		itemAttrModel.setAttributeId(515);
		itemAttrModel.setAttributeValue("Test 515");
		iaModelList.add(itemAttrModel);
		itemAttrModel = new ItemAttributeValueModel();
		itemAttrModel.setAttributeId(513);
		itemAttrModel.setAttributeValue("Test 513");
		iaModelList.add(itemAttrModel);
		
		InventoryModel inv = new InventoryModel();
		inv.setItemDescription("Junit Test");
		ProductModel productModel = new ProductModel();
		
		productModel.setDeptNo(9999);
		productModel.setClassNo(999);
		productModel.setLineNo(99);
		inv.setProductModel(productModel );
//		inv.setDeptNo(9999);
//		inv.setClassNo(999);
//		inv.setLineNo(99);
		inv.setOwnerId(3837);
		ItemCreationDefaultsInfoRequest itemCreateModel = new ItemCreationDefaultsInfoRequest();
		itemCreateModel.setDeptGrpNo(0);
		itemCreateModel.setSiteGroup("3837");
		itemCreateModel.setVendorName("");
		itemCreateModel.setInventoryModel(inv);
		itemCreateModel.setInvBySiteModel(new InvBySiteModel());
//		itemCreateModel.setItemAttributeValueModelList(iaModelList);
		itemCreateModel.setItemUpcModel(new com.rediron.platform.domain.model.request.ItemUpcModel());
		itemCreateModel.setVendorItemModel(new VendorItemModel());
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(itemCreateModel);
		//System.out.println(jsonContent);
		
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD, "1")).thenReturn(true);
		
		when(domainservice.getItemCreationDefaultsInfo( any(UserIdentity.class), eq("BLAR"))).thenReturn( this.buildItemCreationDefaultsInfoDTO() );
	    when(domainservice.createItemFromDefaults( any(UserIdentity.class), any(ItemCreationDefaultsInfoDTO.class), any(ItemCreationDefaultsInfoRequest.class))).thenReturn(10007653);
		
		mockMvc.perform(MockMvcRequestBuilders.post(IRnetDomain.BASE_PATH + "/createitem")
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param("itemCreateDefaultTypeCode", "BLAR"))			
				//.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.notNullValue(String.class)))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.skuNo", equalTo(10007653)));
	}
		
	@Test
	public void test04_createItemBadDescriptionAndSiteGroup() throws Exception {
		
		InventoryModel inv = new InventoryModel();
		inv.setItemDescription("");
		ProductModel productModel = new ProductModel();
		
		productModel.setDeptNo(9999);
		productModel.setClassNo(999);
		productModel.setLineNo(99);
		inv.setProductModel(productModel );
//		inv.setDeptNo(9999);
//		inv.setClassNo(999);
//		inv.setLineNo(99);
		inv.setOwnerId(1);
		ItemCreationDefaultsInfoRequest itemCreateModel = new ItemCreationDefaultsInfoRequest();
		itemCreateModel.setDeptGrpNo(0);
		itemCreateModel.setSiteGroup("");
		itemCreateModel.setVendorName("");
		itemCreateModel.setInventoryModel(inv);
		itemCreateModel.setInvBySiteModel(new InvBySiteModel());
//		itemCreateModel.setItemAttributeValueModelList(new ArrayList<ItemAttributeValueModel>());
		itemCreateModel.setItemUpcModel(new com.rediron.platform.domain.model.request.ItemUpcModel());
		itemCreateModel.setVendorItemModel(new VendorItemModel());
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(itemCreateModel);
		//System.out.println(jsonContent);
		
		
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD, "1")).thenReturn(true);
		
		//No need to mock the domain service for this test because the validation will occur before the domain service is called.
		mockMvc.perform(MockMvcRequestBuilders.post(IRnetDomain.BASE_PATH + "/createitem")
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param("itemCreateDefaultTypeCode", "BLAR"))			
				//.andDo(MockMvcResultHandlers.print())  // <-- Uncomment this to see the Mock Result (great for debugging the test)
        		.andExpect(MockMvcResultMatchers.status().isBadRequest())
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors", hasSize(2)))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors[0].field", anyOf(equalTo("siteGroup"), equalTo("inventoryModel.description"))))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors[1].field", anyOf(equalTo("siteGroup"), equalTo("inventoryModel.description"))));
	}
	
	@Test
	public void test05_createItemBadDeptClassLine() throws Exception {
		
		InventoryModel inv = new InventoryModel();
		inv.setItemDescription("Test Description");
		ProductModel productModel = new ProductModel();
		
		productModel.setDeptNo(null);
		productModel.setClassNo(null);
		productModel.setLineNo(null);
		inv.setProductModel(productModel );
//		inv.setDeptNo(9999);
//		inv.setClassNo(999);
//		inv.setLineNo(99);
		inv.setOwnerId(1);
		ItemCreationDefaultsInfoRequest itemCreateModel = new ItemCreationDefaultsInfoRequest();
		itemCreateModel.setDeptGrpNo(0);
		itemCreateModel.setSiteGroup("3837");
		itemCreateModel.setVendorName("");
		itemCreateModel.setInventoryModel(inv);
		itemCreateModel.setInvBySiteModel(new InvBySiteModel());
//		itemCreateModel.setItemAttributeValueModelList(new ArrayList<ItemAttributeValueModel>());
		itemCreateModel.setItemUpcModel(new ItemUpcModel());
		itemCreateModel.setVendorItemModel(new VendorItemModel());
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(itemCreateModel);
		//System.out.println(jsonContent);
		
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD, "1")).thenReturn(true);

		//No need to mock the domain service for this test because the validation will occur before the domain service is called.
		mockMvc.perform(MockMvcRequestBuilders.post(IRnetDomain.BASE_PATH + "/createitem")
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param("itemCreateDefaultTypeCode", "BLAR"))			
				//.andDo(MockMvcResultHandlers.print())  // <-- Uncomment this to see the Mock Result (great for debugging the test)
        		.andExpect(MockMvcResultMatchers.status().isBadRequest())
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors", hasSize(3)))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors[0].field", anyOf(equalTo("inventoryModel.deptNo"), 
        																				  equalTo("inventoryModel.classNo"), 
        																				  equalTo("inventoryModel.lineNo"))))
        		
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors[1].field", anyOf(equalTo("inventoryModel.deptNo"), 
        				                                                                  equalTo("inventoryModel.classNo"), 
        				                                                                  equalTo("inventoryModel.lineNo"))))
        		
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors[2].field", anyOf(equalTo("inventoryModel.deptNo"), 
        				                                                                  equalTo("inventoryModel.classNo"), 
        				                                                                  equalTo("inventoryModel.lineNo"))));
	}
	
	@Test
	public void test06_createItemBadOwner() throws Exception {
		
		com.rediron.platform.domain.model.request.InventoryModel inv = new InventoryModel();
		inv.setItemDescription("Test Description");
		ProductModel productModel = new ProductModel();
		
		productModel.setDeptNo(9999);
		productModel.setClassNo(999);
		productModel.setLineNo(99);
		inv.setProductModel(productModel );
//		inv.setDeptNo(9999);
//		inv.setClassNo(999);
//		inv.setLineNo(99);
		ItemCreationDefaultsInfoRequest itemCreateModel = new ItemCreationDefaultsInfoRequest();
		itemCreateModel.setDeptGrpNo(0);
		itemCreateModel.setSiteGroup("3837");
		itemCreateModel.setVendorName("");
		itemCreateModel.setInventoryModel(inv);
		itemCreateModel.setInvBySiteModel(new InvBySiteModel());
//		itemCreateModel.setItemAttributeValueModelList(new ArrayList<ItemAttributeValueModel>());
		itemCreateModel.setItemUpcModel(new ItemUpcModel());
		itemCreateModel.setVendorItemModel(new VendorItemModel());
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(itemCreateModel);
		//System.out.println(jsonContent);
		
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD, "1")).thenReturn(true);

		//No need to mock the domain service for this test because the validation will occur before the domain service is called.
		mockMvc.perform(MockMvcRequestBuilders.post(IRnetDomain.BASE_PATH + "/createitem")
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param("itemCreateDefaultTypeCode", "BLAR"))			
				//.andDo(MockMvcResultHandlers.print())  // <-- Uncomment this to see the Mock Result (great for debugging the test)
        		.andExpect(MockMvcResultMatchers.status().isBadRequest())
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors", hasSize(1)))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors[0].field", equalTo("inventoryModel.ownerId")));
	}
	
	@Test
	public void test07_findInventory() throws Exception {
		
		ItemLookUpModel itmLukUp = new ItemLookUpModel();
		itmLukUp.setSiteNo(20);
		itmLukUp.setMfgCd("1212");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(itmLukUp);

		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD)).thenReturn(true);
		
		List<InventorySearchResultsDTO> testList = buildTestInventorySearchResultsList();		
		when(domainservice.findInventory(any(UserIdentity.class), eq(20), any(ItemLookUpModel.class))).thenReturn( testList );	
		
		mockMvc.perform(MockMvcRequestBuilders.post(IRnetDomain.BASE_PATH + IRnetDomain.FIND_INVENTORY_ITEM)
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)) 
				//.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].mfgCd", equalTo("1212")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].itemDescription", equalTo("Testing Inventory Item")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].skuNo", nullValue()));
	}

	@Test
	public void test08_findInventoryThrowItemNotFound() throws Exception {
		
		ItemLookUpModel itmLukUp = new ItemLookUpModel();
		itmLukUp.setSiteNo(20);
		itmLukUp.setMfgCd("1212");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(itmLukUp);

		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD)).thenReturn(true);
		
		// when(domainservice.findInventory(any(UserIdentity.class), eq(20), any(ItemLookUpModel.class))).thenThrow(new ItemNotFoundException("JUnit Testing Item not found. ItemNotFoundException"));
		
		mockMvc.perform(MockMvcRequestBuilders.post(IRnetDomain.BASE_PATH + IRnetDomain.FIND_INVENTORY_ITEM)
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)) 
				.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.message", containsString("Item not found")));
	}
	
	@Test
	public void test09_findInventorySiteNoNotset() throws Exception {
		
		ItemLookUpModel itmLukUp = new ItemLookUpModel();
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(itmLukUp);

		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD)).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.post(IRnetDomain.BASE_PATH + IRnetDomain.FIND_INVENTORY_ITEM)
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)) 
				//.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
        		.andExpect(MockMvcResultMatchers.status().isBadRequest())
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors", hasSize(1)))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors[0].field", equalTo("siteNo")));
	}

	private List<ItemCreationDefaultsHdrDTO> buildTestItemCreationDefaultsHdrList()
	{
		List<ItemCreationDefaultsHdrDTO> testList = new ArrayList<ItemCreationDefaultsHdrDTO>();
		ItemCreationDefaultsHdrDTO itemHdr = new ItemCreationDefaultsHdrDTO(new ItemCreationDefaultsHdrDTOId(1L));
		itemHdr.setItemTypeCd("TEST1");
		itemHdr.setDisplayLabel("TEST1 ITEM");
		itemHdr.setFunctionCd("I");
		itemHdr.setHintText("Unit Testing Item One");
		testList.add(itemHdr);
		
		itemHdr = new ItemCreationDefaultsHdrDTO(new ItemCreationDefaultsHdrDTOId(2L));
		itemHdr.setItemTypeCd("TEST2");
		itemHdr.setDisplayLabel("TEST2 ITEM");
		itemHdr.setFunctionCd("I");
		itemHdr.setHintText("Unit Testing Item Two");
		testList.add(itemHdr);
		
		itemHdr = new ItemCreationDefaultsHdrDTO(new ItemCreationDefaultsHdrDTOId(3L));
		itemHdr.setItemTypeCd("TEST3");
		itemHdr.setDisplayLabel("TEST3 ITEM");
		itemHdr.setFunctionCd("I");
		itemHdr.setHintText("Unit Testing Item Three");
		testList.add(itemHdr);
		return testList;
	}
	
	private List<InventorySearchResultsDTO> buildTestInventorySearchResultsList()
	{
		List<InventorySearchResultsDTO> testList = new ArrayList<InventorySearchResultsDTO>();
		InventorySearchResultsDTO searchResult = new InventorySearchResultsDTO();
		searchResult.setItemDescription("Testing Inventory Item");
		searchResult.setMfgCd("1212");
		searchResult.setDeptNo(9999);
		searchResult.setClassNo(999);
		searchResult.setLineNo(99);
		testList.add(searchResult);
		return testList;
	}
	
	private ItemCreationDefaultsInfoDTO buildItemCreationDefaultsInfoDTO()
	{
		ItemCreationDefaultsInfoDTO testDefaultInfo = new ItemCreationDefaultsInfoDTO(new ItemCreationDefaultsHdrDTO() ,new ArrayList<ItemCreationDefaultsDtlDTO>());
		return testDefaultInfo;
	}
}
