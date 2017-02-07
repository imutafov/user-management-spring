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
public class UserAuthenticationResponse implements Serializable {

    private final String token;

    public UserAuthenticationResponse(String token) {
        this.token = token;
    }

}
