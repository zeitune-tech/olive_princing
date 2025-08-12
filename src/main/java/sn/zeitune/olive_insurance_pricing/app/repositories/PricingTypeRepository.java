package sn.zeitune.olive_insurance_pricing.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;

import java.util.List;
import java.util.UUID;

@Repository
public interface PricingTypeRepository extends JpaRepository<PricingType, UUID> {
    List<PricingType> findByProduct(UUID productId);
    boolean existsByNameAndProduct(String name, UUID productId);
}
