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
    private long nNo;

    @Column(nullable = false, length = 20)
    private String nTitle;

    @Column(nullable = false, length = 255)
    private String nContents;

    @CreationTimestamp
    @Column
    private Timestamp nDate;
}
