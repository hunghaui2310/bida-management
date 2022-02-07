package com.bida.management.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_history_billiards")
@Getter
@Setter
@NoArgsConstructor
public class HistoryBilliards implements Serializable {

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_time_active")
    private Instant startTimeActive = Instant.now();

    @NotNull
    @Column(name = "end_time_active")
    private Instant endTimeActive = Instant.now();

    @ManyToOne
    @JoinColumn(name = "billiards_id", nullable = false)
    @JsonBackReference
    private Billiards billiards;

    private transient Long billiardsId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private User user;

    @PrePersist
    public void prePersist() {
        this.startTimeActive = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.endTimeActive = Instant.now();
    }
}
