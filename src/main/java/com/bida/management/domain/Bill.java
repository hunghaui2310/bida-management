package com.bida.management.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "tbl_bill")
@Getter
@Setter
@NoArgsConstructor
public class Bill implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "customer_name", length = 100)
    private String customerName;

    @Size(max = 50)
    @Column(name = "customer_phone_number", length = 50)
    private String customerPhoneNumber;

    @Size(max = 255)
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(nullable = false)
    private Double price;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Instant createdDate = Instant.now();

    @NotNull
    @Column(nullable = false)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "history_billiards_id", nullable = false)
    private HistoryBilliards historyBilliards;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
