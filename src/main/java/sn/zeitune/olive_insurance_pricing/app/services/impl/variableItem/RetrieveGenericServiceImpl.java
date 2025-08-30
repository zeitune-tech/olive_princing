package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.GenericMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.GenericRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.RetrieveGenericService;

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
    public RES retrieveActive(UUID uuid, UUID managementEntity) {
        return mapper.map(repository
                .findByUuidAndDeletedIsFalse(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Entity with uuid " + uuid + " not found"))
        );
    }

//    @Override
//    public List<RES> retrieveAllActiveByProduct(UUID product, UUID managementEntity, UUID pricingTypeId, UUID coverage) {
//        return repository.findAllByProductAndManagementEntityAndDeletedIsFalse(product, managementEntity)
//                .stream()
//                .map(mapper::map)
//                .toList();
//    }

    @Override
    public List<RES> retrieveAllActive(UUID managementEntity) {
        return repository.findAllByManagementEntityAndDeletedIsFalse(managementEntity)
                .stream()
                .map(mapper::map)
                .toList();
    }

    @Override
    public Page<RES> retrieveAllActive(UUID managementEntity, Pageable pageable) {
        return repository.findAllByManagementEntityAndDeletedIsFalse(managementEntity, pageable)
                .map(mapper::map);
    }

}