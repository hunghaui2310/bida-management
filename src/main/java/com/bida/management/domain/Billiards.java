package com.bida.management.domain;

import com.bida.management.util.AppUtil;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_billiards")
@Getter
@Setter
@NoArgsConstructor
public class Billiards implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 25)
    @Column(name = "name", length = 25)
    private String name;

    @NotNull
    @Column(name = "status", nullable = false)
    private int status;

    @NotNull
    @Column(name = "state", nullable = false)
    private boolean state = false;

    @NotNull
    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "billiards", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<HistoryBilliards> historyBilliards;

    private transient HistoryBilliards historyBilliardLast;

    public Billiards(String name, Integer status) {
        this.name = name;
        this.status = status;
    }

    @PostLoad
    public void onLoad() {
        if (Objects.nonNull(historyBilliards) && historyBilliards.size() > 0) {
            this.historyBilliardLast = AppUtil.getLastElement(this.historyBilliards);
        }
    }
}
