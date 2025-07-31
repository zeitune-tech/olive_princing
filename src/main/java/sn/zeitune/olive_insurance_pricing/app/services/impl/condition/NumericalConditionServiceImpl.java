package sn.zeitune.olive_insurance_pricing.app.services.impl.condition;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.NumericalConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.NumericalConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.condition.NumericalConditionMapper;
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
    public NumericalConditionResponseDTO create(NumericalConditionRequestDTO numericalConditionRequestDTO) {
        NumericCondition numericCondition = NumericalConditionMapper.map(numericalConditionRequestDTO);

        NumericField numericField = numericFieldService.getEntityByUuid(numericalConditionRequestDTO.fieldId());
        if (numericField == null) throw new RuntimeException(String.format(""));

        numericCondition.setNumericField(numericField);
        numericCondition = numericConditionRepository.save(numericCondition);

        numericCondition = numericConditionRepository.save(numericCondition);
        return NumericalConditionMapper.map(numericCondition);
    }

    @Override
    public NumericalConditionResponseDTO findById(Long id) {
        NumericCondition numericCondition = numericConditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'ID : " + id));
        return NumericalConditionMapper.map(numericCondition);
    }

    @Override
    public NumericalConditionResponseDTO findByUuid(UUID uuid) {
        return NumericalConditionMapper.map(getEntityByUuid(uuid));
    }

    @Override
    public List<NumericalConditionResponseDTO> findAll() {
        return numericConditionRepository.findAll()
                .stream()
                .map(NumericalConditionMapper::map)
                .toList();
    }

    @Override
    public Page<NumericalConditionResponseDTO> findAll(Pageable pageable) {
        return numericConditionRepository.findAll(pageable)
                .map(NumericalConditionMapper::map);
    }


    @Override
    public List<NumericalConditionResponseDTO> findByField(Long fieldId) {
        return numericConditionRepository.findById(fieldId)
                .stream()
                .map(NumericalConditionMapper::map)
                .toList();
    }

    @Override
    public NumericalConditionResponseDTO update(Long id, NumericalConditionRequestDTO numericalConditionRequestDTO) {
        NumericCondition existingNumericCondition = numericConditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'ID : " + id));
        
        NumericalConditionMapper.map(numericalConditionRequestDTO, existingNumericCondition);
        NumericCondition updatedNumericCondition = numericConditionRepository.save(existingNumericCondition);
        return NumericalConditionMapper.map(updatedNumericCondition);
    }

    @Override
    public NumericalConditionResponseDTO updateByUuid(UUID uuid, NumericalConditionRequestDTO numericalConditionRequestDTO) {
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
    }

    @Override
    public void delete(Long id) {
        if (!numericConditionRepository.existsById(id)) {
            throw new EntityNotFoundException("Condition non trouvée avec l'ID : " + id);
        }
        numericConditionRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
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
