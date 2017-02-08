/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.user;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author msol-pc
 */
@Setter
@Getter
public class UserDTO implements Serializable {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
