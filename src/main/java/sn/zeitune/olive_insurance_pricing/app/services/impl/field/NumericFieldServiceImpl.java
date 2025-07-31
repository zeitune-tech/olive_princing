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

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class NumericFieldServiceImpl implements NumericFieldService {

    private final NumericFieldRepository fieldRepository;

    @Override
    public NumericFieldResponseDTO create(NumericFieldRequestDTO numericFieldRequestDTO) {
        // Vérifier si un champ avec le même nom de variable existe déjà
        if (fieldRepository.existsByVariableName(numericFieldRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Un champ avec le nom de variable '" + numericFieldRequestDTO.getVariableName() + "' existe déjà");
        }
        
        NumericField field = NumericFieldMapper.map(numericFieldRequestDTO);
        field = fieldRepository.save(field);
        return NumericFieldMapper.map(field);
    }

    @Override
    public NumericFieldResponseDTO findById(Long id) {
        NumericField field = fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'ID : " + id));
        return NumericFieldMapper.map(field);
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
    public Page<NumericFieldResponseDTO> findAll(Pageable pageable) {
        return fieldRepository.findAll(pageable)
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
    public NumericFieldResponseDTO update(Long id, NumericFieldRequestDTO numericFieldRequestDTO) {
        NumericField existingField = fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'ID : " + id));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est le même champ)
        if (!existingField.getVariableName().equals(numericFieldRequestDTO.getVariableName()) &&
            fieldRepository.existsByVariableName(numericFieldRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Un champ avec le nom de variable '" + numericFieldRequestDTO.getVariableName() + "' existe déjà");
        }
        
        NumericFieldMapper.map(numericFieldRequestDTO, existingField);
        NumericField updatedField = fieldRepository.save(existingField);
        return NumericFieldMapper.map(updatedField);
    }

    @Override
    public NumericFieldResponseDTO updateByUuid(UUID uuid, NumericFieldRequestDTO numericFieldRequestDTO) {
        NumericField existingField = fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est le même champ)
        if (!existingField.getVariableName().equals(numericFieldRequestDTO.getVariableName()) &&
            fieldRepository.existsByVariableName(numericFieldRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Un champ avec le nom de variable '" + numericFieldRequestDTO.getVariableName() + "' existe déjà");
        }
        
        NumericFieldMapper.map(numericFieldRequestDTO, existingField);
        NumericField updatedField = fieldRepository.save(existingField);
        return NumericFieldMapper.map(updatedField);
    }

    @Override
    public void delete(Long id) {
        if (!fieldRepository.existsById(id)) {
            throw new EntityNotFoundException("Champ non trouvé avec l'ID : " + id);
        }
        fieldRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        NumericField field = fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
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
