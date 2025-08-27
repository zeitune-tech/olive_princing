package sn.zeitune.olive_insurance_pricing.app.repositories.field;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    boolean existsByVariableName(String variableName);
    
    Optional<NumericField> findByUuid(UUID uuid);
    
    boolean existsByUuid(UUID uuid);
    
    Page<NumericField> findAllByManagementEntity(UUID managementEntity, Pageable pageable);
}
