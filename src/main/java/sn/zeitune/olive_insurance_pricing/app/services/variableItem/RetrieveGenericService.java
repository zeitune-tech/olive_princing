package sn.zeitune.olive_insurance_pricing.app.services.variableItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;

import java.util.List;
import java.util.UUID;

public interface RetrieveGenericService<V extends VariableItem, REQ, RES> {
    RES retrieveActive(UUID uuid, UUID managementEntity);
//    List<RES> retrieveAllActiveByProduct(UUID product, UUID managementEntity, UUID pricingTypeId, UUID coverage);
    List<RES> retrieveAllActive(UUID managementEntity);
    Page<RES> retrieveAllActive(UUID managementEntity, Pageable pageable);
}
