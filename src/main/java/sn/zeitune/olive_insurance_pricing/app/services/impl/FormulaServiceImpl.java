package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.mappers.FormulaMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.FormulaRepository;
import sn.zeitune.olive_insurance_pricing.app.services.FormulaService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FormulaServiceImpl implements FormulaService {

    private final FormulaRepository formulaRepository;

    @Override
    public FormulaResponseDTO create(FormulaRequestDTO formulaRequestDTO) {
        // Vérifier si une formule avec le même nom de variable existe déjà
        if (formulaRepository.existsByVariableName(formulaRequestDTO.variableName())) {
            throw new IllegalArgumentException("Une formule avec le nom de variable '" + formulaRequestDTO.variableName() + "' existe déjà");
        }
        
        Formula formula = FormulaMapper.map(formulaRequestDTO);
        formula = formulaRepository.save(formula);
        return FormulaMapper.map(formula);
    }

    @Override
    public FormulaResponseDTO findById(Long id) {
        Formula formula = formulaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formule non trouvée avec l'ID : " + id));
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
    public FormulaResponseDTO update(Long id, FormulaRequestDTO formulaRequestDTO) {
        Formula existingFormula = formulaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Formule non trouvée avec l'ID : " + id));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est la même formule)
        if (!existingFormula.getVariableName().equals(formulaRequestDTO.variableName()) &&
            formulaRepository.existsByVariableName(formulaRequestDTO.variableName())) {
            throw new IllegalArgumentException("Une formule avec le nom de variable '" + formulaRequestDTO.variableName() + "' existe déjà");
        }

        update(existingFormula, formulaRequestDTO);
        Formula updatedFormula = formulaRepository.save(existingFormula);
        return FormulaMapper.map(updatedFormula);
    }

    @Override
    public FormulaResponseDTO updateByUuid(UUID uuid, FormulaRequestDTO formulaRequestDTO) {
        Formula existingFormula = formulaRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Formule non trouvée avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est la même formule)
        if (!existingFormula.getVariableName().equals(formulaRequestDTO.variableName()) &&
            formulaRepository.existsByVariableName(formulaRequestDTO.variableName())) {
            throw new IllegalArgumentException("Une formule avec le nom de variable '" + formulaRequestDTO.variableName() + "' existe déjà");
        }

        update(existingFormula, formulaRequestDTO);
        Formula updatedFormula = formulaRepository.save(existingFormula);
        return FormulaMapper.map(updatedFormula);
    }

    private void update (Formula formula, FormulaRequestDTO formulaRequestDTO) {
        formula.setLabel(formulaRequestDTO.label());
        formula.setDescription(formulaRequestDTO.description());
        formula.setVariableName(formulaRequestDTO.variableName());
        formula.setExpression(formulaRequestDTO.expression());
        formula.setToReturn(formulaRequestDTO.toReturn());
        formula.setManagementEntity(formulaRequestDTO.managementEntity());
        formula.setProduct(formulaRequestDTO.product());
        formula.setCoverage(formulaRequestDTO.coverage());
    }

    @Override
    public void delete(Long id) {
        if (!formulaRepository.existsById(id)) {
            throw new EntityNotFoundException("Formule non trouvée avec l'ID : " + id);
        }
        formulaRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        Formula formula = formulaRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Formule non trouvée avec l'UUID : " + uuid));
        formulaRepository.delete(formula);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return formulaRepository.existsByUuid(uuid);
    }
}
