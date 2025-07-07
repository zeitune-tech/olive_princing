package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.FieldValue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FieldValueRepository extends JpaRepository<FieldValue, Long> {

    Optional<FieldValue> findByName(String name);
    
    List<FieldValue> findByNameContainingIgnoreCase(String name);
    
    List<FieldValue> findByValue(String value);
    
    boolean existsByName(String name);
}
