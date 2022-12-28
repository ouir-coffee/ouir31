package com.ouir.ouir31.entity.MenuOption;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "optiontbl")
@Data
public class Option {

    @Id
    @Column(nullable = false, length = 20)
    private String oitem;

    @Column(nullable = false)
    private int oprice;

    @Column(nullable = false)
    private String ocate;

}
