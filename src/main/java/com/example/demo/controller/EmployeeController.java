package com.example.demo.controller;

import com.example.demo.bean.EmployeeBean;
import com.example.demo.exception.EmployeeIdNotFoundException;
import com.example.demo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/getAllDetails")
    public ResponseEntity<List<EmployeeBean>> getAllDetails(){
        List<EmployeeBean> employeeBeans=employeeService.findAll();
        log.info(" All Employee Details Retrieved");
        return new ResponseEntity<>(employeeBeans,HttpStatus.OK);


    }

    @GetMapping(value = "/findById")
    public ResponseEntity<EmployeeBean> findById(@RequestParam("id") Integer id)
            throws EmployeeIdNotFoundException {
        EmployeeBean employeeBean=employeeService.findById(id);
        if(employeeBean != null){
            log.info("user retrieved with valid Id {}",id);
            return new ResponseEntity<>(employeeBean,HttpStatus.OK);
        }else{
            log.error("user tried to Retrieve With invalid Id {}",id);
            throw new EmployeeIdNotFoundException("id not found");
        }

    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<EmployeeBean> delete(@RequestParam("id") Integer id)
            throws EmployeeIdNotFoundException {
        EmployeeBean employeeBean=employeeService.delete(id);
        if(employeeBean != null){
            log.info("user deleted account with id {}",id);
            return new ResponseEntity<>(employeeBean,HttpStatus.OK);
        }else{
            log.error("user tried to delete account with wrong id {}",id);
            throw new EmployeeIdNotFoundException("id not found");

        }

    }

    @PostMapping(value = "/add")
    public ResponseEntity<EmployeeBean> addEmployee(
            @RequestBody EmployeeBean employeeBean){

        employeeBean=employeeService.save(employeeBean);
        log.info("new user added with id {}",employeeBean.getId());
        return new ResponseEntity<>(employeeBean,HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<EmployeeBean> update(@RequestBody EmployeeBean employeeBean)
            throws EmployeeIdNotFoundException{
        int id = employeeBean.getId();
        employeeBean=employeeService.update(employeeBean);
        if(employeeBean != null) {
            log.info("user updated account with id {}", id);
            return new ResponseEntity<>(employeeBean, HttpStatus.OK);
        }
        log.error("user tried to update With invalid Id {} ",id);
        throw new EmployeeIdNotFoundException("id not found");
    }
}
