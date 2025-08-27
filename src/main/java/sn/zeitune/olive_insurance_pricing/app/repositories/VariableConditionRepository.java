package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VariableConditionRepository extends JpaRepository<VariableCondition, Long> {
    
    Optional<VariableCondition> findByUuid(UUID uuid);
    
    Page<VariableCondition> findAllByManagementEntity(UUID managementEntity, Pageable pageable);
    
    boolean existsByUuid(UUID uuid);
    
    List<VariableCondition> findByLabelContainingIgnoreCase(String label);
    
    List<VariableCondition> findByProduct(UUID product);
    
    boolean existsByVariableName(String variableName);
}
