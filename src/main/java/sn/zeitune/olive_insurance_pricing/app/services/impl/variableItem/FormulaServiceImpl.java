package sn.zeitune.olive_insurance_pricing.app.services.impl.variableItem;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Formula;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.FormulaMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.FormulaRepository;
import sn.zeitune.olive_insurance_pricing.app.repositories.variableItem.VariableItemRepository;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.FormulaService;
import sn.zeitune.olive_insurance_pricing.app.services.variableItem.VariableItemPreparationService;
import sn.zeitune.olive_insurance_pricing.app.utils.ErrorManager;

import java.util.UUID;


@Service
@Transactional
public class FormulaServiceImpl extends RetrieveGenericServiceImpl<Formula, FormulaRequestDTO, FormulaResponseDTO> implements FormulaService {

    private final FormulaRepository formulaRepository;
    private final VariableItemRepository variableItemRepository;
    private final VariableItemPreparationService variableItemPreparationService;
    private final FormulaMapper mapper;

    public FormulaServiceImpl(FormulaRepository formulaRepository,
                              VariableItemRepository variableItemRepository,
                              VariableItemPreparationService variableItemPreparationService
    ) {
        super(formulaRepository, FormulaMapper.getInstance());
        this.formulaRepository = formulaRepository;
        this.variableItemRepository = variableItemRepository;
        this.variableItemPreparationService = variableItemPreparationService;
        this.mapper = FormulaMapper.getInstance();
    }

    @Override
    public FormulaResponseDTO create(FormulaRequestDTO formulaRequestDTO, UUID managementEntity) {
        Formula formula = (Formula) variableItemPreparationService.prepareCreationEntity(formulaRequestDTO, new Formula(), managementEntity);
        mapper.putRequestValue(formulaRequestDTO, formula);
        formula.setDateEffective(formula.getDateEffective());
        for (UUID variableId : formulaRequestDTO.getVariables())
            formula.getVariables().add(variableItemRepository.findByUuidAndDeletedIsFalse(variableId).orElseThrow(() -> ErrorManager.sayEntityNotFound(Formula.class, "uuid " + variableId)));
        // TODO: Vérifier la validité de l'expression
        formula = formulaRepository.save(formula);
        return mapper.map(formula);
    }

    @Override
    public FormulaResponseDTO update(UUID uuid, FormulaRequestDTO formulaRequestDTO, UUID managementEntity) {
        Formula existingFormula = getEntityByUuid(uuid);
        existingFormula = (Formula) variableItemPreparationService.prepareUpdatingEntity(formulaRequestDTO, existingFormula, managementEntity);
        mapper.putRequestValue(formulaRequestDTO, existingFormula);
        Formula updatedFormula = formulaRepository.save(existingFormula);
        return mapper.map(updatedFormula);
    }

    @Override
    public void delete(UUID uuid, UUID managementEntity) {
        Formula formula = getEntityByUuid(uuid);
        formula.setVariables(null);
        formula.setPricingType(null);
        formulaRepository.delete(formula);
    }

    private Formula getEntityByUuid(UUID uuid) {
        return formulaRepository.findByUuidAndDeletedIsFalse(uuid)
                .orElseThrow(() -> ErrorManager.sayEntityNotFound(Formula.class, "id: " + uuid));
    }

}
