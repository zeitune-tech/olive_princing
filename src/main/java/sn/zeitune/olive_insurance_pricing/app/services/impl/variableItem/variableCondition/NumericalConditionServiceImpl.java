package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.variableCondition;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.variableCondition.NumericConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.variableCondition.NumericConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.variableCondition.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.variableCondition.NumericConditionMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.variableCondition.NumericConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.field.NumericFieldRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.variableCondition.NumericalConditionService;
import sn.zeitune.olive_insurance_pricing.app.utils.ErrorManager;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NumericalConditionServiceImpl implements NumericalConditionService {

    private final NumericConditionRepository numericConditionRepository;
    private final NumericFieldRepository numericFieldRepository;

    @Override
    public NumericConditionResponseDTO create(NumericConditionRequestDTO numericConditionRequestDTO) {
        NumericCondition numericCondition = NumericConditionMapper.map(numericConditionRequestDTO);
        NumericField numericField = numericFieldRepository.findByUuidAndDeletedIsFalse(numericConditionRequestDTO.getFieldId())
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(NumericField.class, "id: " + numericConditionRequestDTO.getFieldId()));
        numericCondition.setNumericField(numericField);
        return NumericConditionMapper.map(numericConditionRepository.save(numericCondition));
    }

    @Override
    public NumericConditionResponseDTO findByUuid(UUID uuid) {
        return NumericConditionMapper.map(numericConditionRepository.findByUuid(
                uuid
        ).orElseThrow(() -> ErrorManager.sayEntityNotFound(NumericCondition.class, "uuid: " + uuid)
        ));
    }

    @Override
    public List<NumericConditionResponseDTO> findAll() {
        return numericConditionRepository.findAll()
                .stream()
                .map(NumericConditionMapper::map)
                .toList();
    }

    @Override
    public Page<NumericConditionResponseDTO> findAll(Pageable pageable) {
        return numericConditionRepository.findAll(pageable)
                .map(NumericConditionMapper::map);
    }

    @Override
    public NumericConditionResponseDTO updateByUuid(UUID uuid, NumericConditionRequestDTO numericConditionRequestDTO) {
        NumericCondition existingNumericCondition = numericConditionRepository.findByUuid(uuid)
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(NumericCondition.class, "uuid: " + uuid)
        );
        NumericConditionMapper.map(numericConditionRequestDTO, existingNumericCondition);
        if (existingNumericCondition.getNumericField().getUuid() != numericConditionRequestDTO.getFieldId())
            existingNumericCondition.setNumericField(
                    numericFieldRepository.findByUuidAndDeletedIsFalse(numericConditionRequestDTO.getFieldId())
                            .orElseThrow(() -> ErrorManager.sayEntityNotFound(NumericField.class, "id: " + numericConditionRequestDTO.getFieldId()))
            );
        return NumericConditionMapper.map(numericConditionRepository.save(existingNumericCondition));
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        numericConditionRepository.delete(
                numericConditionRepository.findByUuid(uuid)
                        .orElseThrow(() -> ErrorManager.sayEntityNotFound(NumericCondition.class, "uuid: " + uuid))
        );
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return numericConditionRepository.existsByUuid(uuid);
    }

}
