package sn.zeitune.olive_insurance_pricing.app.entities.condition;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.enums.NumericOperator;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("condition_numerique")
public class NumericalCondition extends Condition {

    @OneToOne
    private NumericField numericField;
    private NumericOperator numericOperator;
    private Double numericValue;
    private Double minimum;
    private Double maximum;
}