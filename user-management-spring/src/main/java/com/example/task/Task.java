/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.task;

import com.example.employee.Employee;
import com.example.employer.Employer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASK_ID")
    private Long id;

    private String title;

    private String progress;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "EMPLOYEE_TASKS", joinColumns = {
        @JoinColumn(name = "TASK_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "EMPLOYEE_ID")}
    )
    @JsonIgnore
    private List<Employee> assignees;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "EMPLOYER_TASKS", joinColumns = {
        @JoinColumn(name = "TASK_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "EMPLOYER_ID")}
    )
    private Employer assigner;

}
