package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem.field;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.SelectFieldOptionValueRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.SelectFieldOptionValueResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field.SelectFieldOptionValueMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.field.SelectFieldOptionValueRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.field.SelectFieldOptionValueService;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SelectFieldOptionValueServiceImpl implements SelectFieldOptionValueService {

    private final SelectFieldOptionValueRepository selectFieldOptionValueRepository;

    @Override
    public SelectFieldOptionValueResponseDTO create(SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO, UUID managementEntity) {
        // Vérifier si une valeur avec le même nom existe déjà
        if (selectFieldOptionValueRepository.existsByName(selectFieldOptionValueRequestDTO.getName())) {
            throw new IllegalArgumentException("Une valeur de possibilité avec le nom '" + selectFieldOptionValueRequestDTO.getName() + "' existe déjà");
        }

        SelectFieldOptionValue selectFieldOptionValue = SelectFieldOptionValueMapper.map(selectFieldOptionValueRequestDTO);
        selectFieldOptionValue.setManagementEntity(managementEntity);
        selectFieldOptionValue = selectFieldOptionValueRepository.save(selectFieldOptionValue);
        return SelectFieldOptionValueMapper.map(selectFieldOptionValue);
    }

    @Override
    public SelectFieldOptionValue getEntityByUuid(UUID uuid) {
        return selectFieldOptionValueRepository.findByUuid(uuid)
                .orElseThrow( () -> new EntityNotFoundException("Valeur de possibilité non trouvée avec l'UUID : " + uuid) );
    }

    @Override
    public Page<SelectFieldOptionValueResponseDTO> findAll(Pageable pageable, UUID managementEntity) {
        return selectFieldOptionValueRepository.findAllByManagementEntity(pageable, managementEntity)
                .map(SelectFieldOptionValueMapper::map);
    }

    @Override
    public SelectFieldOptionValueResponseDTO updateByUuid(UUID uuid, SelectFieldOptionValueRequestDTO selectFieldOptionValueRequestDTO) {
        SelectFieldOptionValue existingSelectFieldOptionValue = selectFieldOptionValueRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de possibilité non trouvée avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom existe déjà (sauf si c'est la même valeur)
        if (!existingSelectFieldOptionValue.getName().equals(selectFieldOptionValueRequestDTO.getName()) &&
            selectFieldOptionValueRepository.existsByName(selectFieldOptionValueRequestDTO.getName())) {
            throw new IllegalArgumentException("Une valeur de possibilité avec le nom '" + selectFieldOptionValueRequestDTO.getName() + "' existe déjà");
        }
        
        SelectFieldOptionValueMapper.map(selectFieldOptionValueRequestDTO, existingSelectFieldOptionValue);
        SelectFieldOptionValue updatedSelectFieldOptionValue = selectFieldOptionValueRepository.save(existingSelectFieldOptionValue);
        return SelectFieldOptionValueMapper.map(updatedSelectFieldOptionValue);
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