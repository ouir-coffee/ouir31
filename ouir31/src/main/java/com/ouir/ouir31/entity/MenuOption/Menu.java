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

    @Column(nullable = false)
    private int mprice;

    @Column(nullable = false, length = 20)
    private String mcate;

    @Transient
    private List<MenuFiles> mfList;
}
