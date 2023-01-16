package com.ouir.ouir31.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;


@Data
public class Admin {
    @Id
    @Column(nullable = false, length = 20)
    private String aid = "Admin";

    @Column(nullable = false, length = 70)
    private String apwd = "Admin";

    @Column(nullable = false, length = 20)
    private String aname = "관리자";
}