package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.clients.AdministrationClient;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.mappers.ConstantMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.ConstantRepository;
import sn.zeitune.olive_insurance_pricing.app.services.ConstantService;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemPreparationService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ConstantServiceImpl extends RetrieveGenericServiceImpl<Constant, ConstantRequestDTO, ConstantResponseDTO> implements ConstantService {

    private final ConstantRepository constantRepository;
    private final VariableItemPreparationService variableItemPreparationService;
    private final ConstantMapper mapper;

    public ConstantServiceImpl(ConstantRepository constantRepository,
                               VariableItemPreparationService variableItemPreparationService
    ) {
        super(constantRepository, ConstantMapper.getInstance());
        this.constantRepository = constantRepository;
        this.variableItemPreparationService = variableItemPreparationService;
        this.mapper = ConstantMapper.getInstance();
    }

    @Override
    public ConstantResponseDTO create(ConstantRequestDTO constantRequestDTO, UUID managementEntity) {
        Constant constant = (Constant) variableItemPreparationService.prepareCreationEntity(constantRequestDTO, new Constant(), managementEntity);
        mapper.putRequestValue(constantRequestDTO, constant);
        return mapper.map(constantRepository.save(constant));
    }

    @Override
    public ConstantResponseDTO update(UUID uuid, ConstantRequestDTO constantRequestDTO, UUID managementEntity) {
        Constant existingConstant = constantRepository.findByUuidAndManagementEntity(uuid, managementEntity)
                .orElseThrow(() -> new EntityNotFoundException("Constante non trouvée avec l'UUID : " + uuid));

        existingConstant = (Constant) variableItemPreparationService.prepareUpdatingEntity(constantRequestDTO, existingConstant, managementEntity);
        mapper.putRequestValue(constantRequestDTO, existingConstant);
        return mapper.map(constantRepository.save(existingConstant));
    }

    @Override
    public void delete(UUID uuid, UUID managementEntity) {
        if (!exists(uuid, managementEntity))
            throw new EntityNotFoundException("Constante non trouvée avec l'UUID : " + uuid);
        constantRepository.findByUuidAndManagementEntity(uuid, managementEntity).ifPresent(
                constant -> {
                    constant.setPricingType(null);
                    constantRepository.delete(constant);
                }
        );
    }

    @Override
    public Constant getEntityByVariableName(String variableName, UUID coverage, PricingType pricingType, UUID managementEntity) {
        return null;
    }
}
