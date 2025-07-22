package sn.zeitune.olive_insurance_pricing.app.entities.condition;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
@DiscriminatorValue("condition_champs_select")
public class SelectFieldCondition extends Condition {

    @OneToOne
    private SelectField selectField;
    private SelectFieldOperator selectFieldOperator;
    @OneToOne
    private SelectFieldOptionValue selectFieldValue;
}