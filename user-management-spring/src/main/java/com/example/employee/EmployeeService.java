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

    public EmployeeDTO getEmployeeByUserName(String userName) {
        Employee empl = repo.findByUserUserName(userName);
        return EmployeeMapper.mapEntityIntoDTO(empl);
    }

    public EmployeeDTO update(String userName, EmployeeDTO employee) throws Exception {
        Employee empl = repo.findByUserUserName(userName);
        if (empl == null) {
            throw new Exception("User not found");
        }

        empl.setFirstName(employee.getFirstName());
        empl.setMiddleName(employee.getMiddleName());
        empl.setLastName(employee.getLastName());
        empl.setDob(employee.getDob());
        empl.setSex(employee.getSex());
        empl.setPhoneNumber(employee.getPhoneNumber());

        repo.save(empl);
        return getEmployeeByUserName(userName);
    }

}
