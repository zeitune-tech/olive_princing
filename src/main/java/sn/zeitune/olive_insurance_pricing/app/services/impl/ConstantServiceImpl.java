package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.clients.AdministrationClient;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.ConstantResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.mappers.ConstantMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.ConstantRepository;
import sn.zeitune.olive_insurance_pricing.app.services.ConstantService;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ConstantServiceImpl implements ConstantService {

    private final ConstantRepository constantRepository;
    private final PricingTypeService pricingTypeService;
    private final AdministrationClient administrationClient;

    @Override
    public ConstantResponseDTO create(ConstantRequestDTO constantRequestDTO, UUID managementEntity) {
        // Vérifier si une constante avec le même nom de variable existe déjà
        if (constantRepository.existsByVariableName(constantRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Une constante avec le nom de variable '" + constantRequestDTO.getVariableName() + "' existe déjà");
        }
        // Vérifier si l'entité de gestion existe
        administrationClient.findManagementEntityByUuid(managementEntity).orElseThrow(
                () -> new EntityNotFoundException("Entité de gestion non trouvée avec l'UUID : " + managementEntity)
        );
        // Persister la constante
        Constant constant = new Constant();
        ConstantMapper.putRequestValue(constantRequestDTO, constant);
        constant.setManagementEntity(managementEntity);
        constant.setPricingType( pricingTypeService.getEntityById(constantRequestDTO.getPricingType()) );
        return ConstantMapper.map(constantRepository.save(constant));
    }

    @Override
    public ConstantResponseDTO findByUuid(UUID uuid) {
        Constant constant = constantRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Constante non trouvée avec l'UUID : " + uuid));
        return ConstantMapper.map(constant);
    }

    @Override
    public List<ConstantResponseDTO> findAll(UUID managementEntity) {
        return constantRepository.findAllByManagementEntity(managementEntity)
                .stream()
                .map(ConstantMapper::map)
                .toList();
    }

    @Override
    public Page<ConstantResponseDTO> findAll(Pageable pageable, UUID managementEntity) {
        return constantRepository.findAllByManagementEntity(managementEntity, pageable)
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
    public ConstantResponseDTO updateByUuid(UUID uuid, ConstantRequestDTO constantRequestDTO) {
        Constant existingConstant = constantRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Constante non trouvée avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est la même constante)
        if (!existingConstant.getVariableName().equals(constantRequestDTO.getVariableName()) &&
            constantRepository.existsByVariableName(constantRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Une constante avec le nom de variable '" + constantRequestDTO.getVariableName() + "' existe déjà");
        }
        
        ConstantMapper.putRequestValue(constantRequestDTO, existingConstant);
        existingConstant.setPricingType(pricingTypeService.getEntityById(constantRequestDTO.getPricingType()));
        return ConstantMapper.map(constantRepository.save(existingConstant));
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        if (!existsByUuid(uuid))
            throw new EntityNotFoundException("Constante non trouvée avec l'UUID : " + uuid);

        constantRepository.findByUuid(uuid).ifPresent(
                constant -> {
                    constant.setPricingType(null);
                    constantRepository.delete(constant);
                }
        );
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return constantRepository.existsByUuid(uuid);
    }
}
