package sn.zeitune.olive_insurance_pricing.app.services.impl.condition;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.condition.NumericalConditionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.condition.NumericalConditionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.condition.NumericalCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.mappers.condition.NumericalConditionMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.condition.NumericalConditionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.NumericFieldService;
import sn.zeitune.olive_insurance_pricing.app.services.NumericalConditionService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NumericalConditionServiceImpl implements NumericalConditionService {

    private final NumericalConditionRepository numericalConditionRepository;
    private final NumericFieldService numericFieldService;

    @Override
    public NumericalConditionResponseDTO create(NumericalConditionRequestDTO numericalConditionRequestDTO) {
        NumericalCondition numericalCondition = NumericalConditionMapper.map(numericalConditionRequestDTO);

        NumericField numericField = numericFieldService.getEntityByUuid(numericalConditionRequestDTO.fieldId());
        if (numericField == null) throw new RuntimeException(String.format(""));

        numericalCondition.setNumericField(numericField);
        numericalCondition = numericalConditionRepository.save(numericalCondition);

        numericalCondition = numericalConditionRepository.save(numericalCondition);
        return NumericalConditionMapper.map(numericalCondition);
    }

    @Override
    public NumericalConditionResponseDTO findById(Long id) {
        NumericalCondition numericalCondition = numericalConditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'ID : " + id));
        return NumericalConditionMapper.map(numericalCondition);
    }

    @Override
    public NumericalConditionResponseDTO findByUuid(UUID uuid) {
        return NumericalConditionMapper.map(getEntityByUuid(uuid));
    }

    @Override
    public List<NumericalConditionResponseDTO> findAll() {
        return numericalConditionRepository.findAll()
                .stream()
                .map(NumericalConditionMapper::map)
                .toList();
    }

    @Override
    public Page<NumericalConditionResponseDTO> findAll(Pageable pageable) {
        return numericalConditionRepository.findAll(pageable)
                .map(NumericalConditionMapper::map);
    }


    @Override
    public List<NumericalConditionResponseDTO> findByField(Long fieldId) {
        return numericalConditionRepository.findById(fieldId)
                .stream()
                .map(NumericalConditionMapper::map)
                .toList();
    }

    @Override
    public NumericalConditionResponseDTO update(Long id, NumericalConditionRequestDTO numericalConditionRequestDTO) {
        NumericalCondition existingNumericalCondition = numericalConditionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'ID : " + id));
        
        NumericalConditionMapper.map(numericalConditionRequestDTO, existingNumericalCondition);
        NumericalCondition updatedNumericalCondition = numericalConditionRepository.save(existingNumericalCondition);
        return NumericalConditionMapper.map(updatedNumericalCondition);
    }

    @Override
    public NumericalConditionResponseDTO updateByUuid(UUID uuid, NumericalConditionRequestDTO numericalConditionRequestDTO) {
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
    }

    @Override
    public void delete(Long id) {
        if (!numericalConditionRepository.existsById(id)) {
            throw new EntityNotFoundException("Condition non trouvée avec l'ID : " + id);
        }
        numericalConditionRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        throw new UnsupportedOperationException("Condition n'utilise pas d'UUID");
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return numericalConditionRepository.existsByUuid(uuid);
    }

    @Override
    public NumericalCondition getEntityByUuid(UUID uuid) {
        return numericalConditionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Condition non trouvée avec l'UUID : " + uuid));
    }
}
