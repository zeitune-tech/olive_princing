package sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("champ_select")
@ToString
public class SelectField extends Field {
    @ManyToOne
    private SelectFieldOption options;
//    @ManyToOne
//    private SelectFieldOptionValue value;
}
