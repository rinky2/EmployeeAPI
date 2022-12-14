package com.javaspring.employee.EmployeeApi.Service;

import com.javaspring.employee.EmployeeApi.Model.EmpDAO;
import com.javaspring.employee.EmployeeApi.Model.EmpDTO;

import java.util.List;
import java.util.Optional;

public interface EmpService {

    List<EmpDTO> getAll();


    Optional<EmpDAO> getData(int id);

    void addData(EmpDTO empDTO);

    void updateData(int id, EmpDTO empDTO);

    void deleteData(int id);

    void updatePartialData(int id, String name);

}
