package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.PricingType;
import sn.zeitune.olive_insurance_pricing.app.mappers.FormulaMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.FormulaRepository;
import sn.zeitune.olive_insurance_pricing.app.services.FormulaService;
import sn.zeitune.olive_insurance_pricing.app.services.PricingTypeService;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemService;
import sn.zeitune.olive_insurance_pricing.app.utils.ExpressionParser;

import java.util.List;
import java.util.UUID;

import static sn.zeitune.olive_insurance_pricing.app.utils.ExpressionParser.parseExpression;

@Service
@Transactional
@RequiredArgsConstructor
public class FormulaServiceImpl implements FormulaService {

    private final FormulaRepository formulaRepository;
    private final VariableItemService variableItemService;
    private final PricingTypeService pricingTypeService;

    @Override
    public FormulaResponseDTO create(FormulaRequestDTO formulaRequestDTO) {
        // Vérifier si une formule avec le même nom de variable existe déjà
        if (formulaRepository.existsByVariableName(formulaRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Une formule avec le nom de variable '" + formulaRequestDTO.getVariableName() + "' existe déjà");
        }
        
        Formula formula = FormulaMapper.map(formulaRequestDTO, new Formula());

        for (UUID variableId : formulaRequestDTO.getVariables()) {
            if (variableItemService.existsByUuid(variableId)) {
                formula.getVariables().add(variableItemService.getEntityByUuid(variableId));
            } else {
                throw new EntityNotFoundException("Variable non trouvée avec l'UUID : " + variableId);
            }
        }

        formula.setPricingType( pricingTypeService.getEntityById(formulaRequestDTO.getPricingType()) );

        // Vérifier la validité de l'expression
        // TODO
        formula = formulaRepository.save(formula);
        return FormulaMapper.map(formula);
    }

    @Override
    public FormulaResponseDTO findByUuid(UUID uuid) {
        Formula formula = formulaRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Formule non trouvée avec l'UUID : " + uuid));
        return FormulaMapper.map(formula);
    }

    @Override
    public List<FormulaResponseDTO> findAll() {
        return formulaRepository.findAll()
                .stream()
                .map(FormulaMapper::map)
                .toList();
    }

    @Override
    public Page<FormulaResponseDTO> findAll(Pageable pageable) {
        return formulaRepository.findAll(pageable)
                .map(FormulaMapper::map);
    }

    @Override
    public List<FormulaResponseDTO> findByProduct(UUID product) {
        return formulaRepository.findByProduct(product)
                .stream()
                .map(FormulaMapper::map)
                .toList();
    }

    @Override
    public List<FormulaResponseDTO> searchByLabel(String label) {
        return formulaRepository.findByLabelContainingIgnoreCase(label)
                .stream()
                .map(FormulaMapper::map)
                .toList();
    }

    @Override
    public List<FormulaResponseDTO> searchByExpression(String expression) {
        return formulaRepository.findByExpressionContainingIgnoreCase(expression)
                .stream()
                .map(FormulaMapper::map)
                .toList();
    }

    @Override
    public FormulaResponseDTO updateByUuid(UUID uuid, FormulaRequestDTO formulaRequestDTO) {
        Formula existingFormula = formulaRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Formule non trouvée avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est la même formule)
        if (!existingFormula.getVariableName().equals(formulaRequestDTO.getVariableName()) &&
            formulaRepository.existsByVariableName(formulaRequestDTO.getVariableName())) {
            throw new IllegalArgumentException("Une formule avec le nom de variable '" + formulaRequestDTO.getVariableName() + "' existe déjà");
        }

        update(existingFormula, formulaRequestDTO);
        Formula updatedFormula = formulaRepository.save(existingFormula);
        return FormulaMapper.map(updatedFormula);
    }

    private void update (Formula formula, FormulaRequestDTO formulaRequestDTO) {
        formula.setLabel(formulaRequestDTO.getLabel());
        formula.setDescription(formulaRequestDTO.getDescription());
        formula.setVariableName(formulaRequestDTO.getVariableName());
        formula.setExpression(formulaRequestDTO.getExpression());
        formula.setToReturn(formulaRequestDTO.getToReturn());
        formula.setProduct(formulaRequestDTO.getProduct());
        formula.setBranch(formulaRequestDTO.getBranch());
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        Formula formula = formulaRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Formule non trouvée avec l'UUID : " + uuid));
        formula.setVariables(null);
        formula.setPricingType(null);
        formulaRepository.delete(formula);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return formulaRepository.existsByUuid(uuid);
    }

    @Override
    public Formula getEntityByUuid(UUID uuid) {
        Formula formula = formulaRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Formule non trouvée avec l'UUID : " + uuid));
        return formula;
    }
}
