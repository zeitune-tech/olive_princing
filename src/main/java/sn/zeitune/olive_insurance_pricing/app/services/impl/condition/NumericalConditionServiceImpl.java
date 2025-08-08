package sn.zeitune.olive_insurance_pricing.app.services.impl.condition;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.NumericConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.NumericConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.condition.NumericConditionMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.condition.NumericConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.NumericFieldService;
import sn.zeitune.olive_insurance_pricing.app.services.NumericalConditionService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NumericalConditionServiceImpl implements NumericalConditionService {

    private final NumericConditionRepository numericConditionRepository;
    private final NumericFieldService numericFieldService;

    @Override
    public NumericConditionResponseDTO create(NumericConditionRequestDTO numericConditionRequestDTO) {
        NumericCondition numericCondition = NumericConditionMapper.map(numericConditionRequestDTO);

        NumericField numericField = numericFieldService.getEntityByUuid(numericConditionRequestDTO.getFieldId());
        if (numericField == null) throw new RuntimeException(String.format(""));

        numericCondition.setNumericField(numericField);
        numericCondition = numericConditionRepository.save(numericCondition);

        numericCondition = numericConditionRepository.save(numericCondition);
        return NumericConditionMapper.map(numericCondition);
    }

    @Override
    public NumericConditionResponseDTO findByUuid(UUID uuid) {
        return NumericConditionMapper.map(getEntityByUuid(uuid));
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
        NumericCondition existingNumericCondition = getEntityByUuid(uuid);
        NumericConditionMapper.map(numericConditionRequestDTO, existingNumericCondition);

        if (existingNumericCondition.getNumericField().getUuid() != numericConditionRequestDTO.getFieldId())
            existingNumericCondition.setNumericField(numericFieldService.getEntityByUuid(numericConditionRequestDTO.getFieldId()));
        return NumericConditionMapper.map(numericConditionRepository.save(existingNumericCondition));
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        numericConditionRepository.delete(getEntityByUuid(uuid));
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return numericConditionRepository.existsByUuid(uuid);
    }

    @Override
    public NumericCondition getEntityByUuid(UUID uuid) {
        return numericConditionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'UUID : " + uuid));
    }
}
