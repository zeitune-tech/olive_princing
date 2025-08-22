package sn.zeitune.olive_insurance_pricing.app.services;

import sn.zeitune.olive_insurance_pricing.app.dtos.responses.TariffableObjectResponseDTO;

import java.util.List;

public interface TariffableAttributService {
    /**
     * Retrieves all tariffable objects.
     *
     * @return a list of tariffable objects.
     */
    List<TariffableObjectResponseDTO> getAllTariffableObjects();
}
