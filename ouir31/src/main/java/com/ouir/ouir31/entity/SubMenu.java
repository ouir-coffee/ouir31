package com.ouir.ouir31.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "submenutbl")
@Data
public class SubMenu {
    @Id
    @Column(nullable = false, length = 20)
    private String sitem;

    @Column(nullable = true)
    @ColumnDefault("0")
    private boolean sshot;

    @Column(nullable = true)
    @ColumnDefault("0")
    private boolean ssyrup;

    @Column(nullable = true)
    @ColumnDefault("0")
    private boolean swhipping;

}
