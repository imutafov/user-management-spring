/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employee;

public class EmployeeMapper {

    public static EmployeeDTO mapEntityIntoDTO(Employee employee) {

        EmployeeDTO dto = new EmployeeDTO();

        dto.setFirstName(employee.getFirstName());
        dto.setMiddleName(employee.getMiddleName());
        dto.setLastName(employee.getLastName());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setSex(employee.getSex());
        dto.setDob(employee.getDob());

        return dto;
    }

}
