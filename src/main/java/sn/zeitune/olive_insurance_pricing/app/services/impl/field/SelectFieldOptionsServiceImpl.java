package sn.zeitune.olive_insurance_pricing.app.services.impl.field;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldOptionsRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldOptionsResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptionValue;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOption;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldOptionsMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.field.SelectFieldOptionRepository;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionValueService;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SelectFieldOptionsServiceImpl implements SelectFieldOptionsService {

    private final SelectFieldOptionRepository selectFieldOptionRepository;
    private final SelectFieldOptionValueService  selectFieldOptionValueService;

    @Override
    public SelectFieldOptionsResponseDTO create(SelectFieldOptionsRequestDTO selectFieldOptionsRequestDTO) {
        // Vérifier si une valeur de champ avec le même nom existe déjà
        if (selectFieldOptionRepository.existsByName(selectFieldOptionsRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de champ avec le nom '" + selectFieldOptionsRequestDTO.name() + "' existe déjà");
        }

        SelectFieldOption selectFieldOption = SelectFieldOptionsMapper.map(selectFieldOptionsRequestDTO);

        for (UUID possibility : selectFieldOptionsRequestDTO.possibilities()) {
            SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueService.getEntityByUuid(possibility);
            selectFieldOption.getPossibilities().add(selectFieldOptionValue);
        }

        selectFieldOption = selectFieldOptionRepository.save(selectFieldOption);
        return SelectFieldOptionsMapper.map(selectFieldOption);
    }

    @Override
    public SelectFieldOptionsResponseDTO findById(Long id) {
        SelectFieldOption selectFieldOption = selectFieldOptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + id));
        return SelectFieldOptionsMapper.map(selectFieldOption);
    }

    @Override
    public SelectFieldOption getEntityByUuid(UUID uuid) {
        return selectFieldOptionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + uuid));
    }

    @Override
    public SelectFieldOptionsResponseDTO findByUuid(UUID uuid) {
        return SelectFieldOptionsMapper.map(getEntityByUuid(uuid));
    }

    @Override
    public List<SelectFieldOptionsResponseDTO> findAll() {
        return selectFieldOptionRepository.findAll()
                .stream()
                .map(SelectFieldOptionsMapper::map)
                .toList();
    }

    @Override
    public Page<SelectFieldOptionsResponseDTO> findAll(Pageable pageable) {
        List<SelectFieldOption> response = selectFieldOptionRepository.findAll();

        for (SelectFieldOption s : response) {
            System.err.println(s);
        }

        return selectFieldOptionRepository.findAll(pageable).map(SelectFieldOptionsMapper::map);
    }

    @Override
    public Optional<SelectFieldOptionsResponseDTO> findByName(String name) {
        return selectFieldOptionRepository.findByName(name)
                .map(SelectFieldOptionsMapper::map);
    }

    @Override
    public List<SelectFieldOptionsResponseDTO> searchByName(String name) {
        return selectFieldOptionRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(SelectFieldOptionsMapper::map)
                .toList();
    }

    @Override
    public SelectFieldOptionsResponseDTO update(Long id, SelectFieldOptionsRequestDTO selectFieldOptionsRequestDTO) {
        SelectFieldOption existingSelectFieldOption = selectFieldOptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + id));
        
        // Vérifier si le nouveau nom existe déjà (sauf si c'est la même valeur de champ)
        if (!existingSelectFieldOption.getName().equals(selectFieldOptionsRequestDTO.name()) &&
            selectFieldOptionRepository.existsByName(selectFieldOptionsRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de champ avec le nom '" + selectFieldOptionsRequestDTO.name() + "' existe déjà");
        }
        
        SelectFieldOptionsMapper.map(selectFieldOptionsRequestDTO, existingSelectFieldOption);
        SelectFieldOption updatedSelectFieldOption = selectFieldOptionRepository.save(existingSelectFieldOption);
        return SelectFieldOptionsMapper.map(updatedSelectFieldOption);
    }

    @Override
    public SelectFieldOptionsResponseDTO updateByUuid(UUID uuid, SelectFieldOptionsRequestDTO selectFieldOptionsRequestDTO) {
        SelectFieldOption existingSelectFieldOption = selectFieldOptionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + uuid));

        // Vérifier si le nouveau nom existe déjà (sauf si c'est la même valeur de champ)
        if (!existingSelectFieldOption.getName().equals(selectFieldOptionsRequestDTO.name()) &&
                selectFieldOptionRepository.existsByName(selectFieldOptionsRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de champ avec le nom '" + selectFieldOptionsRequestDTO.name() + "' existe déjà");
        }

        for (UUID possibility : selectFieldOptionsRequestDTO.possibilities()) {
            SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueService.getEntityByUuid(possibility);
            existingSelectFieldOption.getPossibilities().add(selectFieldOptionValue);
        }

        SelectFieldOptionsMapper.map(selectFieldOptionsRequestDTO, existingSelectFieldOption);
        SelectFieldOption updatedSelectFieldOption = selectFieldOptionRepository.save(existingSelectFieldOption);
        return SelectFieldOptionsMapper.map(updatedSelectFieldOption);
    }

    @Override
    public void delete(Long id) {
        if (!selectFieldOptionRepository.existsById(id)) {
            throw new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + id);
        }
        selectFieldOptionRepository.deleteById(id);
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
