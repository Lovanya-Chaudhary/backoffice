package com.rediron.platform.domain.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.entities.DomainRefEntity;
import com.rediron.platform.domain.model.response.DomainRef;
import com.rediron.platform.domain.model.response.DomainRefCode;
import com.rediron.platform.domain.repositories.DomainRefRepository;
import com.rediron.platform.domain.util.MapToResponseUtil;
import com.tomax.api.RNetApiContexts;
import com.tomax.api.UserIdentity;
import com.tomax.config.domainRef.api.DomainRefAPI;
import com.tomax.config.domainRef.dto.DomainRefCodeDTO;


/**
 * @author lovanya.chaudhary
 *
 */
@Service
public class DomainService {
	
	Logger logger = LoggerFactory.getLogger(DomainService.class);
	
	private static DomainRefAPI domainRefAPI = RNetApiContexts.hq.getStateless(DomainRefAPI.class);
	
	@Autowired
	private DomainRefRepository domainRefRepository;
	
	@Autowired
	private MapToResponseUtil mapToResponseUtil;
	
	public List<DomainRefCode> getDomainCodes(UserIdentity domainUser, String description) {
		// TODO Auto-generated method stub
		List<DomainRefCodeDTO> listOfDomains = domainRefAPI.getDomainRefCodes(domainUser, description);
		List<DomainRefCode> domainCodes = null;
		if(listOfDomains != null && !listOfDomains.isEmpty())
			domainCodes = mapToResponseUtil.getDomainRefCodes(listOfDomains);
		return domainCodes;
	}
	
	public List<DomainRef> getDomainRefInformation(UserIdentity domainUser) {
		// TODO Auto-generated method stub
		Iterable<DomainRefEntity> domains = domainRefRepository.findAll();
		Iterator<DomainRefEntity> domain = domains.iterator();

		List<DomainRefEntity> listOfDomains = new ArrayList<DomainRefEntity>();

		while (domain.hasNext()) {

			DomainRefEntity de = domain.next();

			if (StringUtils.isNotBlank(de.getDescription()))
				System.out.println("Domain desc == " + de.getDescription());

			listOfDomains.add(de);
		}
		List<DomainRef> domainRefs = null;
		if(listOfDomains != null && !listOfDomains.isEmpty())
			domainRefs = mapToResponseUtil.getDomainRefs(listOfDomains);

		return domainRefs;
	}

}
