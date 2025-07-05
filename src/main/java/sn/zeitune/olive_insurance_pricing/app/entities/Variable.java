package sn.zeitune.olive_insurance_pricing.app.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import sn.zeitune.olive_insurance_pricing.enums.NatureVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity(name = "section")
@NoArgsConstructor
@AllArgsConstructor

public class Variable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @PrePersist
    public void generateUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "label", nullable = false)
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(name = "nature", nullable = false)
    private NatureVariable nature;

    @Column(name = "formula", columnDefinition = "TEXT")
    private String formula;   

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "printable")
    private Boolean printable;

    @Column(name = "saveable")
    private Boolean saveable;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Condition> conditions;
}
