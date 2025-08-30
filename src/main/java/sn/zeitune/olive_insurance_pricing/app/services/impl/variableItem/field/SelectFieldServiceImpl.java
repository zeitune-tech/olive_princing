package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.field;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectFieldOption;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field.SelectFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.field.SelectFieldOptionRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.field.SelectFieldRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.field.SelectFieldService;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.VariableItemPreparationService;
import sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.RetrieveGenericServiceImpl;
import sn.zeitune.olive_insurance_pricing.app.utils.ErrorManager;

import java.util.UUID;

@Service
@Transactional
@Slf4j
public class SelectFieldServiceImpl extends RetrieveGenericServiceImpl<SelectField, SelectFieldRequestDTO, SelectFieldResponseDTO> implements SelectFieldService {

    private final SelectFieldRepository selectFieldRepository;
    private final SelectFieldOptionRepository selectFieldOptionRepository;
    private final VariableItemPreparationService variableItemPreparationService;
    private final SelectFieldMapper mapper;

    public SelectFieldServiceImpl(SelectFieldRepository selectFieldRepository,
                                   VariableItemPreparationService variableItemPreparationService,
                                      SelectFieldOptionRepository selectFieldOptionRepository
    ) {
        super(selectFieldRepository, SelectFieldMapper.getInstance());
        this.selectFieldRepository = selectFieldRepository;
        this.mapper = SelectFieldMapper.getInstance();
        this.variableItemPreparationService = variableItemPreparationService;
        this.selectFieldOptionRepository = selectFieldOptionRepository;
    }

    @Override
    public SelectFieldResponseDTO create(SelectFieldRequestDTO selectFieldRequestDTO, UUID managementEntity) {
        SelectFieldOption selectFieldOption = selectFieldOptionRepository.findByUuid(selectFieldRequestDTO.getOptions()).orElseThrow(() -> ErrorManager.sayEntityNotFound(SelectFieldOption.class, "id: " + selectFieldRequestDTO.getOptions()));
        SelectField selectFieldSaving = (SelectField) variableItemPreparationService.prepareCreationEntity(selectFieldRequestDTO, new SelectField(), managementEntity);
        mapper.putRequestValue(selectFieldRequestDTO, selectFieldSaving);
        selectFieldSaving.setOptions(selectFieldOption);
        return mapper.map(selectFieldRepository.save(selectFieldSaving));
    }

    @Override
    public SelectFieldResponseDTO update(UUID uuid, SelectFieldRequestDTO selectFieldRequestDTO, UUID managementEntity) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void delete(UUID uuid, UUID managementEntity) {
        SelectField field = selectFieldRepository.findByUuidAndDeletedIsFalse(uuid)
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(SelectField.class, "id: " + uuid));
        field.setPricingType(null);
        selectFieldRepository.delete(field);
    }

}
