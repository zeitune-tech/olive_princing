package sn.zeitune.olive_insurance_pricing.app.services.impl.field;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldOptionValueMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.field.SelectFieldOptionValueRepository;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionValueService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SelectFieldOptionValueServiceImpl implements SelectFieldOptionValueService {

    private final SelectFieldOptionValueRepository selectFieldOptionValueRepository;

    @Override
    public SelectFieldOptionValueResponseDTO create(SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO) {
        // Vérifier si une valeur avec le même nom existe déjà
        if (selectFieldOptionValueRepository.existsByName(selectFieldOptionValueRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de possibilité avec le nom '" + selectFieldOptionValueRequestDTO.name() + "' existe déjà");
        }

        SelectFieldOptionValue selectFieldOptionValue = SelectFieldOptionValueMapper.map(selectFieldOptionValueRequestDTO);
        selectFieldOptionValue = selectFieldOptionValueRepository.save(selectFieldOptionValue);
        return SelectFieldOptionValueMapper.map(selectFieldOptionValue);
    }

    @Override
    public SelectFieldOptionValueResponseDTO findById(Long id) {
        SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de possibilité non trouvée avec l'ID : " + id));
        return SelectFieldOptionValueMapper.map(selectFieldOptionValue);
    }

    @Override
    public SelectFieldOptionValueResponseDTO findByUuid(UUID uuid) {
        SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de possibilité non trouvée avec l'UUID : " + uuid));
        return SelectFieldOptionValueMapper.map(selectFieldOptionValue);
    }

    @Override
    public SelectFieldOptionValue getEntityByUuid(UUID uuid) {
        return selectFieldOptionValueRepository.findByUuid(uuid)
                .orElseThrow( () -> new EntityNotFoundException("Valeur de possibilité non trouvée avec l'UUID : " + uuid) );
    }

    @Override
    public List<SelectFieldOptionValueResponseDTO> findAll() {
        return selectFieldOptionValueRepository.findAll()
                .stream()
                .map(SelectFieldOptionValueMapper::map)
                .toList();
    }

    @Override
    public Page<SelectFieldOptionValueResponseDTO> findAll(Pageable pageable) {
        return selectFieldOptionValueRepository.findAll(pageable)
                .map(SelectFieldOptionValueMapper::map);
    }

    @Override
    public Optional<SelectFieldOptionValueResponseDTO> findByName(String name) {
        return selectFieldOptionValueRepository.findByName(name)
                .map(SelectFieldOptionValueMapper::map);
    }

    @Override
    public List<SelectFieldOptionValueResponseDTO> searchByName(String name) {
        return selectFieldOptionValueRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(SelectFieldOptionValueMapper::map)
                .toList();
    }

    @Override
    public List<SelectFieldOptionValueResponseDTO> findByGroup(String group) {
        return selectFieldOptionValueRepository.findByGroup(group)
                .stream()
                .map(SelectFieldOptionValueMapper::map)
                .toList();
    }

    @Override
    public List<SelectFieldOptionValueResponseDTO> searchByLabel(String label) {
        return selectFieldOptionValueRepository.findByLabelContainingIgnoreCase(label)
                .stream()
                .map(SelectFieldOptionValueMapper::map)
                .toList();
    }

    @Override
    public SelectFieldOptionValueResponseDTO update(Long id, SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO) {
        SelectFieldOptionValue existingSelectFieldOptionValue = selectFieldOptionValueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de possibilité non trouvée avec l'ID : " + id));
        
        // Vérifier si le nouveau nom existe déjà (sauf si c'est la même valeur)
        if (!existingSelectFieldOptionValue.getName().equals(selectFieldOptionValueRequestDTO.name()) &&
            selectFieldOptionValueRepository.existsByName(selectFieldOptionValueRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de possibilité avec le nom '" + selectFieldOptionValueRequestDTO.name() + "' existe déjà");
        }
        
        SelectFieldOptionValueMapper.map(selectFieldOptionValueRequestDTO, existingSelectFieldOptionValue);
        SelectFieldOptionValue updatedSelectFieldOptionValue = selectFieldOptionValueRepository.save(existingSelectFieldOptionValue);
        return SelectFieldOptionValueMapper.map(updatedSelectFieldOptionValue);
    }

    @Override
    public SelectFieldOptionValueResponseDTO updateByUuid(UUID uuid, SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO) {
        SelectFieldOptionValue existingSelectFieldOptionValue = selectFieldOptionValueRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de possibilité non trouvée avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom existe déjà (sauf si c'est la même valeur)
        if (!existingSelectFieldOptionValue.getName().equals(selectFieldOptionValueRequestDTO.name()) &&
            selectFieldOptionValueRepository.existsByName(selectFieldOptionValueRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de possibilité avec le nom '" + selectFieldOptionValueRequestDTO.name() + "' existe déjà");
        }
        
        SelectFieldOptionValueMapper.map(selectFieldOptionValueRequestDTO, existingSelectFieldOptionValue);
        SelectFieldOptionValue updatedSelectFieldOptionValue = selectFieldOptionValueRepository.save(existingSelectFieldOptionValue);
        return SelectFieldOptionValueMapper.map(updatedSelectFieldOptionValue);
    }

    @Override
    public void delete(Long id) {
        if (!selectFieldOptionValueRepository.existsById(id)) {
            throw new EntityNotFoundException("Valeur de possibilité non trouvée avec l'ID : " + id);
        }
        selectFieldOptionValueRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de possibilité non trouvée avec l'UUID : " + uuid));
        selectFieldOptionValueRepository.delete(selectFieldOptionValue);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return selectFieldOptionValueRepository.existsByUuid(uuid);
    }
}