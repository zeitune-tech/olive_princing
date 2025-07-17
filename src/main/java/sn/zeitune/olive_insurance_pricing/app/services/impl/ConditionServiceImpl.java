package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Condition;
import sn.zeitune.olive_insurance_pricing.app.mappers.ConditionMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.ConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.ConditionService;
import sn.zeitune.olive_insurance_pricing.enums.Operator;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;

    @Override
    public ConditionResponseDTO create(ConditionRequestDTO conditionRequestDTO) {
        Condition condition = ConditionMapper.map(conditionRequestDTO);
        condition = conditionRepository.save(condition);
        return ConditionMapper.map(condition);
    }

    @Override
    public ConditionResponseDTO findById(Long id) {
        Condition condition = conditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'ID : " + id));
        return ConditionMapper.map(condition);
    }

    @Override
    public ConditionResponseDTO findByUuid(UUID uuid) {
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
    }

    @Override
    public List<ConditionResponseDTO> findAll() {
        return conditionRepository.findAll()
                .stream()
                .map(ConditionMapper::map)
                .toList();
    }

    @Override
    public Page<ConditionResponseDTO> findAll(Pageable pageable) {
        return conditionRepository.findAll(pageable)
                .map(ConditionMapper::map);
    }

    @Override
    public List<ConditionResponseDTO> findByValue(Double value) {
        return conditionRepository.findByValue(value)
                .stream()
                .map(ConditionMapper::map)
                .toList();
    }

    @Override
    public List<ConditionResponseDTO> findByOperator(Operator operator) {
        return conditionRepository.findByOperator(operator)
                .stream()
                .map(ConditionMapper::map)
                .toList();
    }

    @Override
    public List<ConditionResponseDTO> findByField(Long fieldId) {
        return conditionRepository.findByFieldId(fieldId)
                .stream()
                .map(ConditionMapper::map)
                .toList();
    }

    @Override
    public ConditionResponseDTO update(Long id, ConditionRequestDTO conditionRequestDTO) {
        Condition existingCondition = conditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'ID : " + id));
        
        ConditionMapper.map(conditionRequestDTO, existingCondition);
        Condition updatedCondition = conditionRepository.save(existingCondition);
        return ConditionMapper.map(updatedCondition);
    }

    @Override
    public ConditionResponseDTO updateByUuid(UUID uuid, ConditionRequestDTO conditionRequestDTO) {
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
    }

    @Override
    public void delete(Long id) {
        if (!conditionRepository.existsById(id)) {
            throw new EntityNotFoundException("Condition non trouvée avec l'ID : " + id);
        }
        conditionRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
    }
}
