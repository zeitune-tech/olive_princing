package sn.zeitune.olive_insurance_pricing.app.services.impl.field;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOption;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldOptionsMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.field.SelectFieldOptionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionValueService;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SelectFieldOptionServiceImpl implements SelectFieldOptionService {

    private final SelectFieldOptionRepository selectFieldOptionRepository;
    private final SelectFieldOptionValueService  selectFieldOptionValueService;

    @Override
    public SelectFieldOptionResponseDTO create(SelectFieldOptionRequestDTO selectFieldOptionRequestDTO) {
        // Vérifier si une valeur de champ avec le même nom existe déjà
        if (selectFieldOptionRepository.existsByName(selectFieldOptionRequestDTO.getName())) {
            throw new IllegalArgumentException("Une valeur de champ avec le nom '" + selectFieldOptionRequestDTO.getName() + "' existe déjà");
        }

        SelectFieldOption selectFieldOption = SelectFieldOptionsMapper.map(selectFieldOptionRequestDTO);

        for (UUID possibility : selectFieldOptionRequestDTO.getPossibilities()) {
            SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueService.getEntityByUuid(possibility);
            selectFieldOption.getPossibilities().add(selectFieldOptionValue);
        }

        selectFieldOption = selectFieldOptionRepository.save(selectFieldOption);
        return SelectFieldOptionsMapper.map(selectFieldOption);
    }

    @Override
    public SelectFieldOption getEntityByUuid(UUID uuid) {
        return selectFieldOptionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + uuid));
    }

    @Override
    public SelectFieldOptionResponseDTO findByUuid(UUID uuid) {
        return SelectFieldOptionsMapper.map(getEntityByUuid(uuid));
    }

    @Override
    public List<SelectFieldOptionResponseDTO> findAll() {
        return selectFieldOptionRepository.findAll()
                .stream()
                .map(SelectFieldOptionsMapper::map)
                .toList();
    }

    @Override
    public Page<SelectFieldOptionResponseDTO> findAll(Pageable pageable) {
        return selectFieldOptionRepository.findAll(pageable).map(SelectFieldOptionsMapper::map);
    }

    @Override
    public Optional<SelectFieldOptionResponseDTO> findByName(String name) {
        return selectFieldOptionRepository.findByName(name)
                .map(SelectFieldOptionsMapper::map);
    }

    @Override
    public List<SelectFieldOptionResponseDTO> searchByName(String name) {
        return selectFieldOptionRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(SelectFieldOptionsMapper::map)
                .toList();
    }

    @Override
    public SelectFieldOptionResponseDTO updateByUuid(UUID uuid, SelectFieldOptionRequestDTO selectFieldOptionRequestDTO) {
        SelectFieldOption existingSelectFieldOption = selectFieldOptionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + uuid));

        // Vérifier si le nouveau nom existe déjà (sauf si c'est la même valeur de champ)
        if (!existingSelectFieldOption.getName().equals(selectFieldOptionRequestDTO.getName()) &&
                selectFieldOptionRepository.existsByName(selectFieldOptionRequestDTO.getName())) {
            throw new IllegalArgumentException("Une valeur de champ avec le nom '" + selectFieldOptionRequestDTO.getName() + "' existe déjà");
        }

        List<SelectFieldOptionValue> existingPossibilities = existingSelectFieldOption.getPossibilities();

        for (UUID possibilityUuid : selectFieldOptionRequestDTO.getPossibilities()) {
            SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueService.getEntityByUuid(possibilityUuid);
            if (!existingPossibilities.contains(selectFieldOptionValue)) {
                existingPossibilities.add(selectFieldOptionValue);
            }
        }


        SelectFieldOptionsMapper.map(selectFieldOptionRequestDTO, existingSelectFieldOption);
        SelectFieldOption updatedSelectFieldOption = selectFieldOptionRepository.save(existingSelectFieldOption);
        return SelectFieldOptionsMapper.map(updatedSelectFieldOption);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        throw new UnsupportedOperationException("SelectFieldOptions n'utilise pas d'UUID");
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        throw new UnsupportedOperationException("SelectFieldOptions n'utilise pas d'UUID");
    }
}
