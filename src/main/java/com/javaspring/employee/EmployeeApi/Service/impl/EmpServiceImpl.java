package com.javaspring.employee.EmployeeApi.Service.impl;

import com.javaspring.employee.EmployeeApi.Model.EmpDAO;
import com.javaspring.employee.EmployeeApi.Model.EmpDTO;
import com.javaspring.employee.EmployeeApi.Repository.EmpRepository;
import com.javaspring.employee.EmployeeApi.Service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpRepository empRepository;

    public static Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);

    public List<EmpDTO> getAll() {

        List<EmpDAO> empList = (List<EmpDAO>) empRepository.findAll();
        List<EmpDTO> empdto = new ArrayList();
        logger.info("empList:{}",empList);
        if(!empList.isEmpty()){
            for (int i = 0; i < empList.size(); i++) {
                EmpDAO empdb = empList.get(i);
                EmpDTO dto = new EmpDTO(empdb);
                empdto.add(dto);
            }
        }
        else{
            logger.error("No Employee Data present!!");
        }
        return empdto;
    }

    public Optional<EmpDAO> getData(int id) {

        Optional<EmpDAO> opt = empRepository.findById(id);
        try{
            if(opt.isPresent())
                logger.info("found the emp with id:{}",id);

        }catch(Exception e){
            logger.error("Can't find data");
        }
        return opt;
    }

    public void addData(EmpDTO empDTO) {
        EmpDAO empDAO = new EmpDAO(empDTO);
        List<EmpDAO> empData = (List<EmpDAO>) empRepository.findAll();
        for (int i = 0; i < empData.size(); i++) {
            EmpDAO emp = empData.get(i);
            if (empDTO.getName().equals(emp.getName())) {
                logger.warn("Emp already present with ID: {}",empDTO.getId());
                throw new RuntimeException("Can't add data with similar names");
                 }
        }
        empRepository.save(empDAO);
    }



    public void deleteData(int id) {
        Optional<EmpDAO> opt = empRepository.findById(id);
       if(opt.isPresent()) {
           empRepository.deleteById(id);
           logger.info("Emp Deleted with id:{}",id);
       }
        else
            throw new RuntimeException("Emp with id:"+id+" not found!!");

    }


    public void updateData(int id, EmpDTO empDTO) {

        Optional<EmpDAO> db = empRepository.findById(id);
        if (db.isPresent()) {
            EmpDAO emp = db.get();
            if(empDTO.getName()!= null)
                emp.setName(empDTO.getName());
            if(empDTO.getEmail()!= null)
                emp.setEmail(empDTO.getEmail());
            if(empDTO.getSalary()!= ' ')
                emp.setSalary(empDTO.getSalary());

            empRepository.save(emp);
            logger.info("Employee Data updated !!!");
        } else {
            logger.error("Topic Id not found to update!!");
            throw new RuntimeException( "EmpID Not Found!!!!");
        }
    }

    public void updatePartialData(int id, String name) {

        Optional<EmpDAO> db = empRepository.findById(id);
        if (db.isPresent()) {
            EmpDTO dto = new EmpDTO(db.get());
            EmpDAO t = db.get();
            if (name!= null)
                t.setName(name);
            empRepository.save(t);
        } else {
            logger.error("Topic Id not found to update!!");
            throw new RuntimeException( "EmpID Not Found!!!!");
        }
    }
}
