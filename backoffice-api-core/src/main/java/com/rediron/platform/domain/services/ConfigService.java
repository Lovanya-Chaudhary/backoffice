package com.rediron.platform.domain.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rediron.platform.domain.entities.ConfigReqrdField;
import com.rediron.platform.domain.entities.ConfigReqrdFieldPK;
import com.rediron.platform.domain.model.request.ConfigReqrdFieldModel;
import com.rediron.platform.domain.model.request.ConfigReqrdFieldsRequest;
import com.rediron.platform.domain.model.response.ConfigReqrdFieldResponse;
import com.rediron.platform.domain.repositories.ConfigReqrdFieldRepository;
import com.tomax.api.UserIdentity;

@Service
public class ConfigService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ConfigService.class);

	@Autowired
	private ConfigReqrdFieldRepository configReqrdFieldRepository;

	public List<ConfigReqrdFieldResponse> getConfiguration(UserIdentity domainUser) {
		List<ConfigReqrdField> allconfigs = null;
		List<ConfigReqrdFieldResponse> configs = null;
		Iterable<ConfigReqrdField> listOfConfigs = configReqrdFieldRepository.findAll();
		if (listOfConfigs != null) {
			allconfigs = new ArrayList<>();
			listOfConfigs.forEach(allconfigs::add);
		}
		if (allconfigs != null) {
			configs = mapToConfigReqrdFieldResponse(allconfigs);
		}
		return configs;
	}

	private List<ConfigReqrdFieldResponse> mapToConfigReqrdFieldResponse(List<ConfigReqrdField> allconfigs) {
		LOGGER.info("=== Mapping required fields configuration entity response to custom response ===");
		List<ConfigReqrdFieldResponse> configs = null;
		// use CollectionUtils.isEmpty
		if (allconfigs != null && !allconfigs.isEmpty()) {
			configs = new ArrayList<>();
			for (ConfigReqrdField config : allconfigs) {
				ConfigReqrdFieldResponse response = new ConfigReqrdFieldResponse();
				response.setFormName(config.getId().getFormName());
				response.setBlockName(config.getId().getBlockName());
				response.setItemName(config.getId().getItemName());
				response.setLabelTxt(config.getLabelTxt());
				response.setNotNullFl(config.getNotNullFl());
				response.setDefaultValue(config.getDefaultValue());
				configs.add(response);
			}
		}
		return configs;
	}

	public List<ConfigReqrdFieldResponse> updateConfiguration(UserIdentity domainUser,
			ConfigReqrdFieldsRequest request) {
		List<ConfigReqrdFieldModel> configs = request.getListOfConfigReqrdFieldModel();
		List<ConfigReqrdField> list = null;
		if (configs != null && !configs.isEmpty()) {
			list = new ArrayList<>();
			for (ConfigReqrdFieldModel model : configs) {

				ConfigReqrdFieldPK id = new ConfigReqrdFieldPK();
				id.setFormName(model.getFormName());
				id.setBlockName(model.getBlockName());
				id.setItemName(model.getItemName());

				Optional<ConfigReqrdField> field = configReqrdFieldRepository.findById(id);
				if (field.isPresent()) {
					ConfigReqrdField configField = field.get();
					configField.setDefaultValue(model.getDefaultValue());
					configField.setNotNullFl(model.getNotNullFl());
					configField.setUserModified(domainUser.getUserName());
					configField.setDateModified(new Date());
					LOGGER.info("=== Calling save on the required fields configuration ===");
					list.add(configReqrdFieldRepository.save(configField));
				}
			}
		}
		return mapToConfigReqrdFieldResponse(list);
	}

}
