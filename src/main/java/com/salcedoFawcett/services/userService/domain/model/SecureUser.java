package com.salcedoFawcett.services.userService.domain.model;

import java.util.Set;

public class SecureUser {

    private int id;
    private String username;
    private String name;
    private Set access;

    public SecureUser(int id, String username, String name, Set access) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.access = access;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getAccess() {
        return access;
    }
}
