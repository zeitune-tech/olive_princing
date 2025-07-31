package sn.zeitune.olive_insurance_pricing.app.repositories.field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SelectFieldRepository extends JpaRepository<SelectField, Long> {
    
    List<SelectField> findByLabelContainingIgnoreCase(String label);
    
    List<SelectField> findByProduct(UUID product);
    
    boolean existsByVariableName(String variableName);
    
    Optional<SelectField> findByUuid(UUID uuid);
    
    boolean existsByUuid(UUID uuid);
}
