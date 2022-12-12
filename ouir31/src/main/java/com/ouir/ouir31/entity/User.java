package com.ouir.ouir31.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "usertbl")
@Data
public class User {
    @Id
    @Column(nullable = false, length = 20)
    private String u_id;

    @Column(nullable = false, length = 70)
    private String u_pwd;

    @Column(nullable = false, length = 20)
    private String u_name;

    @Column(nullable = false, length = 50)
    private String u_add;

    @Column(nullable = false, length = 40)
    private String u_email;

    @Column(nullable = false, length = 20)
    private String u_phone;

}