package sn.zeitune.olive_insurance_pricing.app.services;

import java.util.UUID;

public interface CUDGenericService<V, REQ, RES> {
    RES create(REQ dto, UUID managementEntity);
    RES update(UUID uuid, REQ dto, UUID managementEntity);
    void delete(UUID uuid, UUID managementEntity);
}
