package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FieldValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FieldValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.SelectFieldOptions;
import sn.zeitune.olive_insurance_pricing.app.mappers.FieldValueMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.FieldValueRepository;
import sn.zeitune.olive_insurance_pricing.app.services.FieldValueService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldValueServiceImpl implements FieldValueService {

    private final FieldValueRepository fieldValueRepository;

    @Override
    public FieldValueResponseDTO create(FieldValueRequestDTO fieldValueRequestDTO) {
        // Vérifier si une valeur de champ avec le même nom existe déjà
        if (fieldValueRepository.existsByName(fieldValueRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de champ avec le nom '" + fieldValueRequestDTO.name() + "' existe déjà");
        }
        
        SelectFieldOptions selectFieldOptions = FieldValueMapper.toEntity(fieldValueRequestDTO);
        selectFieldOptions = fieldValueRepository.save(selectFieldOptions);
        return FieldValueMapper.toResponseDTO(selectFieldOptions);
    }

    @Override
    public FieldValueResponseDTO findById(Long id) {
        SelectFieldOptions selectFieldOptions = fieldValueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + id));
        return FieldValueMapper.toResponseDTO(selectFieldOptions);
    }

    @Override
    public FieldValueResponseDTO findByUuid(UUID uuid) {
        throw new UnsupportedOperationException("FieldValue n'utilise pas d'UUID");
    }

    @Override
    public List<FieldValueResponseDTO> findAll() {
        return fieldValueRepository.findAll()
                .stream()
                .map(FieldValueMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Page<FieldValueResponseDTO> findAll(Pageable pageable) {
        return fieldValueRepository.findAll(pageable)
                .map(FieldValueMapper::toResponseDTO);
    }

    @Override
    public Optional<FieldValueResponseDTO> findByName(String name) {
        return fieldValueRepository.findByName(name)
                .map(FieldValueMapper::toResponseDTO);
    }

    @Override
    public List<FieldValueResponseDTO> searchByName(String name) {
        return fieldValueRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(FieldValueMapper::toResponseDTO)
                .toList();
    }

    @Override
    public FieldValueResponseDTO update(Long id, FieldValueRequestDTO fieldValueRequestDTO) {
        SelectFieldOptions existingSelectFieldOptions = fieldValueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + id));
        
        // Vérifier si le nouveau nom existe déjà (sauf si c'est la même valeur de champ)
        if (!existingSelectFieldOptions.getName().equals(fieldValueRequestDTO.name()) &&
            fieldValueRepository.existsByName(fieldValueRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de champ avec le nom '" + fieldValueRequestDTO.name() + "' existe déjà");
        }
        
        FieldValueMapper.updateEntityFromRequestDTO(existingSelectFieldOptions, fieldValueRequestDTO);
        SelectFieldOptions updatedSelectFieldOptions = fieldValueRepository.save(existingSelectFieldOptions);
        return FieldValueMapper.toResponseDTO(updatedSelectFieldOptions);
    }

    @Override
    public FieldValueResponseDTO updateByUuid(UUID uuid, FieldValueRequestDTO fieldValueRequestDTO) {
        throw new UnsupportedOperationException("FieldValue n'utilise pas d'UUID");
    }

    @Override
    public void delete(Long id) {
        if (!fieldValueRepository.existsById(id)) {
            throw new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + id);
        }
        fieldValueRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        throw new UnsupportedOperationException("FieldValue n'utilise pas d'UUID");
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        throw new UnsupportedOperationException("FieldValue n'utilise pas d'UUID");
    }
}
