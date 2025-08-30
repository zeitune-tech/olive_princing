package sn.zeitune.olive_insurance_pricing.app.entities.variableItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "constant")
public class Constant extends VariableItem {
    @Column(name = "value", nullable = false)
    private Double value;
}
