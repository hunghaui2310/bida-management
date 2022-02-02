package com.bida.management.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "tbl_provider")
@Getter
@Setter
@NoArgsConstructor
public class Provider implements Serializable {

    private static final long serialVersionUID = 8L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "address")
    private String address;

    @Size(max = 10000)
    @Column(name = "note", length = 10000)
    private String note;

    @Size(max = 50)
    @Column(name = "phone_number", length = 50, nullable = false, unique = true)
    private String phoneNumber;

    @NotNull
    @Column(name = "status", nullable = false)
    private int status;

    @OneToMany(mappedBy = "provider")
    private Set<HistoryProduct> historyProducts;

    private transient String description;

    @PostLoad
    public void onLoad() {
        this.description = this.name;
        if (StringUtils.isNotBlank(this.note) && Objects.nonNull(this.note)) {
            this.description += "(" + this.note + ")";
        }
    }
}
