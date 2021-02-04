package com.rediron.platform.domain.repositories;


import com.rediron.platform.domain.entities.Employee;
import com.rediron.security.common.repository.ReadOnlyRepository;

public interface EmployeeRepository extends ReadOnlyRepository<Employee, Long>{

	Employee findByEmployeeId(String employeeId);
}
