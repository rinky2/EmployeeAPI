package com.javaspring.employee.EmployeeApi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class CustomError {

    private HttpStatus status;
    private String message;

    public HttpStatus getStatus() {
        return status;
    }

}
