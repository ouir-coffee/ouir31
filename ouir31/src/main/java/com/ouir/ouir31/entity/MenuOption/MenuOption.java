package com.ouir.ouir31.entity.MenuOption;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "menuoption")
@Data
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mono;

//    @ManyToOne
//    @JoinColumn(name = "momenuno")
//    private Menu menu;
//
//    @ManyToOne
//    @JoinColumn(name = "mooptionno")
//    private Option option;
}
