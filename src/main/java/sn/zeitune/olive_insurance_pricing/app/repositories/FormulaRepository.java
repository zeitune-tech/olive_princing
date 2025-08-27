package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormulaRepository extends JpaRepository<Formula, Long> {
    
    List<Formula> findByExpressionContainingIgnoreCase(String expression);
    
    List<Formula> findByLabelContainingIgnoreCase(String label);
    
    List<Formula> findByProduct(UUID product);

    boolean existsByVariableName(String variableName);
    
    Optional<Formula> findByUuid(UUID uuid);
    
    boolean existsByUuid(UUID uuid);
}
