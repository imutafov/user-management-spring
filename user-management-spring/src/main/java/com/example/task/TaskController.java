/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.task;

import com.example.employee.Employee;
import com.example.employee.EmployeeService;
import com.example.employer.Employer;
import com.example.employer.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Teodor Todorov
 */
@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("this.isOwner(principal.username, #id)")
    @RequestMapping(value = "/tasks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    @PreAuthorize("this.isAssignee(principal.username, #id)")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public TaskDTO update(@PathVariable Long id, @RequestBody Task task) throws Exception {
        return service.logWork(id, task);
    }

    public boolean isOwner(String username, Long id) {
        Employer employer = employerService.getByUsername(username);
        Employee employee = employeeService.getById(id);
        if ((employer.getEmployees().contains(employee))) {
            return true;
        }
        return false;
    }

    public boolean isAssignee(String username, Long id) {
        Employee employee = employeeService.getByUsername(username);
        Task task = service.getById(id);
        if ((task.getAssignees().contains(employee))) {
            return true;
        }
        return false;
    }
}
