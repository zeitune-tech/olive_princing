package sn.zeitune.olive_insurance_pricing.app.clients;


import sn.zeitune.olive_insurance_pricing.app.dtos.externals.CoverageExternalDTO;

import java.util.List;
import java.util.UUID;

public interface VehicleSettingsClient {

    List<CoverageExternalDTO> getCoveragesByProduct(UUID productId, UUID managementEntityId);

}
