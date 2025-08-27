package sn.zeitune.olive_insurance_pricing.app.services.impl.field;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.NumericFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.NumericFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.field.NumericFieldRepository;
import sn.zeitune.olive_insurance_pricing.app.services.NumericFieldService;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemPreparationService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NumericFieldServiceImpl implements NumericFieldService {

    private final NumericFieldRepository fieldRepository;
    private final VariableItemPreparationService variableItemPreparationService;

    @Override
    public NumericFieldResponseDTO create(NumericFieldRequestDTO numericFieldRequestDTO, UUID managementEntity) {
        NumericField fieldSaving = (NumericField) variableItemPreparationService.prepareCreationEntity(numericFieldRequestDTO, new NumericField(), managementEntity);
        NumericFieldMapper.putRequestValue(numericFieldRequestDTO, fieldSaving);
        return NumericFieldMapper.map(fieldRepository.save(fieldSaving));
    }

    @Override
    public NumericFieldResponseDTO findByUuid(UUID uuid) {
        NumericField field = fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        return NumericFieldMapper.map(field);
    }

    @Override
    public List<NumericFieldResponseDTO> findAll() {
        return fieldRepository.findAll()
                .stream()
                .map(NumericFieldMapper::map)
                .toList();
    }

    @Override
    public Page<NumericFieldResponseDTO> findAll(Pageable pageable, UUID managementEntity) {
        return fieldRepository.findAllByManagementEntity(managementEntity, pageable)
                .map(NumericFieldMapper::map);
    }


    @Override
    public List<NumericFieldResponseDTO> findByProduct(UUID product) {
        return fieldRepository.findByProduct(product)
                .stream()
                .map(NumericFieldMapper::map)
                .toList();
    }

    @Override
    public List<NumericFieldResponseDTO> searchByLabel(String label) {
        return fieldRepository.findByLabelContainingIgnoreCase(label)
                .stream()
                .map(NumericFieldMapper::map)
                .toList();
    }

    @Override
    public NumericFieldResponseDTO updateByUuid(UUID uuid, NumericFieldRequestDTO numericFieldRequestDTO) {
        NumericField existingField = fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        existingField = (NumericField) variableItemPreparationService.prepareUpdatingEntity(numericFieldRequestDTO, existingField, existingField.getManagementEntity());
        NumericFieldMapper.putRequestValue(numericFieldRequestDTO, existingField);
        return NumericFieldMapper.map(fieldRepository.save(existingField));
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        NumericField field = fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        field.setPricingType(null);
        fieldRepository.delete(field);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return fieldRepository.existsByUuid(uuid);
    }

    @Override
    public NumericField getEntityByUuid(UUID uuid) {
        return fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
    }
}
