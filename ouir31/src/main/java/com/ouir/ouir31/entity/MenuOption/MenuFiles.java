package com.ouir.ouir31.entity.MenuOption;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "menufiles")
@Data
public class MenuFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mfno;

    @Column(name = "mfmitem")
    private String mfmitem;

    @Column(nullable = false,length = 50)
    private String mforiname;

    @Column(nullable = false,length = 50)
    private String mfsysname;


}
