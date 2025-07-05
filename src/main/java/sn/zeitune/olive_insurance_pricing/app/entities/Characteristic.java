package sn.zeitune.olive_insurance_pricing.app.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import sn.zeitune.olive_insurance_pricing.enums.CharacteristicScope;
import sn.zeitune.olive_insurance_pricing.enums.CharacteristicType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity(name = "caracteristique")
@NoArgsConstructor
@AllArgsConstructor
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "code_caracteristique")
    private Long id;

    @Column(name = "uuid", updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @PrePersist
    public void generateUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CharacteristicType type;

    @Column(name = "unite")
    private String unit;

    @ElementCollection
    @CollectionTable(
            name = "caracteristique_options",
            joinColumns = @JoinColumn(name = "code_caracteristique")
    )
    @Column(name = "valeur")
    private List<String> options;

    @Column(name = "valeur_min")
    private Double minValue;

    @Column(name = "valeur_max")
    private Double maxValue;

    @Column(name = "valeur_defaut")
    private String defaultValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope")
    private CharacteristicScope scope;

    @Column(name = "date_debut")
    private LocalDate startDate;

    @Column(name = "date_fin")
    private LocalDate endDate;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "table_column")
    private String tableColumn;
}
