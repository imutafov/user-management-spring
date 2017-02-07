/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author msol-pc
 */
@Getter
@Setter
public class UserAuthenticationRequest implements Serializable {

    private String username;
    private String password;

    public UserAuthenticationRequest() {
        super();
    }

    public UserAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
