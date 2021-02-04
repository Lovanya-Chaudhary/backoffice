package com.rediron.platform.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rediron.platform.domain.entities.Adhdr;
import com.rediron.platform.domain.entities.AdhdrPK;

public interface AdhdrRepository extends CrudRepository<Adhdr, AdhdrPK>{
	
	Optional<Adhdr> findById(AdhdrPK id);
	List<Adhdr> findByStatus(String status);
}
