package com.ouir.ouir31.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "option")
@Data
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ono;

    @Column(nullable = false, length = 20)
    private String oitem;

    @Column(nullable = false)
    private int oprice;

    @ManyToOne
    @JoinColumn(name = "occode")
    private OptionCategories optionCategories;

}
