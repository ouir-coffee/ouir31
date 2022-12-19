package com.ouir.ouir31.entity.MenuOption;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "optiontbl")
@Data
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ono;

    @Column(nullable = false, length = 20)
    private String oitem;

    @Column(nullable = false)
    private int oprice;

    @ManyToOne
    @JoinColumn(name = "optionCategories")
    private OptionCategories optionCategories;

}
