package sn.zeitune.olive_insurance_pricing.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "field")
public class Field extends VariableItem {

    private FieldType type;
    @OneToOne
    private FieldValue value;
}
