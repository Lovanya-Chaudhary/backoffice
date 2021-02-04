package com.rediron.platform.domain.services;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.repositories.MenuRepository;
import com.tomax.api.UserIdentity;

@Service
public class MenuService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(MenuService.class);
	
	@Autowired
	private MenuRepository menuRepository;

	public List<HashMap<String, String>> getdisplayMenu(UserIdentity domainUser, int siteNo, int menuSeq,
			String userName) {
		LOGGER.info("=== Entered in menu service ===");
		return menuRepository.getdisplayMenu(domainUser, siteNo, menuSeq, userName);
	}

}
