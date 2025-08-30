package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.clients.AdministrationClient;
import sn.zeitune.olive_insurance_pricing.app.dtos.externals.ManagementEntityExternalDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.VariableItemRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.VariableItemRepository;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.VariableItemPreparationService;
import sn.zeitune.olive_insurance_pricing.app.utils.ErrorManager;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public  class VariableItemPreparationServiceImpl implements VariableItemPreparationService {

    private final VariableItemRepository variableItemRepository;
    private final AdministrationClient administrationClient;
    private final PricingTypeService pricingTypeService;

    private boolean isExistSameVariableName(VariableItemRequestDTO variableItemRequestDTO, UUID managementEntity) {
        if (variableItemRequestDTO.getVariableName() == null || variableItemRequestDTO.getVariableName().isBlank()) {
            throw new IllegalArgumentException("Le nom de la variable ne peut pas être nul ou vide");
        }
        Optional<VariableItem> variableItemWithSameCoverage = variableItemRepository.findByVariableNameAndManagementEntityAndPricingType_UuidAndCoverageAndDeletedIsFalse(variableItemRequestDTO.getVariableName(), managementEntity, variableItemRequestDTO.getPricingType(), variableItemRequestDTO.getCoverage());
        return variableItemWithSameCoverage.isPresent();
    }

    @Override
    public VariableItem prepareCreationEntity(VariableItemRequestDTO variableItemRequestDTO,  VariableItem variableItem, UUID managementEntity) {
        // Vérifier si une constante avec le même nom de variable existe déjà
        if (isExistSameVariableName(variableItemRequestDTO, managementEntity)) {
            throw new IllegalArgumentException("Une variable avec le nom de variable '" + variableItemRequestDTO.getVariableName() + "' existe déjà");
        }
        // Vérifier si l'entité de gestion existe
        administrationClient.findManagementEntityByUuid(managementEntity).orElseThrow(
                () -> ErrorManager.sayEntityNotFound(ManagementEntityExternalDTO.class,"l'UUID : " + managementEntity)
        );
        variableItem.setManagementEntity(managementEntity);
        variableItem.setPricingType( pricingTypeService.getEntityById(variableItemRequestDTO.getPricingType(), managementEntity) );
        return variableItem;
    }


    @Override
    public VariableItem prepareUpdatingEntity(VariableItemRequestDTO variableItemRequestDTO , VariableItem variableItem, UUID managementEntity) {
        // Vérifier si le nom de la variable est modifié et s'il existe déjà une variable avec le même nom de variable
        if(!variableItem.getVariableName().equals(variableItemRequestDTO.getVariableName())){
           if(isExistSameVariableName(variableItemRequestDTO, managementEntity)){
               throw new IllegalArgumentException("Une variable avec le nom de variable '" + variableItem.getVariableName() + "' existe déjà");
           }
       }
       // TODO: Réfléchir si on doit vérifier l'existence de l'entité de gestion
       variableItem.setPricingType( pricingTypeService.getEntityById(variableItemRequestDTO.getPricingType(), managementEntity) );
       return variableItem;
    }

}