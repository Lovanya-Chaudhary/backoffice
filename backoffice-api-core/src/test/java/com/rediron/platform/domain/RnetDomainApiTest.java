package com.rediron.platform.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rediron.platform.domain.api.InventoryAPIController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles( value = "testAPI" )
public class RnetDomainApiTest {
	
	@Autowired
	private InventoryAPIController inventoryAPIController;
	 
	@Test
	public void contextLoads() {
		assertThat(inventoryAPIController).isNotNull();
	}
}
