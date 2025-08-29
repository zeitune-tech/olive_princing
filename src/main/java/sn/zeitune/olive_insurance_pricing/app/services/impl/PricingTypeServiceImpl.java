package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.PricingTypeRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.PricingTypeResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.exceptions.ResourceNotFoundException;
import sn.zeitune.olive_insurance_pricing.app.mappers.PricingTypeDetailedMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.PricingTypeMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.FormulaRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.PricingTypeRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.VariableItemRepository;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PricingTypeServiceImpl implements PricingTypeService {

    private final PricingTypeRepository pricingTypeRepository;
    private final VariableItemRepository variableItemRepository;

    @Override
    public PricingTypeResponseDTO create(PricingTypeRequestDTO request, UUID managementEntity) {
        if (pricingTypeRepository.existsByNameAndProductAndDeletedIsFalse(request.getName(), request.getProduct())) {
            throw new ValidationException("Un type de tarification avec ce nom existe déjà pour ce produit");
        }

        PricingType pricingType = new PricingType();
        boolean isPricingTypeRepositoryEmpty = pricingTypeRepository.findAllByManagementEntityAndProductAndDeletedIsFalse(managementEntity, request.getProduct()).isEmpty();
        if (!isPricingTypeRepositoryEmpty) {
            PricingType effectivePricingType = pricingTypeRepository.findByManagementEntityAndProductAndEffectiveIsTrue(managementEntity, request.getProduct())
                    .orElseThrow(() -> new ResourceNotFoundException("Aucun type de tarification effectif trouvé"));

            System.err.println("effectivePricingType.getDateEffective().isBefore(request.getDateEffective()): " + effectivePricingType.getDateEffective().isBefore(request.getDateEffective()));
            System.err.println("effectivePricingType.getDateEffective().isEqual(request.getDateEffective()): " + effectivePricingType.getDateEffective().isEqual(request.getDateEffective()));

            if ( effectivePricingType.getDateEffective().isBefore(request.getDateEffective()) || effectivePricingType.getDateEffective().isEqual(request.getDateEffective()) ) {
                effectivePricingType.setEffective(false);
                pricingTypeRepository.save(effectivePricingType);
                pricingType.setEffective(true);
            } else {
                pricingType.setEffective(false);
            }
        }else {
            System.err.println("is empty");
            pricingType.setEffective(true);
        }

        PricingTypeMapper.putRequestValue(request, pricingType);
        pricingType.setManagementEntity(managementEntity);
        return PricingTypeMapper.map(pricingTypeRepository.save(pricingType), false);
    }

    @Override
    public PricingTypeResponseDTO update(UUID id, PricingTypeRequestDTO request, UUID managementEntity) {
        PricingType updatingPricingType = pricingTypeRepository.findByUuidAndManagementEntityAndDeletedIsFalse(id, managementEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Type de tarification non trouvé"));

        PricingType effectivePricingType = pricingTypeRepository.findByManagementEntityAndProductAndEffectiveIsTrue(managementEntity, request.getProduct())
                        .orElseThrow(() -> new ResourceNotFoundException("Aucun type de tarification effectif trouvé"));

        if (!updatingPricingType.getUuid().equals(effectivePricingType.getUuid())) {
            if ( effectivePricingType.getDateEffective().isBefore(request.getDateEffective()) || effectivePricingType.getDateEffective().isEqual(request.getDateEffective()) ) {
                effectivePricingType.setEffective(false);
                updatingPricingType.setEffective(true);
                pricingTypeRepository.save(effectivePricingType);
            } else {
                updatingPricingType.setEffective(false);
            }
        }

        PricingTypeMapper.putRequestValue(request, updatingPricingType);
        return PricingTypeMapper.map(pricingTypeRepository.save(updatingPricingType), false);
    }

    @Override
    public void delete(UUID id, UUID managementEntity) {
        PricingType pricingType = pricingTypeRepository.findByUuidAndManagementEntityAndDeletedIsFalse(id, managementEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Type de tarification non trouvé"));
        pricingType.setEffective(false);
        pricingType.setDeleted(true);
        pricingTypeRepository.save(pricingType);
    }

    @Override
    public PricingTypeResponseDTO getById(UUID id, UUID managementEntity) {
        PricingType pricingType = pricingTypeRepository.findByUuidAndManagementEntityAndDeletedIsFalse(id, managementEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Type de tarification non trouvé"));
        return PricingTypeMapper.map(pricingType, false);
    }

    @Override
    public PricingTypeResponseDTO getDetailedById(UUID id, UUID managementEntity) {
        PricingType pricingType = pricingTypeRepository.findByUuidAndManagementEntityAndDeletedIsFalse(id, managementEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Type de tarification non trouvé"));

        return PricingTypeDetailedMapper.map(pricingType, variableItemRepository.findAllByPricingTypeAndManagementEntity(pricingType, managementEntity));
    }

    @Override
    public List<PricingTypeResponseDTO> getAllActiveByProduct(UUID productId, UUID managementEntity) {
        return pricingTypeRepository.findAllByManagementEntityAndProductAndDeletedIsFalse(managementEntity, productId)
                .stream()
                .map(pricingType ->  PricingTypeMapper.map(pricingType, false))
                .collect(Collectors.toList());
    }

    @Override
    public PricingTypeResponseDTO getPricingTypeForProduct(UUID productId, UUID managementEntity) {
        PricingType pricingType = pricingTypeRepository.findByManagementEntityAndProductAndEffectiveIsTrue(managementEntity, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Aucun type de tarification effectif trouvé pour ce produit"));
        return PricingTypeMapper.map(pricingType, false);

    }

    @Override
    public Page<PricingTypeResponseDTO> getAllActive(Pageable pageable, UUID managementEntity) {
        return pricingTypeRepository.findAllByManagementEntityAndDeletedIsFalse(managementEntity, pageable)
                .map(pricingType ->  PricingTypeMapper.map(pricingType, false));

    }

    @Override
    public PricingType getEntityById(UUID id, UUID managementEntity) {
        System.err.println("getEntityById id: " + id);
        System.err.println("getEntityById ma: " + managementEntity);
        return pricingTypeRepository.findByUuidAndManagementEntityAndDeletedIsFalse(id, managementEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Type de tarification non trouvé"));
    }
}
