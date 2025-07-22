package sn.zeitune.olive_insurance_pricing.app.repositories.field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SelectFieldOptionsRepository extends JpaRepository<SelectFieldOptions, Long> {

    Optional<SelectFieldOptions> findByName(String name);

    Optional<SelectFieldOptions> findByUuid(UUID id);
    
    List<SelectFieldOptions> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
