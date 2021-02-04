package com.rediron.platform.domain.repositories;

import com.rediron.platform.domain.entities.CustomerCategoryCdEntity;
import com.rediron.security.common.repository.ReadOnlyRepository;

public interface CustomerCategoryCdRepository extends ReadOnlyRepository< CustomerCategoryCdEntity, Long > {

	CustomerCategoryCdEntity findByCustCategoryCd(String custCategoryCd);
	
}
