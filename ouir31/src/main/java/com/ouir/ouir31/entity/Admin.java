package com.ouir.ouir31.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "admintbl")
@Data
public class Admin {
    @Id
    @Column(nullable = false, length = 20)
    private String a_id;

    @Column(nullable = false, length = 70)
    private String a_pwd;

    @Column(nullable = false, length = 20)
    private String a_name;
}