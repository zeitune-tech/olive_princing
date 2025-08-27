package sn.zeitune.olive_insurance_pricing.app.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.mappers.GenericMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.VariableItemMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.GenericRepository;
import sn.zeitune.olive_insurance_pricing.app.services.RetrieveGenericService;

import java.util.List;
import java.util.UUID;


public abstract class RetrieveGenericServiceImpl<V extends VariableItem, REQ, RES> implements RetrieveGenericService<V, REQ, RES> {

    protected final GenericRepository<V> repository;
    private final GenericMapper<V, REQ, RES> mapper;

    public RetrieveGenericServiceImpl(GenericRepository<V> repository, GenericMapper<V, REQ, RES> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RES retrieve(UUID uuid, UUID managementEntity) {
        return mapper.map(getEntityByUuid(uuid, managementEntity));
    }

    @Override
    public List<RES> retrieveByProduct(UUID product, UUID managementEntity) {
        return getAllEntityByProduct(product, managementEntity)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public List<RES> retrieveAll(UUID managementEntity) {
        return getAllEntityByManagementEntity(managementEntity)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public Page<RES> retrieveAll(UUID managementEntity, Pageable pageable) {
        return getAllEntityByManagementEntity(managementEntity, pageable)
                .map(mapper::map);
    }

    @Override
    public boolean exists(UUID uuid, UUID managementEntity) {
        return repository.existsByUuidAndManagementEntity(uuid, managementEntity);
    }

    @Override
    public V getEntityByUuid(UUID uuid, UUID managementEntity){
        return repository.findByUuidAndManagementEntity(uuid, managementEntity)
                .orElseThrow(() -> new RuntimeException(String.format("Entity with UUID %s not found", uuid)));
    }

    @Override
    public List<V> getAllEntityByProduct(UUID product, UUID managementEntity) {
        return repository.findAllByManagementEntity(managementEntity)
                .stream()
                .filter( entity -> (entity).getPricingType().getProduct().toString().equals(product.toString()) )
                .toList();
    }

    @Override
    public Page<V> getAllEntityByManagementEntity(UUID managementEntity, Pageable pageable) {
        return repository.findAllByManagementEntity(managementEntity, pageable);
    }

    @Override
    public List<V> getAllEntityByManagementEntity(UUID managementEntity) {
        return repository.findAllByManagementEntity(managementEntity);
    }

}