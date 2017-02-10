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

    public Employee getById(Long id) {
        return repo.findOne(id);
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public List<Employee> getEmployeesByEmployer(String employerName) {
        return repo.findByEmployerUserUserName(employerName);
    }

    public Employee update(Long id, Employee empl) throws Exception {
        Employee dbEmpl = repo.findOne(id);
        if (empl == null) {
            throw new Exception("Employee not found");
        }
        dbEmpl.setWorkDeptId(empl.getWorkDeptId());
        dbEmpl.setJob(empl.getJob());
        dbEmpl.setSalary(empl.getSalary());
        dbEmpl.setBonus(empl.getBonus());
        dbEmpl.setCommission(empl.getCommission());
        return repo.save(dbEmpl);
    }

}
