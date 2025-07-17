package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Field;
import sn.zeitune.olive_insurance_pricing.app.mappers.SelectedFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.FieldRepository;
import sn.zeitune.olive_insurance_pricing.app.services.FieldService;
import sn.zeitune.olive_insurance_pricing.enums.FieldType;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    @Override
    public SelectFieldResponseDTO create(SelectFieldRequestDTO selectFieldRequestDTO) {
        // Vérifier si un champ avec le même nom de variable existe déjà
        if (fieldRepository.existsByVariableName(selectFieldRequestDTO.variableName())) {
            throw new IllegalArgumentException("Un champ avec le nom de variable '" + selectFieldRequestDTO.variableName() + "' existe déjà");
        }
        
        Field field = SelectedFieldMapper.map(selectFieldRequestDTO);
        field = fieldRepository.save(field);
        return SelectedFieldMapper.map(field);
    }

    @Override
    public SelectFieldResponseDTO findById(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'ID : " + id));
        return SelectedFieldMapper.map(field);
    }

    @Override
    public SelectFieldResponseDTO findByUuid(UUID uuid) {
        Field field = fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        return SelectedFieldMapper.map(field);
    }

    @Override
    public List<SelectFieldResponseDTO> findAll() {
        return fieldRepository.findAll()
                .stream()
                .map(SelectedFieldMapper::map)
                .toList();
    }

    @Override
    public Page<SelectFieldResponseDTO> findAll(Pageable pageable) {
        return fieldRepository.findAll(pageable)
                .map(SelectedFieldMapper::map);
    }

    @Override
    public List<SelectFieldResponseDTO> findByType(FieldType type) {
        return fieldRepository.findByType(type)
                .stream()
                .map(SelectedFieldMapper::map)
                .toList();
    }

    @Override
    public List<SelectFieldResponseDTO> findByProduct(UUID product) {
        return fieldRepository.findByProduct(product)
                .stream()
                .map(SelectedFieldMapper::map)
                .toList();
    }

    @Override
    public List<SelectFieldResponseDTO> searchByLabel(String label) {
        return fieldRepository.findByLabelContainingIgnoreCase(label)
                .stream()
                .map(SelectedFieldMapper::map)
                .toList();
    }

    @Override
    public SelectFieldResponseDTO update(Long id, SelectFieldRequestDTO selectFieldRequestDTO) {
        Field existingField = fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'ID : " + id));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est le même champ)
        if (!existingField.getVariableName().equals(selectFieldRequestDTO.variableName()) &&
            fieldRepository.existsByVariableName(selectFieldRequestDTO.variableName())) {
            throw new IllegalArgumentException("Un champ avec le nom de variable '" + selectFieldRequestDTO.variableName() + "' existe déjà");
        }
        
        SelectedFieldMapper.map(selectFieldRequestDTO, existingField);
        Field updatedField = fieldRepository.save(existingField);
        return SelectedFieldMapper.map(updatedField);
    }

    @Override
    public SelectFieldResponseDTO updateByUuid(UUID uuid, SelectFieldRequestDTO selectFieldRequestDTO) {
        Field existingField = fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est le même champ)
        if (!existingField.getVariableName().equals(selectFieldRequestDTO.variableName()) &&
            fieldRepository.existsByVariableName(selectFieldRequestDTO.variableName())) {
            throw new IllegalArgumentException("Un champ avec le nom de variable '" + selectFieldRequestDTO.variableName() + "' existe déjà");
        }
        
        SelectedFieldMapper.map(selectFieldRequestDTO, existingField);
        Field updatedField = fieldRepository.save(existingField);
        return SelectedFieldMapper.map(updatedField);
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
        Field field = fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        fieldRepository.delete(field);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return fieldRepository.existsByUuid(uuid);
    }
}
