package sn.zeitune.olive_insurance_pricing.app.services.impl.field;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.field.SelectFieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.field.SelectFieldRepository;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionService;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SelectFieldServiceImpl implements SelectFieldService {

    private final SelectFieldRepository selectFieldRepository;
    private final SelectFieldOptionService selectFieldOptionService;

    @Override
    public SelectFieldResponseDTO create(SelectFieldRequestDTO selectFieldRequestDTO) {
        // Vérifier si un champ avec le même nom de variable existe déjà
        if (selectFieldRepository.existsByVariableName(selectFieldRequestDTO.getVariableName()))
            throw new IllegalArgumentException("Un champ avec le nom de variable '" + selectFieldRequestDTO.getVariableName() + "' existe déjà");


        if (selectFieldOptionService.findByUuid(selectFieldRequestDTO.getOptions()) == null)
            throw new IllegalArgumentException("Options are not defined");

        SelectField field = SelectFieldMapper.map(selectFieldRequestDTO);

        if (selectFieldRequestDTO.getOptions() != null)
            field.setOptions(selectFieldOptionService.getEntityByUuid(selectFieldRequestDTO.getOptions()));

        System.out.println(field);
        log.info(field.toString());

        field = selectFieldRepository.save(field);
        return SelectFieldMapper.map(field);
    }

    @Override
    public SelectFieldResponseDTO findById(Long id) {
        SelectField field = selectFieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'ID : " + id));
        return SelectFieldMapper.map(field);
    }

    @Override
    public SelectFieldResponseDTO findByUuid(UUID uuid) {
        return SelectFieldMapper.map(getEntityByUuid(uuid));
    }

    @Override
    public SelectField getEntityByUuid(UUID uuid) {
        return selectFieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
    }

    @Override
    public List<SelectFieldResponseDTO> findAll() {
        return selectFieldRepository.findAll()
                .stream()
                .map(SelectFieldMapper::map)
                .toList();
    }

    @Override
    public Page<SelectFieldResponseDTO> findAll(Pageable pageable) {
        return selectFieldRepository.findAll(pageable)
                .map(SelectFieldMapper::map);
    }

    @Override
    public List<SelectFieldResponseDTO> findByProduct(UUID product) {
        return selectFieldRepository.findByProduct(product)
                .stream()
                .map(SelectFieldMapper::map)
                .toList();
    }

    @Override
    public List<SelectFieldResponseDTO> searchByLabel(String label) {
        return selectFieldRepository.findByLabelContainingIgnoreCase(label)
                .stream()
                .map(SelectFieldMapper::map)
                .toList();
    }

    @Override
    public SelectFieldResponseDTO update(Long id, SelectFieldRequestDTO selectFieldRequestDTO) {
        SelectField existingField = selectFieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'ID : " + id));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est le même champ)
        if (!existingField.getVariableName().equals(selectFieldRequestDTO.getVariableName()) &&
            selectFieldRepository.existsByVariableName(selectFieldRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Un champ avec le nom de variable '" + selectFieldRequestDTO.getVariableName() + "' existe déjà");
        }

        SelectFieldMapper.map(selectFieldRequestDTO, existingField);

        if (selectFieldRequestDTO.getOptions() != null)
            existingField.setOptions(selectFieldOptionService.getEntityByUuid(selectFieldRequestDTO.getOptions()));

        SelectField updatedField = selectFieldRepository.save(existingField);
        return SelectFieldMapper.map(updatedField);
    }

    @Override
    public SelectFieldResponseDTO updateByUuid(UUID uuid, SelectFieldRequestDTO selectFieldRequestDTO) {
        SelectField existingField = selectFieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est le même champ)
        if (!existingField.getVariableName().equals(selectFieldRequestDTO.getVariableName()) &&
            selectFieldRepository.existsByVariableName(selectFieldRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Un champ avec le nom de variable '" + selectFieldRequestDTO.getVariableName() + "' existe déjà");
        }
        
        SelectFieldMapper.map(selectFieldRequestDTO, existingField);

        if (selectFieldRequestDTO.getOptions() != null)
            existingField.setOptions(selectFieldOptionService.getEntityByUuid(selectFieldRequestDTO.getOptions()));

        SelectField updatedField = selectFieldRepository.save(existingField);
        return SelectFieldMapper.map(updatedField);
    }

    @Override
    public void delete(Long id) {
        if (!selectFieldRepository.existsById(id)) {
            throw new EntityNotFoundException("Champ non trouvé avec l'ID : " + id);
        }
        selectFieldRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        SelectField field = selectFieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        selectFieldRepository.delete(field);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return selectFieldRepository.existsByUuid(uuid);
    }
}
