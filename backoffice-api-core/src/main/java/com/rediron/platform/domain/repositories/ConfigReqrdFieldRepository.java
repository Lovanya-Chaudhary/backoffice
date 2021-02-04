package com.rediron.platform.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.rediron.platform.domain.entities.ConfigReqrdField;
import com.rediron.platform.domain.entities.ConfigReqrdFieldPK;

public interface ConfigReqrdFieldRepository extends CrudRepository<ConfigReqrdField	, ConfigReqrdFieldPK>{
}
