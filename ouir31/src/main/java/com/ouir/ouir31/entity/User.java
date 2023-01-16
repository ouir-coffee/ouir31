package com.ouir.ouir31.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "usertbl")
@Data
public class User {
    @Id
    @Column(nullable = false, length = 20)
    private String uid;

    @Column(nullable = false, length = 200)
    private String upwd;

    @Column(nullable = false, length = 20)
    private String uname;

    @Column(nullable = false, length = 40)
    private String uemail;

    @Column(nullable = false, length = 20)
    private String uphone;
}