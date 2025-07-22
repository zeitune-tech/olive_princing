package sn.zeitune.olive_insurance_pricing.app.repositories.condition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectFieldCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;

import java.util.List;

@Repository
public interface SelectFieldConditionRepository extends JpaRepository<SelectFieldCondition, Long> {

    List<SelectFieldCondition> findBySelectFieldValue(SelectFieldOptionValue value);

    List<SelectFieldCondition> findBySelectField(SelectField field);
    
    List<SelectFieldCondition> findBySelectFieldId(Long fieldId);
}
