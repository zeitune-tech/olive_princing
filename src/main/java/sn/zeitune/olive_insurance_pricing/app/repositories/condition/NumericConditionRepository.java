package sn.zeitune.olive_insurance_pricing.app.repositories.condition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NumericConditionRepository extends JpaRepository<NumericCondition, Long> {

    List<NumericCondition> findByNumericField(NumericField field);

    Optional<NumericCondition> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
