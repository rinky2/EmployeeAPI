package com.javaspring.employee.EmployeeApi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdNameEmail {
    int id;
    String name;
    String email;

    public IdNameEmail(EmpDAO dao) {
        //Converting from Entity to DTO
        this.id = dao.getId();
        this.name = dao.getName();
        this.email = dao.getEmail();
    }
}

