package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;

import java.util.List;
import java.util.UUID;

@Repository
public interface VariableConditionRepository extends JpaRepository<VariableCondition, Long> {
    
    List<VariableCondition> findByLabelContainingIgnoreCase(String label);
    
    List<VariableCondition> findByProduct(UUID product);
    
    List<VariableCondition> findByCoverage(UUID coverage);
    
    boolean existsByVariableName(String variableName);
}
