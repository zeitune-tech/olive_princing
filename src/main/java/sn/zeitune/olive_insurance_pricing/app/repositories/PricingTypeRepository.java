package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PricingTypeRepository extends JpaRepository<PricingType, UUID> {
    Page<PricingType> findAllByManagementEntityAndDeletedIsFalse(UUID managementEntity, Pageable pageable);
    List<PricingType> findAllByManagementEntityAndProductAndDeletedIsFalse(UUID managementEntity, UUID product);
    boolean existsByNameAndProductAndDeletedIsFalse(String name, UUID productId);

    boolean existsByUuid(UUID uuid);
    void deleteByUuid(UUID id);

    Optional<PricingType> findByUuidAndManagementEntityAndDeletedIsFalse(UUID id, UUID managementEntity);
    Optional<PricingType> findByManagementEntityAndProductAndEffectiveIsTrue(UUID managementEntity, UUID productId);
}
