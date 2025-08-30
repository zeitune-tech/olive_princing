package sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.NumericField;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("condition_champ_numerique")
public class NumericCondition extends Condition {
    @ManyToOne
    private NumericField numericField;
    private NumericOperator numericOperator;
    private Double numericValue;
    private Double minimum;
    private Double maximum;
}