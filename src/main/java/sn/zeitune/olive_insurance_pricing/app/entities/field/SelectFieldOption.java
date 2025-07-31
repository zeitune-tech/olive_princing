package sn.zeitune.olive_insurance_pricing.app.entities.field;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.entities.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "champ_select_option")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "visibilite", discriminatorType = DiscriminatorType.STRING)
@ToString
public class SelectFieldOption extends BaseEntity {
    private String label;
    private String name;
    private String description;
    @OneToMany
    private List<SelectFieldOptionValue> possibilities = new ArrayList<>();
}
