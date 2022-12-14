package com.ouir.ouir31.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "usertbl")
@Data
public class User {
    @Id
    @Column(nullable = false, length = 20)
    private String uId;

    @Column(nullable = false, length = 70)
    private String uPwd;

    @Column(nullable = false, length = 20)
    private String uName;

    @Column(nullable = false, length = 50)
    private String uAdd;

    @Column(nullable = false, length = 40)
    private String uEmail;

    @Column(nullable = false, length = 20)
    private String uPhone;

}