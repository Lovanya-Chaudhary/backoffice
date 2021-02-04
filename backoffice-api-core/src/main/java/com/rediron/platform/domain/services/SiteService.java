package com.rediron.platform.domain.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.model.response.GroupsAllResponse;
import com.rediron.platform.domain.model.response.SiteDetailResponse;
import com.rediron.platform.domain.model.response.SiteResponse;
import com.rediron.platform.domain.repositories.SiteRepository;
import com.rediron.platform.domain.util.MapToResponseUtil;
import com.tomax.api.RNetApiContexts;
import com.tomax.api.UserIdentity;
import com.tomax.site.api.SiteAPI;
import com.tomax.site.dto.GroupsAllDTO;
import com.tomax.site.dto.SiteDTO;


/**
 * @author lovanya.chaudhary
 *
 */
@Service
public class SiteService {
	
	Logger logger = LoggerFactory.getLogger(SiteService.class);
	
	private static SiteAPI siteAPI = RNetApiContexts.hq.getStateless(SiteAPI.class);
	
	@Autowired
	private SiteRepository siteRepository;
	
	@Autowired
	private MapToResponseUtil mapToResponseUtil;
	
	// strts
	
	public GroupsAllResponse getSiteGrouptest(UserIdentity domainUser, String siteGroupId) {
		// TODO Auto-generated method stub
		GroupsAllResponse groupsAll = mapToResponseUtil.getGroupsAll(siteAPI.getGroup(domainUser, siteGroupId));
		return groupsAll;
	}
	
	// edns
	
	public GroupsAllResponse getSiteGroup(UserIdentity domainUser, String siteGroupId) {
		// TODO Auto-generated method stub
		GroupsAllDTO groupsAllDTO = siteAPI.getGroup(domainUser, siteGroupId);
		GroupsAllResponse groupsAll = null;
		
		if(groupsAllDTO != null)
			groupsAll = mapToResponseUtil.getGroupsAll(groupsAllDTO);
		
		return groupsAll;
	}
	
	public Integer getCurrentSite(UserIdentity domainUser) {
		// TODO Auto-generated method stub		
		return siteAPI.getCurrentSite(domainUser);
	}
	
	public SiteResponse getSiteDetails(UserIdentity domainUser, int siteNumber) {
		// TODO Auto-generated method stub
		SiteDTO dto = siteAPI.getSite(domainUser, siteNumber);
		SiteResponse site = null;
		if(dto != null)
			site = mapToResponseUtil.getSite(dto);
		return site;
	}
	
	public List<GroupsAllResponse> getAllSiteGroups(UserIdentity domainUser) {
		// TODO Auto-generated method stub
		List<GroupsAllDTO> groupsAllDTOs = siteAPI.getAllGroups(domainUser);
		List<GroupsAllResponse> groupsAllList = null;
		if(groupsAllDTOs != null)
			groupsAllList = mapToResponseUtil.getGroupsAllList(groupsAllDTOs);
		return groupsAllList;
	}
	
	public List<GroupsAllResponse> getAllSiteGroupsTests(UserIdentity domainUser) {
		// TODO Auto-generated method stub
		List<GroupsAllResponse> groupsAllList = mapToResponseUtil.getGroupsAllList(siteAPI.getAllGroups(domainUser));
		return groupsAllList;
	}
	
	// all sites
	public List<SiteDetailResponse> getAllSites() {
		return siteRepository.getAllSites();
	}
	
	public List<SiteResponse> getSitesInfoForGroup(UserIdentity domainUser, String siteGroupId) {
		List<SiteDTO> siteDTOs = siteAPI.getSitesInfoForGroup(domainUser, siteGroupId, 0, 100);
		List<SiteResponse> sites = null;
		if(siteDTOs != null)
			sites = mapToResponseUtil.getSites(siteDTOs);
		return sites;
	}
	

}
