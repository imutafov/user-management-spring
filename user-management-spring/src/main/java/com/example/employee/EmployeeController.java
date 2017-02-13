/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employee;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author msol-pc
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(value = "/employees", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Employee save(@RequestBody Employee empl) {
        return service.save(empl);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> getAllEmployees(Pageable pageRequest) {
        return service.getAllEmployees(pageRequest).getContent();
    }

    @RequestMapping(value = "/employees/self", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeDTO currentEmployee() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDTO empl = service.getEmployeeByUserName(auth.getName());
        return empl;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public EmployeeDTO update(@RequestBody EmployeeDTO empl) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return service.update(auth.getName(), empl);
    }

}
