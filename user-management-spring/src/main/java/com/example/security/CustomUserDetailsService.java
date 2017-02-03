/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import com.example.user.User;
import com.example.user.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author I.Mutafov
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        System.out.println(user.getFirstName() + " " + user.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with username: " + username);
        }
        return user;
    }

    public List<User> getAllFlaggedUsers() {
        return userRepository.findByEnabledTrue();
    }

    public List<User> getAllUnflaggedUsers() {
        return userRepository.findByEnabledFalse();
    }

    public User changeFlag(String name) {
        User user = userRepository.findByUserName(name);
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
        return user;
    }

//    private Set<GrantedAuthority> getAuthorities(User user) {
//        Set<GrantedAuthority> authorities = new HashSet();
//        for (Role role : user.getRoles()) {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
//            authorities.add(grantedAuthority);
//        }
//        return authorities;
//    }
}
