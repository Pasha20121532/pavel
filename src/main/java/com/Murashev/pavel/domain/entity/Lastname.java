package com.Murashev.pavel.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "lastname") //фамилия
public class Lastname {
    @Id
    @Column(name = "lastname_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    public Lastname() {
    }
    public Lastname(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
