package com.ouir.ouir31.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "admintbl")
@Data
public class Admin {
    @Id
    @Column(nullable = false, length = 20)
    private String aid;

    @Column(nullable = false, length = 70)
    private String apwd;

    @Column(nullable = false, length = 20)
    private String aname;
}