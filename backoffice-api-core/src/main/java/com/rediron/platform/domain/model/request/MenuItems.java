package com.rediron.platform.domain.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MenuItems {
	
	@JsonProperty("menus")
	private List<MenuItem> listOfMenu;

}
