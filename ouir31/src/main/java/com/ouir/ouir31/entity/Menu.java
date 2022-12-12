package com.ouir.ouir31.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "menutbl")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int m_no;

    @Column(nullable = false, length = 20)
    private String m_item;

    @Column(nullable = false)
    private int m_price;

    @Column(nullable = false, length = 10)
    private String m_type;
}
