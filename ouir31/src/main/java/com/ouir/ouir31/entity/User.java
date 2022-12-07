package com.ouir.ouir31.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "usertbl")
@Data
public class User {
    @Id
    @Column(nullable = false, length = 20)
    private String uid;

    @Column(nullable = false, length = 70)
    private String upwd;

    @Column(nullable = false, length = 20)
    private String uname;

    @Column(nullable = false, length = 50)
    private String uadd;

    @Column(nullable = false, length = 40)
    private String uemail;
}