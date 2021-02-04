package com.rediron.platform.domain.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import com.rediron.platform.domain.RnetDomainApiApplication;
import com.rediron.platform.domain.constants.IPricingConstants;
import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.platform.domain.services.PriceListService;
import com.rediron.security.common.ISecurity;
import com.tomax.api.UserIdentity;
import com.tomax.pricing.dto.PriceListDtlLiteDTO;
import com.tomax.pricing.dto.custom.CustomPriceListDtlLiteDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest( classes=RnetDomainApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@ActiveProfiles( value = "testAPI" )
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class RnetDomainAPIControllerPriceListTest {
	
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
	PriceListService priceListService;

	@Before
    public void setupMockMvc() {
		
        mockMvc = MockMvcBuilders
        		.webAppContextSetup(webApplicationContext)
        		.apply(springSecurity())
        		.build();
	}
	
	@Test	
	public void test01_findPriceList() throws Exception {
		
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD)).thenReturn(true);
		
		List<CustomPriceListDtlLiteDTO> testList = buildTestCustomPriceListDtlLiteDTOList();
		
		when(priceListService.getPriceListDtls( any(UserIdentity.class), eq(1L), eq(1), eq(false), eq("FIXED"))).thenReturn(testList);
		
		mockMvc.perform(MockMvcRequestBuilders.get(IRnetDomain.BASE_PATH + "/findpricelist")
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param(IPricingConstants.PRICE_LIST_ID, "1")
				.param(IPricingConstants.PRICE_LIST_IS_INACTIVE, "false")
				.param(IPricingConstants.PRICE_LIST_TYPE, "FIXED")
				.param(IPricingConstants.SITE_GROUP, "1")) 
				.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].pricelistDtlLiteDTO.typeCd", equalTo("SL")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].pricelistDtlLiteDTO.externalRefId", equalTo("ALL_29942_R_ABR__071513___P")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].pricelistDtlLiteDTO.priceListDtlType", equalTo("FIXED")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].description", equalTo("JUnit Test Description")));
	}
	
	@Test
	public void test02_findPriceListError() throws Exception {
		
		//when(authServiceMock.canAccess(udToken,IRnetDomain.INV_MAINT_MODULE_CD,IRnetDomain.INV_MAINT_GLOBAL_EDIT_PERM_CD)).thenReturn(true);
		
		when(priceListService.getPriceListDtls( any(UserIdentity.class), any(Long.class),any(Integer.class),any(Boolean.class), any(String.class))).thenReturn(null);	
		
		mockMvc.perform(MockMvcRequestBuilders.get(IRnetDomain.BASE_PATH + "/findpricelist")
				.contentType(MediaType.APPLICATION_JSON)
				.header(ISecurity.UD_TOKEN, udToken)
				.param(IPricingConstants.PRICE_LIST_ID, "1000")
				.param(IPricingConstants.PRICE_LIST_IS_INACTIVE, "false")
				.param(IPricingConstants.PRICE_LIST_TYPE, "NOTE")
				.param(IPricingConstants.SITE_GROUP, "21"))
				.andDo(MockMvcResultHandlers.print()) // <-- Uncomment this to see the Mock Result (great for debugging the test
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	
	private List<CustomPriceListDtlLiteDTO> buildTestCustomPriceListDtlLiteDTOList() {
		
		List<CustomPriceListDtlLiteDTO> plList = new ArrayList<CustomPriceListDtlLiteDTO>();
		
		CustomPriceListDtlLiteDTO customPriceListDTO = new CustomPriceListDtlLiteDTO();
		
		PriceListDtlLiteDTO pricelistDtlLiteDTO = new PriceListDtlLiteDTO();
		
		customPriceListDTO.setDescription("JUnit Test Description");
		
		pricelistDtlLiteDTO.setPriceListId(1L);
		pricelistDtlLiteDTO.setSeqNo(1L);
		pricelistDtlLiteDTO.setTypeCd("SL");
		pricelistDtlLiteDTO.setPriceLevel(1);
		pricelistDtlLiteDTO.setPricePct(new BigDecimal(0.0));
		pricelistDtlLiteDTO.setDateCreated(new Date());
		pricelistDtlLiteDTO.setDateModified(new Date());
		pricelistDtlLiteDTO.setUserCreated("JUnit");
		pricelistDtlLiteDTO.setUserModified("JUnit");
		pricelistDtlLiteDTO.setForcePriceFl("N");
		pricelistDtlLiteDTO.setAttribute1Id(527);
		pricelistDtlLiteDTO.setAttribute1Value("ABR");
		pricelistDtlLiteDTO.setExternalRefId("ALL_29942_R_ABR__071513___P");
		pricelistDtlLiteDTO.setPartialItem("071513");
		pricelistDtlLiteDTO.setPartialItemType("MFG");
		pricelistDtlLiteDTO.setRebateRegistrationFl("N");
		pricelistDtlLiteDTO.setPriceListDtlType("FIXED");
		
		customPriceListDTO.setPricelistDtlLiteDTO(pricelistDtlLiteDTO);
		
		plList.add(customPriceListDTO);
		
		return plList;
	}
}
