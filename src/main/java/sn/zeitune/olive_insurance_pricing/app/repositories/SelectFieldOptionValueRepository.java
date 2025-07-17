package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.SelectFieldOptionValue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SelectFieldOptionValueRepository extends JpaRepository<SelectFieldOptionValue, Long> {

    Optional<SelectFieldOptionValue> findByUuid(UUID uuid);

    Optional<SelectFieldOptionValue> findByName(String name);
    
    List<SelectFieldOptionValue> findByNameContainingIgnoreCase(String name);

    List<SelectFieldOptionValue> findByGroup(String group);

    List<SelectFieldOptionValue> findByLabelContainingIgnoreCase(String label);

    boolean existsByName(String name);

    boolean existsByUuid(UUID uuid);
}