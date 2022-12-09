package com.ouir.ouir31.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "carttbl")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chno;

    @ManyToOne
    @JoinColumn(name = "chmitem")
    private Menu chmitem;

    @Column(nullable = false)
    private int chmcount;

    @Column(nullable = false)
    private int chshot;

    @Column(nullable = false)
    private int chsyrup;

    @Column(nullable = false)
    private int chwhipping;

    @ManyToOne
    @JoinColumn(name = "chuid")
    private User chuid;

    @Column(nullable = false)
    private boolean chcomp;

}
