package sn.zeitune.olive_insurance_pricing.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import sn.zeitune.olive_insurance_pricing.enums.LogicalOperator;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

import java.util.UUID;

@Data
@Builder
@Entity(name = "condition")
@NoArgsConstructor
@AllArgsConstructor
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @PrePersist
    public void generateUuid() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }

    /**
     * Left operand of the condition (e.g. the code of the Characteristic)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "characteristic_id")
    private Characteristic leftOperand;


    /**
     * Operator (e.g. =, >, <, etc.)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "operator", nullable = false)
    private Operator operator;

    /**
     * Right operand value (e.g. "URBAN" or numeric value as String)
     */
    @Column(name = "right_operand", nullable = false)
    private String rightOperand;

    /**
     * Logical operator chaining this condition to the next one
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "logical_operator")
    private LogicalOperator logicalOperator;

    /**
     * Order of the condition in the list of conditions
     */
    int order;

    /**
     * Section to which this condition belongs
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variable_id", nullable = false)
    private Variable variable;
}
