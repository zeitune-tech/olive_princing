package sn.zeitune.olive_insurance_pricing.app.repositories.condition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericalCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NumericalConditionRepository extends JpaRepository<NumericalCondition, Long> {
    
    List<NumericalCondition> findByNumericFieldValue(Double value);

    List<NumericalCondition> findByNumericField(NumericField field);

    Optional<NumericalCondition> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
