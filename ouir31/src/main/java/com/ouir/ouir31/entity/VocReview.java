package com.ouir.ouir31.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "vrtbl")
@Data

public class VocReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vr_no")
    private Long vrno;

    @ManyToOne
    @JoinColumn(name = "voc_vr_no")
    private Voc vvrno;

//    @ManyToOne //??
//    @JoinColumn(name = "admin_vr_id")
//    private Admin avrid;

//    @ManyToOne //??
//    @JoinColumn(name = "user_vr_no")
//    private User uvrno;

    @Column(length = 255)
    private String vrcontents;

    @CreationTimestamp
    @Column
    private Timestamp vrdate;
}
