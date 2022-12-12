package com.ouir.ouir31.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "submenutbl")
@Data
public class SubMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long s_no;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int s_shot;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int s_syrup;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int s_whipping;

    @ManyToOne
    @JoinColumn(name = "s_c_no")
    private Cart s_c_no;

}