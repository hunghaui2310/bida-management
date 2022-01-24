package com.bida.management.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_product")
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 7L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(nullable = false)
    private Double price;
}
