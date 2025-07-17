package sn.zeitune.olive_insurance_pricing.app.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.RuleRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.RuleResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Rule;
import sn.zeitune.olive_insurance_pricing.app.mappers.RuleMapper;
import sn.zeitune.olive_insurance_pricing.app.repositories.RuleRepository;
import sn.zeitune.olive_insurance_pricing.app.services.RuleService;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;

    @Override
    public RuleResponseDTO create(RuleRequestDTO ruleRequestDTO) {
        // Vérifier si une règle avec le même nom de variable existe déjà
        if (ruleRepository.existsByVariableName(ruleRequestDTO.variableName())) {
            throw new IllegalArgumentException("Une règle avec le nom de variable '" + ruleRequestDTO.variableName() + "' existe déjà");
        }

        Rule rule = RuleMapper.map(ruleRequestDTO, new Rule());
        rule = ruleRepository.save(rule);
        return RuleMapper.map(rule);
    }

    @Override
    public RuleResponseDTO findById(Long id) {
        Rule rule = ruleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Règle non trouvée avec l'ID : " + id));
        return RuleMapper.map(rule);
    }

    @Override
    public RuleResponseDTO findByUuid(UUID uuid) {
        Rule rule = ruleRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Règle non trouvée avec l'UUID : " + uuid));
        return RuleMapper.map(rule);
    }

    @Override
    public List<RuleResponseDTO> findAll() {
        return ruleRepository.findAll()
                .stream()
                .map(RuleMapper::map)
                .toList();
    }

    @Override
    public Page<RuleResponseDTO> findAll(Pageable pageable) {
        return ruleRepository.findAll(pageable)
                .map(RuleMapper::map);
    }

    @Override
    public List<RuleResponseDTO> findByValue(Double value) {
        return ruleRepository.findByValue(value)
                .stream()
                .map(RuleMapper::map)
                .toList();
    }

    @Override
    public List<RuleResponseDTO> findByProduct(UUID product) {
        return ruleRepository.findByProduct(product)
                .stream()
                .map(RuleMapper::map)
                .toList();
    }

    @Override
    public List<RuleResponseDTO> searchByLabel(String label) {
        return ruleRepository.findByLabelContainingIgnoreCase(label)
                .stream()
                .map(RuleMapper::map)
                .toList();
    }

    @Override
    public RuleResponseDTO update(Long id, RuleRequestDTO ruleRequestDTO) {
        Rule existingRule = ruleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Règle non trouvée avec l'ID : " + id));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est la même règle)
        if (!existingRule.getVariableName().equals(ruleRequestDTO.variableName()) &&
            ruleRepository.existsByVariableName(ruleRequestDTO.variableName())) {
            throw new IllegalArgumentException("Une règle avec le nom de variable '" + ruleRequestDTO.variableName() + "' existe déjà");
        }
        
        RuleMapper.map(ruleRequestDTO, existingRule);
        Rule updatedRule = ruleRepository.save(existingRule);
        return RuleMapper.map(updatedRule);
    }

    @Override
    public RuleResponseDTO updateByUuid(UUID uuid, RuleRequestDTO ruleRequestDTO) {
        Rule existingRule = ruleRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Règle non trouvée avec l'UUID : " + uuid));
        
        // Vérifier si le nouveau nom de variable existe déjà (sauf si c'est la même règle)
        if (!existingRule.getVariableName().equals(ruleRequestDTO.variableName()) &&
            ruleRepository.existsByVariableName(ruleRequestDTO.variableName())) {
            throw new IllegalArgumentException("Une règle avec le nom de variable '" + ruleRequestDTO.variableName() + "' existe déjà");
        }
        
        RuleMapper.map(ruleRequestDTO, existingRule);
        Rule updatedRule = ruleRepository.save(existingRule);
        return RuleMapper.map(updatedRule);
    }

    @Override
    public void delete(Long id) {
        if (!ruleRepository.existsById(id)) {
            throw new EntityNotFoundException("Règle non trouvée avec l'ID : " + id);
        }
        ruleRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        Rule rule = ruleRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Règle non trouvée avec l'UUID : " + uuid));
        ruleRepository.delete(rule);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return ruleRepository.existsByUuid(uuid);
    }
}
