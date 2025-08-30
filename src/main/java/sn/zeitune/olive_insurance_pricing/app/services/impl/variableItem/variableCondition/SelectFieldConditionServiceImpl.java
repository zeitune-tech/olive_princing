package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.variableCondition;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.SelectConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.SelectConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.variableCondition.SelectConditionMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.field.SelectFieldOptionValueRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.field.SelectFieldRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.variableCondition.SelectConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.variableCondition.SelectFieldConditionService;
import sn.zeitune.olive_insurance_pricing.app.utils.ErrorManager;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SelectFieldConditionServiceImpl implements SelectFieldConditionService {

    private final SelectConditionRepository selectConditionRepository;
    private final SelectFieldRepository selectFieldRepository;
    private final SelectFieldOptionValueRepository selectFieldOptionValueRepository;

    @Override
    public SelectConditionResponseDTO create(SelectConditionRequestDTO selectConditionRequestDTO) {
        SelectCondition selectCondition = SelectConditionMapper.map(selectConditionRequestDTO);
        SelectField selectField = selectFieldRepository.findByUuidAndDeletedIsFalse(selectConditionRequestDTO.getFieldId()).orElseThrow(() -> ErrorManager.sayEntityNotFound(SelectField.class, "id: "+selectConditionRequestDTO.getFieldId()));
        List<UUID> possibleValues = selectField.getOptions().getPossibilities().stream().map(SelectFieldOptionValue::getUuid).toList();
        if (!possibleValues.contains(selectConditionRequestDTO.getValue()))
            throw new RuntimeException(String.format("Value with id %s does not exist", selectConditionRequestDTO.getValue()));
        selectCondition.setSelectFieldValue(getSelectFieldOptionValueEntityByUuid(selectConditionRequestDTO.getValue()));
        selectCondition.setSelectField(selectField);
        return SelectConditionMapper.map(selectConditionRepository.save(selectCondition));
    }

    @Override
    public SelectConditionResponseDTO findByUuid(UUID uuid) {
        return SelectConditionMapper.map(getEntityByUuid(uuid));
    }

    @Override
    public List<SelectConditionResponseDTO> findAll() {
        return selectConditionRepository.findAll()
                .stream()
                .map(SelectConditionMapper::map)
                .toList();
    }

    @Override
    public Page<SelectConditionResponseDTO> findAll(Pageable pageable) {
        return selectConditionRepository.findAll(pageable)
                .map(SelectConditionMapper::map);
    }

    @Override
    public List<SelectConditionResponseDTO> findByValue(SelectFieldOptionValue value) {
        return selectConditionRepository.findBySelectFieldValue(value)
                .stream()
                .map(SelectConditionMapper::map)
                .toList();
    }

    @Override
    public SelectConditionResponseDTO updateByUuid(UUID uuid, SelectConditionRequestDTO selectConditionRequestDTO) {
        SelectCondition existingSelectCondition = SelectConditionMapper.map(selectConditionRequestDTO, getEntityByUuid(uuid));
        SelectField selectField = selectFieldRepository.findByUuidAndDeletedIsFalse(selectConditionRequestDTO.getFieldId()).orElseThrow(() -> ErrorManager.sayEntityNotFound(SelectField.class, "id: "+selectConditionRequestDTO.getFieldId()));
        if (!selectField.getOptions().getPossibilities().stream().map(SelectFieldOptionValue::getUuid).toList().contains(selectConditionRequestDTO.getValue()))
            throw new RuntimeException(String.format("Value with id %s does not exist", selectConditionRequestDTO.getValue()));
        existingSelectCondition.setSelectFieldValue(getSelectFieldOptionValueEntityByUuid(selectConditionRequestDTO.getValue()));
        existingSelectCondition.setSelectField(selectField);
        return SelectConditionMapper.map(selectConditionRepository.save(existingSelectCondition));
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        selectConditionRepository.deleteByUuid(uuid);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return selectConditionRepository.existsByUuid(uuid);
    }


    SelectCondition getEntityByUuid(UUID uuid) {
        return selectConditionRepository.findByUuid(uuid)
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(SelectCondition.class, "uuid: " + uuid));

    }

    SelectFieldOptionValue getSelectFieldOptionValueEntityByUuid(UUID uuid) {
        return selectFieldOptionValueRepository.findByUuid(uuid)
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(SelectFieldOptionValue.class, "uuid: " + uuid));
    }
}
