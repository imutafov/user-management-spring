/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.task;

import com.example.employee.Employee;
import com.example.employer.Employer;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity(name = "TASK")
@Data
public class Task {

//Нека да има work logging 
//– employers да създават/задават задачи 
//само за собствените си employees 
//free text title за задачата + към кого е assgin-ата задачата /може да е повече от един/
//– еmployees да имат възможност да логват какво е изпълнено /free text/ към задачата /може върху повече от една да е/ която работят    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WORKLOG_ID")
    private Long id;

    private String title;

    private String worksProgress;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "EMPLOYEE_WORKLOGS", joinColumns = {
        @JoinColumn(name = "WORKLOG_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "EMPLOYEE_ID")}
    )
    private List<Employee> assignees;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "EMPLOYER_WORKLOGS", joinColumns = {
        @JoinColumn(name = "WORKLOG_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "EMPLOYER_ID")}
    )
    private Employer assigner;

}
