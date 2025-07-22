package sn.zeitune.olive_insurance_pricing.app.repositories.field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NumericFieldRepository extends JpaRepository<NumericField, Long> {
    
    List<NumericField> findByLabelContainingIgnoreCase(String label);
    
    List<NumericField> findByProduct(UUID product);
    
    List<NumericField> findByCoverage(UUID coverage);
    
    boolean existsByVariableName(String variableName);
    
    Optional<NumericField> findByUuid(UUID uuid);
    
    boolean existsByUuid(UUID uuid);
}
