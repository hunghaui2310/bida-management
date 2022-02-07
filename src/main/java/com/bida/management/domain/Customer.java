package com.bida.management.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 50)
    @Column(name = "phone_number", length = 50, nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @NotNull
    @Column(name = "status", nullable = false)
    private int status;
}
