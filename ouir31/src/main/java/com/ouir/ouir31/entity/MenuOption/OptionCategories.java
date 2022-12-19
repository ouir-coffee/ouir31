package com.ouir.ouir31.entity.MenuOption;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "optioncategories")
@Data
public class OptionCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int occode;

    @Column(nullable = false, length = 20)
    private String ocitem;
}
