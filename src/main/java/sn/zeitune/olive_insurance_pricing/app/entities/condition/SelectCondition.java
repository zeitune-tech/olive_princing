package sn.zeitune.olive_insurance_pricing.app.entities.condition;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.enums.SelectFieldOperator;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("condition_champ_select")
public class SelectCondition extends Condition {
    @ManyToOne
    private SelectField selectField;
    private SelectFieldOperator selectFieldOperator;
    @ManyToOne
    private SelectFieldOptionValue selectFieldValue;
}