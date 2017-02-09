/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employee;

import com.example.employer.EmployerRepository;
import com.example.user.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author msol-pc
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmployerRepository employerRepo;

    public Employee save(Employee empl) {
        return repo.save(empl);
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

}
