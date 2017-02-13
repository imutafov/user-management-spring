/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author msol-pc
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    public Employee save(Employee empl) {
        return repo.save(empl);
    }

    public Employee getById(Long id) {
        return repo.findOne(id);
    }

    public Page<Employee> getAllEmployees(Pageable pageRequest) {
        return repo.findAll(pageRequest);
    }

    public Page<Employee> getEmployeesByEmployer(String employerName, Pageable pageRequest) {
        return repo.findByEmployerUserUserName(employerName, pageRequest);
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
