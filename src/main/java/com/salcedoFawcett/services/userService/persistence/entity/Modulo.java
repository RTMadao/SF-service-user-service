package com.salcedoFawcett.services.userService.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "module")
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "accesos")
    Set<Usuario> usuariosAutorizados;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String moduleName) {
        this.name = moduleName;
    }
}
