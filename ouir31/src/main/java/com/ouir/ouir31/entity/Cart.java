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
    private long c_no;

    @ManyToOne
    @JoinColumn(name = "c_m_no")
    private Menu c_m_no;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int c_mcount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int c_shot;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int c_syrup;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int c_whipping;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean c_comp;

    @ManyToOne
    @JoinColumn(name = "c_u_id")
    private User c_u_id;

}
