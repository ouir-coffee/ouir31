package com.ouir.ouir31.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menucategories")
public class MenuCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mccode;

    @Column(nullable = false, length = 20)
    private String mcitem;

    @OneToMany(mappedBy = "menuCategories")
    private List<Menu> menus = new ArrayList<Menu>();

}
