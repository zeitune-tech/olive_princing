package sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "champ")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Field extends VariableItem {
}
