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

//    @Override
//    public FieldResponseDTO create(FieldRequestDTO numericFieldRequestDTO) {
//        // Vérifier si un champ avec le même nom de variable existe déjà
//        if (fieldRepository.existsByVariableName(numericFieldRequestDTO.variableName())) {
//            throw new IllegalArgumentException("Un champ avec le nom de variable '" + numericFieldRequestDTO.variableName() + "' existe déjà");
//        }
//
//        Field field = FieldMapper.map(numericFieldRequestDTO);
//        field = fieldRepository.save(field);
//        return FieldMapper.map(field);
//    }

    @Override
    public FieldResponseDTO findById(Long id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'ID : " + id));
        return FieldMapper.map(field);
    }

    @Override
    public FieldResponseDTO findByUuid(UUID uuid) {
        Field field = fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
        return FieldMapper.map(field);
    }

    @Override
    public List<FieldResponseDTO> findAll() {
        return fieldRepository.findAll()
                .stream()
                .map(FieldMapper::map)
                .toList();
    }

    @Override
    public Page<FieldResponseDTO> findAll(Pageable pageable) {
        return fieldRepository.findAll(pageable)
                .map(FieldMapper::map);
    }


    @Override
    public List<FieldResponseDTO> findByProduct(UUID product) {
        return fieldRepository.findByProduct(product)
                .stream()
                .map(FieldMapper::map)
                .toList();
    }

    @Override
    public List<FieldResponseDTO> searchByLabel(String label) {
        return fieldRepository.findByLabelContainingIgnoreCase(label)
                .stream()
                .map(FieldMapper::map)
                .toList();
    }

//    @Override
//    public FieldResponseDTO update(Long id, FieldRequestDTO numericFieldRequestDTO) {
//        Field existingField = fieldRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'ID : " + id));
//
//        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est le même champ)
//        if (!existingField.getVariableName().equals(numericFieldRequestDTO.variableName()) &&
//            fieldRepository.existsByVariableName(numericFieldRequestDTO.variableName())) {
//            throw new IllegalArgumentException("Un champ avec le nom de variable '" + numericFieldRequestDTO.variableName() + "' existe déjà");
//        }
//
//        FieldMapper.map(numericFieldRequestDTO, existingField);
//        Field updatedField = fieldRepository.save(existingField);
//        return FieldMapper.map(updatedField);
//    }
//
//    @Override
//    public FieldResponseDTO updateByUuid(UUID uuid, FieldRequestDTO numericFieldRequestDTO) {
//        Field existingField = fieldRepository.findByUuid(uuid)
//                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
//
//        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est le même champ)
//        if (!existingField.getVariableName().equals(numericFieldRequestDTO.variableName()) &&
//            fieldRepository.existsByVariableName(numericFieldRequestDTO.variableName())) {
//            throw new IllegalArgumentException("Un champ avec le nom de variable '" + numericFieldRequestDTO.variableName() + "' existe déjà");
//        }
//
//        FieldMapper.map(numericFieldRequestDTO, existingField);
//        Field updatedField = fieldRepository.save(existingField);
//        return FieldMapper.map(updatedField);
//    }

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

    @Override
    public Field getEntityByUuid(UUID uuid) {
        return fieldRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Champ non trouvé avec l'UUID : " + uuid));
    }
}
