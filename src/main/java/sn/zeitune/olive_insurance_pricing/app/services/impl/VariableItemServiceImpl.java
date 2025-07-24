package sn.zeitune.olive_insurance_pricing.app.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.repositories.VariableItemRepository;
import sn.zeitune.olive_insurance_pricing.app.services.VariableItemService;

@Service
@Transactional
@RequiredArgsConstructor
public class VariableItemServiceImpl implements VariableItemService {

    private final VariableItemRepository variableItemRepository;

    @Override
    public VariableItem findByVariableName(String variableName) {
        return variableItemRepository.findByVariableName(variableName)
                .orElseThrow(() -> new RuntimeException("Variable item not found with name: " + variableName));
    }
}
