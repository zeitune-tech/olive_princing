package sn.zeitune.olive_insurance_pricing.app.services;

import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;

public interface VariableItemService {
    VariableItem findByVariableName (String variableName);
}
