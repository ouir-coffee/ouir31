package com.ouir.ouir31.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "menutbl")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mNo;

    @Column(nullable = false, length = 20)
    private String mItem;

    @Column(nullable = false)
    private int mPrice;

    @Column(nullable = false, length = 10)
    private String mType;
}
