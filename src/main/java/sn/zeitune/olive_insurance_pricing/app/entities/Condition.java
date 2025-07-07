package sn.zeitune.olive_insurance_pricing.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "condition")
public class Condition {

    @Id
    @Column(name = "code_condition")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    @OneToOne
    private Field field;
    private Operator operator;

}
