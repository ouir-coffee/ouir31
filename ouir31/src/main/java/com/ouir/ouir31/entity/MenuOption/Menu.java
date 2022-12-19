package com.ouir.ouir31.entity.MenuOption;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menutbl")
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;

    @Column(nullable = false, length = 20)
    private String mitem;

    @Column(nullable = false)
    private int mprice;

    @ManyToOne
    @JoinColumn(name = "menuCategories")
    private MenuCategories menuCategories;

//    @OneToMany(mappedBy = "menu")
//    private List<MenuImages> menuImages = new ArrayList<>();
//
//    @OneToMany(mappedBy = "menu")
//    private List<MenuOption> menuOptions = new ArrayList<>();
}
