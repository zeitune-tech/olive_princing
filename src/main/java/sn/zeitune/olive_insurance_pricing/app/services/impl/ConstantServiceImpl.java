package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.mappers.ConstantMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.ConstantRepository;
import sn.zeitune.olive_insurance_pricing.app.services.ConstantService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ConstantServiceImpl implements ConstantService {

    private final ConstantRepository constantRepository;

    @Override
    public ConstantResponseDTO create(ConstantRequestDTO constantRequestDTO) {
        // Vérifier si une constante avec le même nom de variable existe déjà
        if (constantRepository.existsByVariableName(constantRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Une constante avec le nom de variable '" + constantRequestDTO.getVariableName() + "' existe déjà");
        }
        
        Constant constant = ConstantMapper.map(constantRequestDTO);
        constant = constantRepository.save(constant);
        return ConstantMapper.map(constant);
    }

    @Override
    public ConstantResponseDTO findById(Long id) {
        Constant constant = constantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Constante non trouvée avec l'ID : " + id));
        return ConstantMapper.map(constant);
    }

    @Override
    public ConstantResponseDTO findByUuid(UUID uuid) {
        Constant constant = constantRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Constante non trouvée avec l'UUID : " + uuid));
        return ConstantMapper.map(constant);
    }

    @Override
    public List<ConstantResponseDTO> findAll() {
        return constantRepository.findAll()
                .stream()
                .map(ConstantMapper::map)
                .toList();
    }

    @Override
    public Page<ConstantResponseDTO> findAll(Pageable pageable) {
        return constantRepository.findAll(pageable)
                .map(ConstantMapper::map);
    }

    @Override
    public List<ConstantResponseDTO> findByValue(Double value) {
        return constantRepository.findAllByValue(value)
                .stream()
                .map(ConstantMapper::map)
                .toList();
    }

    @Override
    public List<ConstantResponseDTO> findByValueRange(Double minValue, Double maxValue) {
        if (minValue > maxValue) {
            throw new IllegalArgumentException("La valeur minimale ne peut pas être supérieure à la valeur maximale");
        }
        return constantRepository.findByValueBetween(minValue, maxValue)
                .stream()
                .map(ConstantMapper::map)
                .toList();
    }

    @Override
    public List<ConstantResponseDTO> findByProduct(UUID product) {
        return constantRepository.findByProduct(product)
                .stream()
                .map(ConstantMapper::map)
                .toList();
    }

    @Override
    public List<ConstantResponseDTO> searchByLabel(String label) {
        return constantRepository.findByLabelContainingIgnoreCase(label)
                .stream()
                .map(ConstantMapper::map)
                .toList();
    }

    @Override
    public ConstantResponseDTO update(Long id, ConstantRequestDTO constantRequestDTO) {
        Constant existingConstant = constantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Constante non trouvée avec l'ID : " + id));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est la même constante)
        if (!existingConstant.getVariableName().equals(constantRequestDTO.getVariableName()) &&
            constantRepository.existsByVariableName(constantRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Une constante avec le nom de variable '" + constantRequestDTO.getVariableName() + "' existe déjà");
        }
        
        ConstantMapper.map(constantRequestDTO, existingConstant);
        Constant updatedConstant = constantRepository.save(existingConstant);
        return ConstantMapper.map(updatedConstant);
    }

    @Override
    public ConstantResponseDTO updateByUuid(UUID uuid, ConstantRequestDTO constantRequestDTO) {
        Constant existingConstant = constantRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Constante non trouvée avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est la même constante)
        if (!existingConstant.getVariableName().equals(constantRequestDTO.getVariableName()) &&
            constantRepository.existsByVariableName(constantRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Une constante avec le nom de variable '" + constantRequestDTO.getVariableName() + "' existe déjà");
        }
        
        ConstantMapper.map(constantRequestDTO, existingConstant);
        Constant updatedConstant = constantRepository.save(existingConstant);
        return ConstantMapper.map(updatedConstant);
    }

    @Override
    public void delete(Long id) {
        if (!constantRepository.existsById(id)) {
            throw new EntityNotFoundException("Constante non trouvée avec l'ID : " + id);
        }
        constantRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        Constant constant = constantRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Constante non trouvée avec l'UUID : " + uuid));
        constantRepository.delete(constant);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return constantRepository.existsByUuid(uuid);
    }
}
