package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Constant;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.ConstantMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.ConstantRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.ConstantService;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.VariableItemPreparationService;

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
        Constant existingConstant = constantRepository.findByUuidAndDeletedIsFalse(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Constant with " + uuid + " not found"));

        existingConstant = (Constant) variableItemPreparationService.prepareUpdatingEntity(constantRequestDTO, existingConstant, managementEntity);
        mapper.putRequestValue(constantRequestDTO, existingConstant);
        return mapper.map(constantRepository.save(existingConstant));
    }

    @Override
    public void delete(UUID uuid, UUID managementEntity) {
        constantRepository.findByUuidAndDeletedIsFalse(uuid).ifPresent(
                constant -> {
                    constant.setPricingType(null);
                    constantRepository.delete(constant);
                }
        );
    }

}
