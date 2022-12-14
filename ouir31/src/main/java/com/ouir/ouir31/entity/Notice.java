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
    private long nno;

    @Column(nullable = false, length = 20)
    private String ntitle;

    @Column(nullable = false, length = 255)
    private String ncontents;

    @CreationTimestamp
    @Column
    private Timestamp ndate;
}
