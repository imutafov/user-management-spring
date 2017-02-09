/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employer;

import com.example.employee.Employee;
import com.example.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author msol-pc
 */
@Service
public class EmployerService {

    @Autowired
    private EmployerRepository repo;

    @Autowired
    private UserRepository userRepo;

    public Employer save(Employer empl) {
        empl.setUser(userRepo.findOne(3L));
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee());
        employees.add(new Employee());
        return repo.save(empl);
    }

    public List<Employer> getAllEmployers() {
        return repo.findAll();
    }

}
