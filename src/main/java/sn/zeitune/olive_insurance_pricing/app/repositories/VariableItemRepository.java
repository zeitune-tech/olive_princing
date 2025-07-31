package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VariableItemRepository extends JpaRepository<VariableItem, Long> {

    List<VariableItem> findAllByOrderByLabelAsc();

    Optional<VariableItem> findByUuid(UUID uuid);
    
    List<VariableItem> findByLabelContainingIgnoreCase(String label);
    
    Optional<VariableItem> findByVariableName(String variableName);
    
    List<VariableItem> findByProduct(UUID product);
    
    List<VariableItem> findByCoverage(UUID coverage);
    
    List<VariableItem> findByToReturn(Boolean toReturn);
    
    boolean existsByUuid(UUID uuid);
    
    boolean existsByVariableName(String variableName);

}
