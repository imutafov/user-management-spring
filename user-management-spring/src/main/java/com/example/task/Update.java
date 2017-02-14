/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.task;

import com.example.user.User;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author msol-pc
 */
@Entity
@Data
public class Update {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UPDATE_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeUpdated;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User updater;

    private String body;

    @PrePersist
    void setDates() {
        this.timeUpdated = new Date();
    }
}
