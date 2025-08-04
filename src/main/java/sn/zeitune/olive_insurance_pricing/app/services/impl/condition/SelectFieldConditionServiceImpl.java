package sn.zeitune.olive_insurance_pricing.app.services.impl.condition;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.SelectConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.SelectConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.mappers.condition.SelectConditionMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.condition.SelectConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldConditionService;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionValueService;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SelectFieldConditionServiceImpl implements SelectFieldConditionService {

    private final SelectConditionRepository selectConditionRepository;
    private final SelectFieldService selectFieldService;
    private final SelectFieldOptionValueService selectFieldOptionValueService;

    @Override
    public SelectConditionResponseDTO create(SelectConditionRequestDTO selectConditionRequestDTO) {

        SelectCondition selectCondition = SelectConditionMapper.map(selectConditionRequestDTO);

        SelectField selectField = selectFieldService.getEntityByUuid(selectConditionRequestDTO.getFieldId());
        if (selectField == null) throw new RuntimeException(String.format(""));

        SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueService.getEntityByUuid(selectConditionRequestDTO.getValue());
        if (selectFieldOptionValue == null) throw new RuntimeException(String.format("Field with id %s does not exist", selectConditionRequestDTO.getFieldId()));

        if (!selectField.getOptions().getPossibilities().stream().map(SelectFieldOptionValue::getUuid).toList().contains(selectConditionRequestDTO.getValue()))
            throw new RuntimeException(String.format("Value with id %s does not exist", selectConditionRequestDTO.getValue()));

        selectCondition.setSelectFieldValue(selectFieldOptionValueService.getEntityByUuid(selectConditionRequestDTO.getValue()));
        selectCondition.setSelectField(selectField);
        selectCondition = selectConditionRepository.save(selectCondition);

        return SelectConditionMapper.map(selectCondition);
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
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return selectConditionRepository.existsByUuid(uuid);
    }

    @Override
    public SelectCondition getEntityByUuid(UUID uuid) {
        return selectConditionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'ID : " + uuid));
    }
}
