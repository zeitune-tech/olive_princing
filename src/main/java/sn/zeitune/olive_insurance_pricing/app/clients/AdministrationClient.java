package sn.zeitune.olive_insurance_pricing.app.clients;


import sn.zeitune.olive_insurance_pricing.app.dtos.externals.ManagementEntityExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.ProductExternalDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AdministrationClient {

    List<ProductExternalDTO> getByManagementEntity(UUID uuid);
    List<ManagementEntityExternalDTO> getManagementEntities(List<UUID> uuids);

    List<ProductExternalDTO> getProductsByIds(List<UUID> uuids);

    Optional<ManagementEntityExternalDTO> findManagementEntityByUuid(UUID ownerUuid);

    List<ManagementEntityExternalDTO> findManagementEntityByUuidIn(Set<UUID> companyUuids);
}
