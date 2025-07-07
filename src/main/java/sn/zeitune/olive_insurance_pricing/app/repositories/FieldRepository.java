package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.Field;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    
    List<Field> findByType(FieldType type);
    
    List<Field> findByLabelContainingIgnoreCase(String label);
    
    List<Field> findByProduct(UUID product);
    
    List<Field> findByCoverage(UUID coverage);
    
    boolean existsByVariableName(String variableName);
    
    Optional<Field> findByUuid(UUID uuid);
    
    boolean existsByUuid(UUID uuid);
}
