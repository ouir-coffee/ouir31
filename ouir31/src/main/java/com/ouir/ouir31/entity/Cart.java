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
    private long cno;

    @ManyToOne
    @JoinColumn(name = "cmno")
    private Menu cmno;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int cmenuCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int cshot;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int csyrup;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int cwhipping;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean ccomp;

    @ManyToOne
    @JoinColumn(name = "cuid")
    private User cuid;

}
