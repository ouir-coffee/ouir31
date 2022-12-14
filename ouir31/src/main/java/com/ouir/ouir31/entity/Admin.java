package com.ouir.ouir31.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "admintbl")
@Data
public class Admin {
    @Id
    @Column(nullable = false, length = 20)
    private String aId;

    @Column(nullable = false, length = 70)
    private String aPwd;

    @Column(nullable = false, length = 20)
    private String aName;
}