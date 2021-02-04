package com.rediron.platform.domain.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.model.request.ItemCreationDefaultsInfoRequest;
import com.rediron.platform.util.validator.ValidationStrategy;

public class ItemCreationDefaultsInfoRequestValidationStrategy implements ValidationStrategy<ItemCreationDefaultsInfoRequest>{

	@Autowired
	private Errors errors;
	
	@Override
	public void validate(ItemCreationDefaultsInfoRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		if(request.getInventoryModel() == null)
			throw new ServiceException(errors.getErrors().get("INVTORY"));
		
		if(request.getInvBySiteModel() == null)
			throw new ServiceException(errors.getErrors().get("INVBYSIT"));
		
//		if(request.getItemUpcModel() == null)
//			throw new ServiceException(errors.getErrors().get("ITEM_UPC"));
		
		if(request.getInventoryModel() != null && request.getInventoryModel().getProductModel() == null)
			throw new ServiceException(errors.getErrors().get("DCL"));
		
		if(request.getInventoryModel() != null && request.getInventoryModel().getDescriptionModel() == null)
			throw new ServiceException(errors.getErrors().get("DESCRIPTION"));
		
		if(request.getInventoryModel() != null && request.getInventoryModel().getReportInfoModel() == null)
			throw new ServiceException(errors.getErrors().get("REPORT"));
	}

}
