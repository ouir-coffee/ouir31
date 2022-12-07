package com.ouir.ouir31.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "submenutbl")
@Data
public class SubMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;

    @Column(nullable = false, length = 20)
    private String sitem;

    @Column(nullable = false)
    private int sprice;


}
