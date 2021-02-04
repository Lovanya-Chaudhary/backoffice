package com.rediron.platform.domain.config;

import com.rediron.platform.domain.constants.IRnetDomain;
import com.rediron.platform.domain.constants.ISalesConstants;
import com.rediron.platform.domain.constants.ISkuPkgConstants;
import com.rediron.platform.domain.constants.ITmxGblConstants;
import com.rediron.platform.domain.services.ItemNotesRequestValidationStrategy;
import com.rediron.platform.domain.constants.ISecurityConstants;
import com.rediron.platform.core.Errors;
import com.rediron.platform.domain.constants.IDomainConstants;
import com.rediron.platform.domain.constants.ILOVConstants;
import com.rediron.platform.domain.constants.IPricingConstants;
import com.rediron.platform.domain.constants.IQueryConstants;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RnetApiConfiguration {

	@Autowired DataSource datasource;
	
//	@Bean
//	public Errors errors() {
//		return new Errors();
//	}
	
//	@Bean
//	public ItemNotesRequestValidationStrategy itemNotesValidationStrategy() {
//		return new ItemNotesRequestValidationStrategy();
//	}
	
	@Bean
	public IRnetDomain iRnetDomain() {
		return new IRnetDomain() {};
	}
	
	@Bean
	public ISalesConstants iSalesConstants() {
		return new ISalesConstants() {};
	}
	
	@Bean
	public IPricingConstants iPricingConstants() {
		return new IPricingConstants() {};
	}
	
	@Bean
	public ITmxGblConstants iTmxGblConstants() {
		return new ITmxGblConstants() {};
	}
	
	@Bean
	public IQueryConstants iQueryConstants() {
		return new IQueryConstants() {};
	}
	
	@Bean
	public IDomainConstants iDomainConstants() {
		return new IDomainConstants() {};
	}	
	
	@Bean
	public ISecurityConstants iSecurityConstants() {
		return new ISecurityConstants() {};
	}	
	
	@Bean
	public ILOVConstants iLOVConstants() {
		return new ILOVConstants() {};
	}	
	
	@Bean
	public ISkuPkgConstants iSkuPkgConstants() {
		return new ISkuPkgConstants() {};
	}	
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(datasource) {};
	}
}
