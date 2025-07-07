package sn.zeitune.olive_insurance_pricing.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "formula")
public class Formula extends VariableItem {

    @Column(name = "expression", nullable = false, columnDefinition = "TEXT")
    private String expression;

//    private List<VariableItem> variables;
}
