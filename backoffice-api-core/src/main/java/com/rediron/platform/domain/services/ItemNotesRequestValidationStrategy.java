package com.rediron.platform.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.models.ItemNotesRequest;
import com.rediron.platform.util.validator.ValidationStrategy;

@Configuration
public class ItemNotesRequestValidationStrategy implements ValidationStrategy<ItemNotesRequest>{

	@Autowired
	private Errors errors;
	
	@Override
	public void validate(ItemNotesRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		if(request.getBackOfficeHandling() == null)
			throw new ServiceException(errors.getErrors().get("GENERIC_ERROR"));
		
	}

}
