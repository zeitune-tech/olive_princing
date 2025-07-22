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
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOptions;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldOptionsMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.field.SelectFieldOptionsRepository;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionValueService;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SelectFieldOptionsServiceImpl implements SelectFieldOptionsService {

    private final SelectFieldOptionsRepository selectFieldOptionsRepository;
    private final SelectFieldOptionValueService  selectFieldOptionValueService;

    @Override
    public SelectFieldOptionsResponseDTO create(SelectFieldOptionsRequestDTO selectFieldOptionsRequestDTO) {
        // Vérifier si une valeur de champ avec le même nom existe déjà
        if (selectFieldOptionsRepository.existsByName(selectFieldOptionsRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de champ avec le nom '" + selectFieldOptionsRequestDTO.name() + "' existe déjà");
        }

        SelectFieldOptions selectFieldOptions = SelectFieldOptionsMapper.map(selectFieldOptionsRequestDTO);

        for (UUID possibility : selectFieldOptionsRequestDTO.possibilities()) {
            SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueService.getEntityByUuid(possibility);
            selectFieldOptions.getPossibilities().add(selectFieldOptionValue);
        }

        selectFieldOptions = selectFieldOptionsRepository.save(selectFieldOptions);
        return SelectFieldOptionsMapper.map(selectFieldOptions);
    }

    @Override
    public SelectFieldOptionsResponseDTO findById(Long id) {
        SelectFieldOptions selectFieldOptions = selectFieldOptionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + id));
        return SelectFieldOptionsMapper.map(selectFieldOptions);
    }

    @Override
    public SelectFieldOptions getEntityByUuid(UUID uuid) {
        return selectFieldOptionsRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + uuid));
    }

    @Override
    public SelectFieldOptionsResponseDTO findByUuid(UUID uuid) {
        return SelectFieldOptionsMapper.map(getEntityByUuid(uuid));
    }

    @Override
    public List<SelectFieldOptionsResponseDTO> findAll() {
        return selectFieldOptionsRepository.findAll()
                .stream()
                .map(SelectFieldOptionsMapper::map)
                .toList();
    }

    @Override
    public Page<SelectFieldOptionsResponseDTO> findAll(Pageable pageable) {
        List<SelectFieldOptions> response = selectFieldOptionsRepository.findAll();

        for (SelectFieldOptions s : response) {
            System.err.println(s);
        }

        return selectFieldOptionsRepository.findAll(pageable).map(SelectFieldOptionsMapper::map);
    }

    @Override
    public Optional<SelectFieldOptionsResponseDTO> findByName(String name) {
        return selectFieldOptionsRepository.findByName(name)
                .map(SelectFieldOptionsMapper::map);
    }

    @Override
    public List<SelectFieldOptionsResponseDTO> searchByName(String name) {
        return selectFieldOptionsRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(SelectFieldOptionsMapper::map)
                .toList();
    }

    @Override
    public SelectFieldOptionsResponseDTO update(Long id, SelectFieldOptionsRequestDTO selectFieldOptionsRequestDTO) {
        SelectFieldOptions existingSelectFieldOptions = selectFieldOptionsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + id));
        
        // Vérifier si le nouveau nom existe déjà (sauf si c'est la même valeur de champ)
        if (!existingSelectFieldOptions.getName().equals(selectFieldOptionsRequestDTO.name()) &&
            selectFieldOptionsRepository.existsByName(selectFieldOptionsRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de champ avec le nom '" + selectFieldOptionsRequestDTO.name() + "' existe déjà");
        }
        
        SelectFieldOptionsMapper.map(selectFieldOptionsRequestDTO, existingSelectFieldOptions);
        SelectFieldOptions updatedSelectFieldOptions = selectFieldOptionsRepository.save(existingSelectFieldOptions);
        return SelectFieldOptionsMapper.map(updatedSelectFieldOptions);
    }

    @Override
    public SelectFieldOptionsResponseDTO updateByUuid(UUID uuid, SelectFieldOptionsRequestDTO selectFieldOptionsRequestDTO) {
        SelectFieldOptions existingSelectFieldOptions = selectFieldOptionsRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + uuid));

        // Vérifier si le nouveau nom existe déjà (sauf si c'est la même valeur de champ)
        if (!existingSelectFieldOptions.getName().equals(selectFieldOptionsRequestDTO.name()) &&
                selectFieldOptionsRepository.existsByName(selectFieldOptionsRequestDTO.name())) {
            throw new IllegalArgumentException("Une valeur de champ avec le nom '" + selectFieldOptionsRequestDTO.name() + "' existe déjà");
        }

        for (UUID possibility : selectFieldOptionsRequestDTO.possibilities()) {
            SelectFieldOptionValue selectFieldOptionValue = selectFieldOptionValueService.getEntityByUuid(possibility);
            existingSelectFieldOptions.getPossibilities().add(selectFieldOptionValue);
        }

        SelectFieldOptionsMapper.map(selectFieldOptionsRequestDTO, existingSelectFieldOptions);
        SelectFieldOptions updatedSelectFieldOptions = selectFieldOptionsRepository.save(existingSelectFieldOptions);
        return SelectFieldOptionsMapper.map(updatedSelectFieldOptions);
    }

    @Override
    public void delete(Long id) {
        if (!selectFieldOptionsRepository.existsById(id)) {
            throw new EntityNotFoundException("Valeur de champ non trouvée avec l'ID : " + id);
        }
        selectFieldOptionsRepository.deleteById(id);
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
