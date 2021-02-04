package com.rediron.platform.domain.services;

import com.rediron.platform.core.Errors;
import com.rediron.platform.core.ServiceException;
import com.rediron.platform.domain.constants.IErrorConstants;
import com.rediron.platform.domain.entities.CustomerCategoryCdEntity;
import com.rediron.platform.domain.models.CustomerCategoryModel;
import com.rediron.platform.domain.models.CustomerModel;
import com.rediron.platform.domain.repositories.CustomerCategoryCdRepository;
import com.tomax.api.RNetApiContexts;
import com.tomax.api.SortBy;
import com.tomax.api.UserIdentity;
import com.tomax.customer.api.CustomerAPI;
import com.tomax.customer.dto.CustomerCategoryDTO;
import com.tomax.customer.dto.CustomerDTO;
import com.tomax.customer.dto.CustomerFullDTO;
import com.tomax.customer.dto.custom.EGetCustomerCategoriesSortOptions;
import com.tomax.customerEmployeeHelper.CustomerEmployeeDomainHelperAPI;
import com.tomax.pricing.dto.PriceListAssignmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private Errors errors;

	@Autowired
	CustomerCategoryCdRepository custCatCdRepo;

	//TODO -Later we need to chipin JPA repository directly to each microservice instead of calling rnet-domain components  through Remote lookup

	private static CustomerAPI customerApi = RNetApiContexts.hq.getStateless(CustomerAPI.class);
	private static CustomerEmployeeDomainHelperAPI customerEmployeeApi = RNetApiContexts.hq.getStateless(CustomerEmployeeDomainHelperAPI.class);
	
	public List<CustomerCategoryModel> getCustomerCategoryCodes(UserIdentity domainUser)
			throws ServiceException
	{
		List<CustomerCategoryDTO> custCategoryCds = customerApi.getCustomerCategories(domainUser, 
				new SortBy<EGetCustomerCategoriesSortOptions>( EGetCustomerCategoriesSortOptions.DESCRIPTION ) );
		
		/*
		 * Comment the call above and uncomment the call below to use JPA instead of the EJB.
		 * Testing shows the EJB call is slightly faster after the first EJB call that sets up RMI.
		 */
		//List<CustomerCategoryCdEntity> custCategoryCds = Lists.newArrayList( custCatCdRepo.findAll() );
		
		
    	if( custCategoryCds == null || custCategoryCds.size() == 0 ){
			throw new ServiceException(errors.getErrors().get(IErrorConstants.NO_CUSTOMER_CATEGORY_FOUND));
    	}

    	List<CustomerCategoryModel> custCategoryModels = custCategoryCds.stream()
                .map( c -> new CustomerCategoryModel( c.getCustCategoryCd(), c.getDescription() ) )
                .collect( Collectors.toList() );
		
		return custCategoryModels;
	}
	
	/*
	 * This service call uses JPA. It does not use the EJB layer to return the Customer Category Code object.
	 */
	public CustomerCategoryModel getCustomerCategory( String categoryCdId )
			throws ServiceException
	{
		CustomerCategoryCdEntity custCategoryCd = custCatCdRepo.findByCustCategoryCd( categoryCdId );
		
		if( custCategoryCd != null ){
			CustomerCategoryModel custCatCdModel = new CustomerCategoryModel(custCategoryCd.getCustCategoryCd(), custCategoryCd.getDescription());
			return custCatCdModel;
		}
		else{
			throw new ServiceException(errors.getErrors().get(IErrorConstants.CATEGORY_CODE_NOT_FOUND).formatMessage(custCategoryCd));
		}
	}
	
	public String createCustomer(UserIdentity domainUser, CustomerModel customerModel ){
		CustomerFullDTO custFullDto = new CustomerFullDTO();
		custFullDto.setPrimarySiteNo( customerModel.getPrimarySiteNo() );
		custFullDto.setCustCategoryCd( customerModel.getCategoryCd() );
		custFullDto.setTypeCd( "REG" );
		custFullDto.setStatusCd( "A" );
		custFullDto.setName( customerModel.getName() );
		custFullDto.setFirstName( customerModel.getFirstName() );
		custFullDto.setMiddleName( customerModel.getMiddleName() );
		
		CustomerFullDTO cust = customerEmployeeApi.createCustomerFull(domainUser, custFullDto, new ArrayList<PriceListAssignmentDTO>());
		
		return cust.getCustomerId();
	}
	
	public CustomerModel getCustomer(UserIdentity domainUser, String custId ) throws ServiceException
	{		
		CustomerDTO custDTO = customerApi.getCustomer(domainUser, custId );
		if(custDTO != null && custDTO.getCustomerId() != null ) {
			CustomerModel cust = new CustomerModel();
			cust.setCustomerId( custDTO.getCustomerId() );
			cust.setCategoryCd( custDTO.getCustCategoryCd() );
			cust.setName( custDTO.getName() );
			cust.setFirstName( custDTO.getFirstName() );
			cust.setMiddleName( custDTO.getMiddleName() );
			cust.setOwnerId( custDTO.getOwnerId() );
			cust.setPrimarySiteNo( custDTO.getPrimarySiteNo() );
			return cust;
		}
		else{
			throw new ServiceException(errors.getErrors().get(IErrorConstants.CUST_NOT_FOUND).formatMessage(custId));
		}
	}
}
