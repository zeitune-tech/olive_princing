package sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.entities.BaseEntity;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "champ_select_option_value")
public class SelectFieldOptionValue extends BaseEntity {
    private String label;
    private String name;
    @Column(name="classe")
    private String group;
}
