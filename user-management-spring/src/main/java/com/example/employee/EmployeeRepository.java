/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employee;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author msol-pc
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public List<Employee> findByEmployerUserUserName(String employerName);

}
