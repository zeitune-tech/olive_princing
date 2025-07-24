package sn.zeitune.olive_insurance_pricing.app.repositories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    boolean existsByName(@NotNull(message = "Name is required") @NotBlank(message = "Name must not be blank") String name);
}
