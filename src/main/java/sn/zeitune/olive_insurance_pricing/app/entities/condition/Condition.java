package sn.zeitune.olive_insurance_pricing.app.entities.condition;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "condition")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Condition {

/*
    TODO:
     * créer des classes spécialisées qui s'occuperons des conditions pour les champs numériques et de type select
        Pour le champs numérique, les contraintes seront gérés avec min-max donc nous rajouterons ces attributs à la place de value
        Pour le champs de selection, les deux opérations permisent sont l'égalité et la difference, dans ce cas un champs de type SelectFieldOptionValue
     * pour gérer le ou dans les conditions, pensé à une approche de composition des Conditions
 */

    @Id
    @Column(name = "code_condition")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @PrePersist
    public void generateUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }
}
