package sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.variableCondition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectFieldOptionValue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SelectConditionRepository extends JpaRepository<SelectCondition, Long> {

    List<SelectCondition> findBySelectFieldValue(SelectFieldOptionValue value);

    List<SelectCondition> findBySelectField(SelectField field);
    
    List<SelectCondition> findBySelectFieldId(Long fieldId);

    Optional<SelectCondition> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
