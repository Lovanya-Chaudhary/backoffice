package com.rediron.platform.domain.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.security.common.ISecurity;
import com.rediron.security.common.RestClient;
import com.rediron.security.common.ServerInfo;
import com.rediron.security.common.exceptions.ExternalSecurityException;
import com.rediron.security.common.exceptions.InvalidToken;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = IRnetDomain.BASE_PATH, method = RequestMethod.POST)
public class RNetApiSwaggerController {

	@Autowired
	ServerInfo serverInfo;


	//TODO - Need to be correct getHost() inapi-common components as of today it is adding schema to URL which leads double (http/https) appended to Swagger URL
	
	//This POST request is called to launch swagger with the udtoken.
	//This request is redirected to the GET request and the swagger page is sent in the response
	@RequestMapping(value = IRnetDomain.SWAGGER, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Swagger Rediredt",
	        notes = "Swagger Redirect")
	@ApiResponses(value = { 
			@ApiResponse(code = 401, message = ISecurity.INVALID_TOKEN),
	        @ApiResponse(code = 400, message = ISecurity.BAD_REQUEST),
	        @ApiResponse(code = 200, message = "") })
	public ResponseEntity<String> redirectToSwagger(
	        @ApiParam(value = "User Token", required = true) @RequestParam(value = ISecurity.UD_TOKEN ,required = false) String udtoken)
	        throws InvalidToken, ExternalSecurityException {
		
		String swaggerUrl=  serverInfo.getRelativeUrl() + IRnetDomain.SWAGGER;
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);

		RestClient restClient = new RestClient(requestFactory);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(swaggerUrl)
		        .queryParam(ISecurity.UD_TOKEN, udtoken);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restClient.exchange(
				builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
		return  response;
	}
	
}
