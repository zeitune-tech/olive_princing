package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.GroupedFieldValue;

import java.util.List;

@Repository
public interface GroupedFieldValueRepository extends JpaRepository<GroupedFieldValue, Long> {
    
    List<GroupedFieldValue> findByGroupName(String groupName);
    
    List<GroupedFieldValue> findByGroupNameContainingIgnoreCase(String groupName);
    
    List<GroupedFieldValue> findByNameContainingIgnoreCase(String name);
    
    boolean existsByName(String name);
}
