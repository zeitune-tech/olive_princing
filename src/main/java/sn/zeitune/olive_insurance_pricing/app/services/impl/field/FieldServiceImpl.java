package sn.zeitune.olive_insurance_pricing.app.services.impl.field;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.field.Field;
import sn.zeitune.olive_insurance_pricing.app.mappers.field.FieldMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.field.FieldRepository;
import sn.zeitune.olive_insurance_pricing.app.services.FieldService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    @Override
    public FieldResponseDTO findByUuid(UUID uuid, UUID managementEntity) {
        Field field = fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        return FieldMapper.map(field);
    }

    @Override
    public Page<FieldResponseDTO> findAll(Pageable pageable, UUID managementEntity) {
        return fieldRepository.findAll(pageable)
                .map(FieldMapper::map);
    }

    @Override
    public Field getEntityByUuid(UUID uuid) {
        return fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
    }

//    @Override
//    public List<FieldResponseDTO> findAll() {
//        return fieldRepository.findAll()
//                .stream()
//                .map(FieldMapper::map)
//                .toList();
//    }

//    @Override
//    public List<FieldResponseDTO> findByProduct(UUID product) {
//        return fieldRepository.findByProduct(product)
//                .stream()
//                .map(FieldMapper::map)
//                .toList();
//    }
//
//    @Override
//    public List<FieldResponseDTO> searchByLabel(String label) {
//        return fieldRepository.findByLabelContainingIgnoreCase(label)
//                .stream()
//                .map(FieldMapper::map)
//                .toList();
//    }
//
//    @Override
//    public void deleteByUuid(UUID uuid) {
//        Field field = fieldRepository.findByUuid(uuid)
//                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
//        fieldRepository.delete(field);
//    }
//
//    @Override
//    public boolean existsByUuid(UUID uuid) {
//        return fieldRepository.existsByUuid(uuid);
//    }


}
