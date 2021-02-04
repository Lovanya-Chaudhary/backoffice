package com.rediron.platform.domain.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemNotes {
	
	@JsonProperty(value = "item_note")
	List<ItemNote> itemNotes;

}
