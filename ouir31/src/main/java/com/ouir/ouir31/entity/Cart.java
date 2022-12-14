package com.ouir.ouir31.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "carttbl")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cNo;

    @ManyToOne
    @JoinColumn(name = "cmNo")
    private Menu cmNo;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int cMenuCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int cShot;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int cSyrup;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int cWhipping;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean cComp;

    @ManyToOne
    @JoinColumn(name = "cuId")
    private User cuId;

}
