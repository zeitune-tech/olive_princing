package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public PricingTypeResponseDTO create(PricingTypeRequestDTO request) {
        if (pricingTypeRepository.existsByNameAndProduct(request.getName(), request.getProduct())) {
            throw new ValidationException("Un type de tarification avec ce nom existe déjà pour ce produit");
        }
        PricingType pricingType = new PricingType();
        PricingTypeMapper.putRequestValue(request, pricingType);
        return PricingTypeMapper.map(pricingTypeRepository.save(pricingType), false);
    }

    @Override
    public PricingTypeResponseDTO update(UUID id, PricingTypeRequestDTO request) {
        PricingType pricingType = pricingTypeRepository.findByUuid((id))
                .orElseThrow(() -> new ResourceNotFoundException("Type de tarification non trouvé"));
        PricingTypeMapper.putRequestValue(request, pricingType);
        return PricingTypeMapper.map(pricingTypeRepository.save(pricingType), false);
    }

    @Override
    public void delete(UUID id) {
        if (!pricingTypeRepository.existsByUuid(id)) {
            throw new ResourceNotFoundException("Type de tarification non trouvé");
        }
        pricingTypeRepository.deleteByUuid(id);
    }

    @Override
    public PricingTypeResponseDTO getById(UUID id) {
        PricingType pricingType = pricingTypeRepository.findByUuid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type de tarification non trouvé"));
        return PricingTypeMapper.map(pricingType, false);
    }

    @Override
    public PricingTypeResponseDTO getDetailedById(UUID id, UUID managementEntity) {
        PricingType pricingType = pricingTypeRepository.findByUuid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type de tarification non trouvé"));
        return PricingTypeDetailedMapper.map(pricingType, variableItemRepository.findAllByPricingTypeAndManagementEntity(pricingType, managementEntity));
    }

    @Override
    public List<PricingTypeResponseDTO> getByProduct(UUID productId) {
        return pricingTypeRepository.findByProduct(productId)
                .stream()
                .map(pricingType ->  PricingTypeMapper.map(pricingType, false))
                .collect(Collectors.toList());
    }

    @Override
    public Page<PricingTypeResponseDTO> getAll(Pageable pageable) {
        return pricingTypeRepository.findAll(pageable)
                .map(pricingType ->  PricingTypeMapper.map(pricingType, false));

    }

    @Override
    public PricingType getEntityById(UUID id) {
        return pricingTypeRepository.findByUuid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Type de tarification non trouvé"));
    }
}
