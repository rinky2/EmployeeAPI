package com.javaspring.employee.EmployeeApi.Repository;

import com.javaspring.employee.EmployeeApi.Model.EmpDAO;
import org.springframework.data.repository.CrudRepository;

public interface EmpRepository extends CrudRepository<EmpDAO, Integer> {

}
