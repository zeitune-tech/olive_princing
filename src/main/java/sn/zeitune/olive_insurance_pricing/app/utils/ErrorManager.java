package sn.zeitune.olive_insurance_pricing.app.utils;

import sn.zeitune.olive_insurance_pricing.app.entities.BaseEntity;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Formula;
import sn.zeitune.olive_insurance_pricing.app.exceptions.ResourceNotFoundException;

public class ErrorManager {
    public static ResourceNotFoundException sayEntityNotFound(Class<?> entityClass, String id ) {
        return new ResourceNotFoundException(String.format("%s not found with %s", entityClass.getCanonicalName(), id));
    }
}
