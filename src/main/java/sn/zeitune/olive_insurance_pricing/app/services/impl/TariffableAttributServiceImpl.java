package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.clients.AdministrationClient;
import sn.zeitune.olive_insurance_pricing.app.clients.VehicleExploitationClient;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.TariffableAttributExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.TariffableAttributResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.TariffableObjectResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.mappers.ConstantMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.TariffableAttributMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.TariffableObjectMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.ConstantRepository;
import sn.zeitune.olive_insurance_pricing.app.services.ConstantService;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;
import sn.zeitune.olive_insurance_pricing.app.services.TariffableAttributService;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import javax.naming.ldap.HasControls;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TariffableAttributServiceImpl implements TariffableAttributService {

    private final VehicleExploitationClient vehicleExploitationClient;

    @Override
    public List<TariffableObjectResponseDTO> getAllTariffableObjects() {
        Map<String, TariffableObjectResponseDTO> map = new HashMap<>();
        for (TariffableAttributExternalDTO dto : vehicleExploitationClient.getAllTariffableAttribute()) {
            String type = dto.entite();
            if (!map.containsKey(type))
                map.put(type, TariffableObjectMapper.map(dto));
            else
                map.get(type).getAttributs().add(TariffableAttributMapper.map(dto));
        }
        return new ArrayList<>(map.values());
    }
}
