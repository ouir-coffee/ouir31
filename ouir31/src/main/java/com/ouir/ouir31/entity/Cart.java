package com.ouir.ouir31.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "carttbl")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cno;

    //메뉴item
    @Column(nullable = false,length = 20)
    private String cmitem;

    //옵션1,2,3
    private String coitem1;
    private String coitem2;
    private String coitem3;

    // 결제수단
    @Column(nullable = false)
    private String cmethod;

    // 요청사항
    @Column(nullable = false,length = 150)
    private String crequest;

    // 총 가격
    @Column(nullable = false)
    private int cprice;

    // 날짜 시간.
    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp cdate;

    // 상태값
    @Column(nullable = false)
    private int cstatus;

    // id값
    @Column(nullable = false)
    private String cuid;


}
