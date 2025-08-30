package sn.zeitune.olive_insurance_pricing.app.repositories.variableItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericRepository<V extends VariableItem> extends JpaRepository<V, Long> {

    Optional<V> findByUuidAndDeletedIsFalse(UUID variableItemId);
    Optional<V> findByVariableNameAndManagementEntityAndPricingType_UuidAndCoverageAndDeletedIsFalse(String variableName, UUID managementEntity, UUID PricingType_Uuid, UUID coverage);


    List<V> findAllByPricingType_UuidAndManagementEntityAndDeletedIsFalse(UUID pricingTypeId, UUID managementEntity);
//    List<V> findAllByProductAndManagementEntityAndDeletedIsFalse(UUID product, UUID managementEntity);


    List<V> findAllByManagementEntityAndDeletedIsFalse(UUID managementEntity);
    Page<V> findAllByManagementEntityAndDeletedIsFalse(UUID managementEntity, Pageable pageable);
}
