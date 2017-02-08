package com.example.user;

import com.example.access.Privilege;
import com.example.access.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "T_USER")
@Getter
@Setter
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private long userId;

    @JsonView(Views.Public.class)
    @Column(name = "USERNAME")
    private String userName;

    private String password;

    @JsonView(Views.Public.class)
    @Column(name = "FIRSTNAME")
    private String firstName;

    @JsonView(Views.Public.class)
    @Column(name = "LASTNAME")
    private String lastName;

    @JsonView(Views.Public.class)
    private Date birthDate;
    private String phoneNumber;
    private String email;

    @Column(name = "ENABLED")
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = {
        @JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {
                @JoinColumn(name = "ROLE_ID")})
    private Role role;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.addAll((Collection<? extends GrantedAuthority>) role.getPrivileges());
        return authList;
    }

    public Collection<Privilege> getPrivileges() {
        return role.getPrivileges();
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
