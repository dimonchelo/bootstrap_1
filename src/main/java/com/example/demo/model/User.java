package com.example.demo.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "name empty")
    @Size(min = 2, max = 30, message = "name size [2-30]")
    @Pattern(regexp = "[A-Za-z]+", message = "only A-Z , a-z")
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    @NotEmpty(message = "password empty")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Role> roles;
    @NotEmpty(message = "lastname empty")
    @Size(min = 2, max = 30, message = "lastname size [2-30]")
    @Pattern(regexp = "[A-Za-z]+", message = "only A-Z , a-z")
    @Column(name = "lastName")
    private String lastName;


    public User() {
    }

    public User(int id, String name, String password, List<Role> roles, String lastName) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.lastName = lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // rols
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
        return true;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
