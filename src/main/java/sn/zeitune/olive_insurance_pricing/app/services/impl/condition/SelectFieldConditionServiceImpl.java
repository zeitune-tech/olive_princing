package sn.zeitune.olive_insurance_pricing.app.services.impl.condition;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.SelectFieldConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.SelectFieldConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.SelectCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.mappers.condition.SelectFieldConditionMapper;
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
    public SelectFieldConditionResponseDTO create(SelectFieldConditionRequestDTO selectFieldConditionRequestDTO) {

        SelectCondition selectCondition = SelectFieldConditionMapper.map(selectFieldConditionRequestDTO);

        SelectField selectField = selectFieldService.getEntityByUuid(selectFieldConditionRequestDTO.fieldId());
        if (selectField == null) throw new RuntimeException(String.format(""));

        SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueService.getEntityByUuid(selectFieldConditionRequestDTO.value());
        if (selectFieldOptionValue == null) throw new RuntimeException(String.format("Field with id %s does not exist", selectFieldConditionRequestDTO.fieldId()));

        if (!selectField.getOptions().getPossibilities().stream().map(SelectFieldOptionValue::getUuid).toList().contains(selectFieldConditionRequestDTO.value()))
            throw new RuntimeException(String.format("Value with id %s does not exist", selectFieldConditionRequestDTO.value()));

        selectCondition.setSelectFieldValue(selectFieldOptionValueService.getEntityByUuid(selectFieldConditionRequestDTO.value()));
        selectCondition.setSelectField(selectField);
        selectCondition = selectConditionRepository.save(selectCondition);

        return SelectFieldConditionMapper.map(selectCondition);
    }

    @Override
    public SelectFieldConditionResponseDTO findById(Long id) {
        SelectCondition selectCondition = selectConditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'ID : " + id));
        return SelectFieldConditionMapper.map(selectCondition);
    }

    @Override
    public SelectFieldConditionResponseDTO findByUuid(UUID uuid) {
        return SelectFieldConditionMapper.map(getEntityByUuid(uuid));
    }

    @Override
    public List<SelectFieldConditionResponseDTO> findAll() {
        return selectConditionRepository.findAll()
                .stream()
                .map(SelectFieldConditionMapper::map)
                .toList();
    }

    @Override
    public Page<SelectFieldConditionResponseDTO> findAll(Pageable pageable) {
        return selectConditionRepository.findAll(pageable)
                .map(SelectFieldConditionMapper::map);
    }

    @Override
    public List<SelectFieldConditionResponseDTO> findByValue(SelectFieldOptionValue value) {
        return selectConditionRepository.findBySelectFieldValue(value)
                .stream()
                .map(SelectFieldConditionMapper::map)
                .toList();
    }


    @Override
    public List<SelectFieldConditionResponseDTO> findByField(Long fieldId) {
        return selectConditionRepository.findBySelectFieldId(fieldId)
                .stream()
                .map(SelectFieldConditionMapper::map)
                .toList();
    }

    @Override
    public SelectFieldConditionResponseDTO update(Long id, SelectFieldConditionRequestDTO selectFieldConditionRequestDTO) {
        SelectCondition existingSelectCondition = selectConditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'ID : " + id));
        
        SelectFieldConditionMapper.map(selectFieldConditionRequestDTO, existingSelectCondition);
        SelectCondition updatedSelectCondition = selectConditionRepository.save(existingSelectCondition);
        return SelectFieldConditionMapper.map(updatedSelectCondition);
    }

    @Override
    public SelectFieldConditionResponseDTO updateByUuid(UUID uuid, SelectFieldConditionRequestDTO selectFieldConditionRequestDTO) {
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
    }

    @Override
    public void delete(Long id) {
        if (!selectConditionRepository.existsById(id)) {
            throw new EntityNotFoundException("Condition non trouvée avec l'ID : " + id);
        }
        selectConditionRepository.deleteById(id);
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
