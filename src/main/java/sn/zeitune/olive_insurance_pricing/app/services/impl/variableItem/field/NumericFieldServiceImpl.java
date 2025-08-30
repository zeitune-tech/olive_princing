package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.field;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field.NumericFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.field.NumericFieldRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.field.NumericFieldService;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.VariableItemPreparationService;
import sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.RetrieveGenericServiceImpl;
import sn.zeitune.olive_insurance_pricing.app.utils.ErrorManager;

import java.util.UUID;

@Service
@Transactional
public class NumericFieldServiceImpl extends RetrieveGenericServiceImpl<NumericField, NumericFieldRequestDTO, NumericFieldResponseDTO> implements NumericFieldService {

    private final NumericFieldRepository numericFieldRepository;
    private final VariableItemPreparationService variableItemPreparationService;

    public NumericFieldServiceImpl(NumericFieldRepository numericFieldRepository,
                                   VariableItemPreparationService variableItemPreparationService
    ) {
        super(numericFieldRepository, NumericFieldMapper.getInstance());
        this.numericFieldRepository = numericFieldRepository;
        this.variableItemPreparationService = variableItemPreparationService;
    }

    @Override
    public NumericFieldResponseDTO create(NumericFieldRequestDTO numericFieldRequestDTO, UUID managementEntity) {
        NumericField fieldSaving = (NumericField) variableItemPreparationService.prepareCreationEntity(numericFieldRequestDTO, new NumericField(), managementEntity);
        NumericFieldMapper.getInstance().putRequestValue(numericFieldRequestDTO, fieldSaving);
        return NumericFieldMapper.getInstance().map(numericFieldRepository.save(fieldSaving));
    }

    @Override
    public NumericFieldResponseDTO update(UUID uuid, NumericFieldRequestDTO numericFieldRequestDTO, UUID managementEntity) {
        NumericField existingField = numericFieldRepository.findByUuidAndDeletedIsFalse(uuid)
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(NumericField.class, "id: "+uuid));
        existingField = (NumericField) variableItemPreparationService.prepareUpdatingEntity(numericFieldRequestDTO, existingField, existingField.getManagementEntity());
        NumericFieldMapper.getInstance().putRequestValue(numericFieldRequestDTO, existingField);
        return NumericFieldMapper.getInstance().map(numericFieldRepository.save(existingField));
    }

    @Override
    public void delete(UUID uuid, UUID managementEntity) {
        NumericField field = numericFieldRepository.findByUuidAndDeletedIsFalse(uuid)
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(NumericField.class, "id: "+uuid));
        field.setPricingType(null);
        numericFieldRepository.delete(field);
    }

}