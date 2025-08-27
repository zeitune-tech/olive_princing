package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericRepository<V extends VariableItem> extends JpaRepository<V, Long> {

    boolean existsByUuidAndManagementEntity(UUID uuid, UUID managementEntity);
    Optional<V> findByUuidAndManagementEntity(UUID uuid, UUID managementEntity);

    boolean existsByVariableNameAndManagementEntity(String variableName, UUID managementEntity);
    Optional<V> findByManagementEntityAndPricingType_UuidAndCoverageAndVariableName(UUID managementEntity, UUID PricingType_Uuid, UUID coverage, String variableName);

    Page<V> findAllByManagementEntity(UUID managementEntity, Pageable pageable);
    List<V> findAllByManagementEntity(UUID managementEntity);
    List<V> findAllByPricingTypeAndManagementEntity(PricingType pricingType, UUID managementEntity);
}
