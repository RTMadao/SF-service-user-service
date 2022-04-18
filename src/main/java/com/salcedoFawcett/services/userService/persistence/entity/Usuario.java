package com.salcedoFawcett.services.userService.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    @Column(name = "pass")
    private String password;
    private String name;
    private String email;
    private boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "user_access_module",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "moduleId"))
    Set<Modulo> accesos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Modulo> getAccesos() {
        return accesos;
    }

    public void setAccesos(Set<Modulo> accesos) {
        this.accesos = accesos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
