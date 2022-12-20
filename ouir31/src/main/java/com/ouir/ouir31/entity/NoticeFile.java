package com.ouir.ouir31.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "noticefiletbl")
@Data

public class NoticeFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long nfnum;

    @ManyToOne
    @JoinColumn(name = "nfnid")
    private Notice nfnid;

    @Column(nullable = false, length = 50)
    private String nfsysname;

    @Column(nullable = false, length = 50)
    private String nforiname;
}
