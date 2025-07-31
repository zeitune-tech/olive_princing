package sn.zeitune.olive_insurance_pricing.app.repositories.field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOption;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SelectFieldOptionRepository extends JpaRepository<SelectFieldOption, Long> {

    Optional<SelectFieldOption> findByName(String name);

    Optional<SelectFieldOption> findByUuid(UUID id);
    
    List<SelectFieldOption> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}
