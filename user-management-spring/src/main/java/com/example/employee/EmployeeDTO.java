/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.employee;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Teodor Todorov
 */
@Data
public class EmployeeDTO implements Serializable {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String sex;
    private Date dob;

}
