package sn.zeitune.olive_insurance_pricing.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Entity(name = "formula")
@NoArgsConstructor
@AllArgsConstructor
public class Formula extends VariableItem {

    @Column(name = "expression", nullable = false, columnDefinition = "TEXT")
    private String expression;



}
