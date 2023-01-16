package com.ouir.ouir31.entity.MenuOption;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menutbl")
@Data
public class Menu {

    @Id
    @Column(nullable = false, length = 20)
    private String mitem;

    @Column(nullable = false, length = 40)
    private String mname;

    @Column(nullable = false, length = 150)
    private String mcontents;

    @Column(nullable = false)
    private int mprice;

    @Column(nullable = false, length = 20)
    private String mcate;

    @Column(nullable = false)
    private boolean mbest;

    @Transient
    private MenuFiles mfList;
}
