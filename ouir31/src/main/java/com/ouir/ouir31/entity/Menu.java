package com.ouir.ouir31.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "menutbl")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;

    @Column(nullable = false, length = 20)
    private String mitem;

    @Column(nullable = false)
    private int mprice;

    @Column(nullable = false, length = 10)
    private String mtype;
}
