package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConstantRepository extends JpaRepository<Constant, Long> {
    
    Optional<Constant> findByUuid(UUID uuid);
    
    boolean existsByUuid(UUID uuid);
    
    List<Constant> findAllByValue(Double value);
    
    List<Constant> findByLabelContainingIgnoreCase(String label);
    
    List<Constant> findByProduct(UUID product);
    
    List<Constant> findByCoverage(UUID coverage);
    
    List<Constant> findByValueBetween(Double minValue, Double maxValue);
    
    boolean existsByVariableName(String variableName);
}
