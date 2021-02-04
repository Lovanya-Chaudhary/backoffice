package com.rediron.platform.domain.services;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rediron.platform.domain.util.JwtUtils;
import com.rediron.security.common.domain.ConfigrecEntity;
import com.rediron.security.common.repository.ConfigrecRepository;
import com.tomax.api.RNetApiContexts;
import com.tomax.api.UserIdentity;
import com.tomax.pricing.api.PricingAPI;
import com.tomax.pricing.dto.PricechgDTO;
import com.tomax.pricing.dto.custom.CustomPriceListDtlLiteDTO;
import com.tomax.pricing.dto.custom.PriceListSearchDTO;

@Service
public class PriceListService {

	@Autowired
	private ConfigrecRepository configrecRepository;
	
	private static PricingAPI pricingAPI = RNetApiContexts.hq.getStateless(PricingAPI.class);

	
	Logger logger = LoggerFactory.getLogger(PriceListService.class);
	
	private static String cRnetApiHost = null;
	private static long cRnetApiPort = 0;
	
	public List<CustomPriceListDtlLiteDTO> getPriceListDtls(UserIdentity domainUser, long priceListId, int siteGrp, boolean isInactive, String priceListType ){	
     	String jwtToken = null;
     	
		if( cRnetApiHost == null )
			{
				this.initialize();
			}
			
			jwtToken = JwtUtils.generateToken(domainUser);
			if ( logger.isDebugEnabled() ) {
				logger.debug( "getPriceListDtls jwt -> " + jwtToken );
			}

			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set( "Authorization", "Bearer "+jwtToken );

			PriceListSearchDTO searchDTO = new PriceListSearchDTO();
			searchDTO.setPriceListId(priceListId); // 1L
			searchDTO.setSearchingSiteNo(siteGrp); // 1
			searchDTO.setIgnoreInactivePriceLists(isInactive); // false
			searchDTO.setPriceListDtlType(priceListType.toUpperCase()); // FIXED

			HttpEntity<PriceListSearchDTO> requestEntity = new HttpEntity<>(searchDTO, headers);

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

			ResponseEntity<String> priceListResponse = restTemplate.exchange(
					"http://" + cRnetApiHost + ":" + cRnetApiPort + "/rnet/api/rest/pricing/findPriceListDtlLites",
					HttpMethod.POST,
					requestEntity, 
					String.class);

			if (logger.isDebugEnabled()) {
				logger.debug(priceListResponse.getBody());
			}

			List<CustomPriceListDtlLiteDTO> pricelist = new ArrayList<CustomPriceListDtlLiteDTO>();

			ObjectMapper objectMapper = new ObjectMapper();
			try {
				pricelist = objectMapper.readValue(priceListResponse.getBody(), new TypeReference<List<CustomPriceListDtlLiteDTO>>() {});
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Found " + pricelist.size() + " Price List DTO's");
				for (CustomPriceListDtlLiteDTO plDto : pricelist) {
					logger.debug("---------------------------------------");
					logger.debug("Description: " + plDto.getDescription());
					logger.debug(plDto.getPricelistDtlLiteDTO().toString());
				}
			}
			
			return pricelist;
	}
	
	private void initialize() {			
			ConfigrecEntity rnetApiHost = configrecRepository.findByConfigrecEntityIdConfigName("API_SERVICE_HOST");
			ConfigrecEntity rnetApiPort = configrecRepository.findByConfigrecEntityIdConfigName("API_SERVICE_PORT");
			
			if (logger.isDebugEnabled()) {
				logger.debug( rnetApiHost.getConfigTxt());
				logger.debug( Long.toString(rnetApiPort.getConfigNo().longValue()));
			}
			
			cRnetApiHost = rnetApiHost.getConfigTxt();
			cRnetApiPort = rnetApiPort.getConfigNo().longValue();
			
			if (logger.isDebugEnabled()) {
				logger.debug( cRnetApiHost );
				logger.debug( Long.toString( cRnetApiPort ) );
			}
	}
	
	// changes start here

	public PricechgDTO getPricechg(UserIdentity domainUser, long promoKeyNumber) {
		// TODO Auto-generated method stub
		return pricingAPI.getPricechg(domainUser, promoKeyNumber);
	}
}
