package com.javaspring.employee.EmployeeApi.Controller;

import com.javaspring.employee.EmployeeApi.Model.CustomError;
import com.javaspring.employee.EmployeeApi.Model.EmpDAO;
import com.javaspring.employee.EmployeeApi.Model.EmpDTO;
import com.javaspring.employee.EmployeeApi.Service.impl.EmpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class EmpController {

    @Autowired //dependency injection
    private EmpServiceImpl empServiceImpl;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleError(HttpServletRequest req, Exception ex) {

        System.out.println(ex.getStackTrace());
        CustomError error = new CustomError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
    }

    @RequestMapping("/emp") //will be converted to json by spring mvc
    public List<EmpDTO> getAll()
    {
        return empServiceImpl.getAll();
    }

    @RequestMapping("/emp/{id}")
    public Optional<EmpDAO> getData(@PathVariable int id){

        return empServiceImpl.getData(id);
    }

    @PostMapping("/emp")
    public void addData(@RequestBody EmpDTO empDTO)//pick this instance from request payload
    {
        empServiceImpl.addData(empDTO);
    }

    @PutMapping( "/emp/{id}")
    public void updateData(@RequestBody EmpDTO empDTO, @PathVariable int id)//pick this instance from request payload
    {
        empServiceImpl.updateData(id, empDTO);
    }

    @DeleteMapping("/emp/{id}")
    public void deleteData(@PathVariable int id){
       empServiceImpl.deleteData(id);
    }

    @PatchMapping("/emp/{id}/{name}")
    public void updatePartialData( @PathVariable int id , @PathVariable String name){
        empServiceImpl.updatePartialData(id,name);

    }
}
