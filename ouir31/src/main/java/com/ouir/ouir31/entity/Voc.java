package com.ouir.ouir31.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "voctbl")
@Data
public class Voc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vocno;

    @Column(nullable = false, length = 20)
    private String voctitle;

    @Column(nullable = false)
    private String voccontents;

    @Column
    @CreationTimestamp
    private Timestamp vocdate;

    @ManyToOne
    @JoinColumn(name = "u_id")
    private User vocuid;
}
