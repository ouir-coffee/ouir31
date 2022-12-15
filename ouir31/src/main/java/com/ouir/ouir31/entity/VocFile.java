package com.ouir.ouir31.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "vocfiletbl")
@Data
public class VocFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vfnum;

    @ManyToOne
    @JoinColumn(name = "vfid")
    private Voc vfid;

    @Column(nullable = false, length = 50)
    private String vfsysname;

    @Column(nullable = false, length = 50)
    private String vforiname;
}
