package com.ouir.ouir31.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "noticetbl")
@Data
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long n_no;

    @Column(nullable = false, length = 20)
    private String n_title;

    @Column(nullable = false, length = 255)
    private String n_contents;

    @CreationTimestamp
    @Column
    private Timestamp n_date;
}
