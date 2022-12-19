package com.ouir.ouir31.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "menuimages")
@Data
public class MenuImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mino;

    @Column(nullable = false,length = 20)
    private String miname;

    @Column(nullable = false,length = 50)
    private String mipath;

    @ManyToOne
    @JoinColumn(name = "mid")
    private Menu menu;

}
