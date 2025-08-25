package sn.zeitune.olive_insurance_pricing.app.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;

import java.util.List;
import java.util.UUID;

public interface RetrieveGenericService<V extends VariableItem, REQ, RES> {
    RES retrieve(UUID uuid, UUID managementEntity);
    List<RES> retrieveByProduct(UUID product, UUID managementEntity);
    List<RES> retrieveAll(UUID managementEntity);
    Page<RES> retrieveAll(UUID managementEntity, Pageable pageable);

    boolean exists(UUID uuid, UUID managementEntity);

    V getEntityByUuid(UUID uuid, UUID managementEntity);
    V getEntityByVariableName(String variableName, UUID coverage, PricingType pricingType, UUID managementEntity);
    List<V> getAllEntityByProduct(UUID product, UUID managementEntity);
    Page<V> getAllEntityByManagementEntity(UUID managementEntity, Pageable pageable);
    List<V> getAllEntityByManagementEntity(UUID managementEntity);
}
