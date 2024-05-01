package com.example.application.data;

import jakarta.persistence.*;

@Entity
@Table(name = "absence_category")
public class AbsenceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
