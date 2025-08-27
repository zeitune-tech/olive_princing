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
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectFieldOption;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.SelectFieldMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.field.SelectFieldRepository;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldOptionService;
import sn.zeitune.olive_insurance_pricing.app.services.SelectFieldService;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemPreparationService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SelectFieldServiceImpl implements SelectFieldService {

    private final SelectFieldRepository selectFieldRepository;
    private final SelectFieldOptionService selectFieldOptionService;
    private final VariableItemPreparationService variableItemPreparationService;

    @Override
    public SelectFieldResponseDTO create(SelectFieldRequestDTO selectFieldRequestDTO, UUID managementEntity) {
        SelectFieldOption selectFieldOption = selectFieldOptionService.getEntityByUuid(selectFieldRequestDTO.getOptions());
        if (selectFieldOption == null)
            throw new IllegalArgumentException("Options are not defined");
        SelectField selectFieldSaving = (SelectField) variableItemPreparationService.prepareCreationEntity(selectFieldRequestDTO, new SelectField(), managementEntity);
        SelectFieldMapper.putRequestValue(selectFieldRequestDTO, selectFieldSaving);
        selectFieldSaving.setOptions(selectFieldOptionService.getEntityByUuid(selectFieldRequestDTO.getOptions()));
        return SelectFieldMapper.map(selectFieldRepository.save(selectFieldSaving));
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
    public SelectFieldResponseDTO updateByUuid(UUID uuid, SelectFieldRequestDTO selectFieldRequestDTO) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        SelectField field = selectFieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        field.setPricingType(null);
        selectFieldRepository.delete(field);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return selectFieldRepository.existsByUuid(uuid);
    }
}
