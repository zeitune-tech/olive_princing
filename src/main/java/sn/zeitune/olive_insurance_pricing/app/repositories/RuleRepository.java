package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.Rule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
    
    Optional<Rule> findByUuid(UUID uuid);
    
    boolean existsByUuid(UUID uuid);
    
    List<Rule> findByValue(Double value);
    
    List<Rule> findByLabelContainingIgnoreCase(String label);
    
    List<Rule> findByProduct(UUID product);
    
    List<Rule> findByCoverage(UUID coverage);
    
    List<Rule> findByValueBetween(Double minValue, Double maxValue);
    
    boolean existsByVariableName(String variableName);
}
