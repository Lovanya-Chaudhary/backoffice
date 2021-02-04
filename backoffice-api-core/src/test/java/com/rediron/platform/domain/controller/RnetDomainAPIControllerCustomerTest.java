package com.rediron.platform.domain.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
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
import com.rediron.platform.domain.constants.IPricingConstants;
import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.platform.domain.models.CustomerCategoryModel;
import com.rediron.platform.domain.models.CustomerModel;
import com.rediron.platform.domain.services.CustomerService;
import com.rediron.platform.domain.services.InventoryService;
import com.rediron.security.common.ISecurity;
import com.tomax.api.UserIdentity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest( classes=RnetDomainApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@ActiveProfiles( value = "testAPI" )
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class RnetDomainAPIControllerCustomerTest {
	
	@Autowired
    private WebApplicationContext webApplicationContext;
		
    private MockMvc mockMvc;
    
    // will be used if security is turned on.
    private String udToken = "0-50E88BDF5CF5B582327115A85E35C9D94BE311C46A4FF310-1";
    
    // This is used to mock out the Authorization service if SpringSecurity is turned on for tests.
    // Mock AuthorizationService
    //@MockBean
    //AuthorizationService authServiceMock;
    
    
    // Mock CustomerService
	@MockBean
	CustomerService customerService;
	
	@MockBean
	InventoryService invService;

	@Before
    public void setupMockMvc() {
		
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(webApplicationContext)
        		.apply(springSecurity())
        		.build();
	}
		
	@Test
	public void test01_getCustomerCategoryCodes() throws Exception {
				
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD)).thenReturn(true);
		
		List<CustomerCategoryModel> testList = buildTestCustomerCategoryCodeList();
		when(customerService.getCustomerCategoryCodes(any(UserIdentity.class))).thenReturn(testList);	
			
		mockMvc.perform(MockMvcRequestBuilders.get(IRnetDomain.BASE_PATH + "/getcustcategorycodes")
			.contentType(MediaType.APPLICATION_JSON).header(ISecurity.UD_TOKEN, udToken))
			.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
			.andExpect(MockMvcResultMatchers.status().isOk()) 
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[0].custCategoryCd", equalTo("2")))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[2].custCategoryCd", equalTo("AR")));
	}

	@Test
	public void test02_getCustomerCategory() throws Exception {
		
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD)).thenReturn(true);
		
		CustomerCategoryModel custCategoryModel = new CustomerCategoryModel( "2", "JUnit_Test" );		
		when(customerService.getCustomerCategory(eq("TEST"))).thenReturn(custCategoryModel);	
		
		mockMvc.perform(MockMvcRequestBuilders.get(IRnetDomain.BASE_PATH + "/getcustcategory")
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param(IRnetDomain.CUST_CATEGORY_CD, "TEST")) 
				//.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.custCategoryCd", equalTo("2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description", equalTo("JUnit_Test")));
	}
	
	@Test
	public void test04_getCustomer() throws Exception {
		
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD)).thenReturn(true);

		CustomerModel custModel = new CustomerModel();
		custModel.setCategoryCd("2");
		custModel.setName("JUnit_Test");
		custModel.setPrimarySiteNo(3837);
		
		when(customerService.getCustomer( any(UserIdentity.class), any(String.class))).thenReturn(null);	
		
		mockMvc.perform(MockMvcRequestBuilders.get(IRnetDomain.BASE_PATH + "/" + IRnetDomain.GET_CUSTOMER)
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param(IRnetDomain.CUST_CD, "299-180")) 
				.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.debugMessage", equalTo("No Customer Information found for 299-180")));
	}

	@Test
	public void test05_createCustomer() throws Exception {
		
		CustomerModel custModel = new CustomerModel();
		custModel.setCategoryCd("AR");
		custModel.setName("JUnit_Test");
		custModel.setPrimarySiteNo(3837);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(custModel);
		//System.out.println(jsonContent);
		
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD, "1")).thenReturn(true);
		
	    when(customerService.createCustomer(any(UserIdentity.class), any(CustomerModel.class))).thenReturn("1-990");
		
		mockMvc.perform(MockMvcRequestBuilders.post(IRnetDomain.BASE_PATH + "/" + IRnetDomain.CREATE_CUSTOMER)
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param(IPricingConstants.SITE_GROUP, "1"))			
				//.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.notNullValue(String.class)))
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.customerCode", equalTo("1-990")));
	}	
	
	@Test
	public void test06_createCustomerBadPrimarySiteNameCategoryCode() throws Exception {
		
		CustomerModel cust = new CustomerModel();
		cust.setName("");
		cust.setPrimarySiteNo(0);
		cust.setCategoryCd("");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(cust);
		//System.out.println(jsonContent);
			
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD, "1")).thenReturn(true);
		
		//No need to mock the domain service for this test because the validation will occur before the service is called.
		mockMvc.perform(MockMvcRequestBuilders.post(IRnetDomain.BASE_PATH + "/" + IRnetDomain.CREATE_CUSTOMER)
				.content(jsonContent)
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param("siteGrp", "1"))			
				//.andDo(MockMvcResultHandlers.print())  // <-- Uncomment this to see the Mock Result (great for debugging the test)
        		.andExpect(MockMvcResultMatchers.status().isBadRequest())
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors", hasSize(3)))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors[0].field", anyOf(equalTo("name"), 
						  equalTo("primarySiteNo"), 
						  equalTo("categoryCd"))))

        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors[1].field", anyOf(equalTo("name"), 
						  equalTo("primarySiteNo"), 
						  equalTo("categoryCd"))))

        		.andExpect(MockMvcResultMatchers.jsonPath("$.errorinfo.validationErrors[2].field", anyOf(equalTo("name"), 
						  equalTo("primarySiteNo"), 
						  equalTo("categoryCd"))));
	}
	
	private List<CustomerCategoryModel> buildTestCustomerCategoryCodeList()
	{
		List<CustomerCategoryModel> testList = new ArrayList<CustomerCategoryModel>();
		
		CustomerCategoryModel custCatCode = new CustomerCategoryModel("2", "Customer Category Cd 1" );
		testList.add(custCatCode);
		custCatCode = new CustomerCategoryModel("12", "Customer Category Cd 2" );
		testList.add(custCatCode);
		custCatCode = new CustomerCategoryModel("AR", "Customer Category Cd 3" );
		testList.add(custCatCode);
		
		return testList;
	}
}
