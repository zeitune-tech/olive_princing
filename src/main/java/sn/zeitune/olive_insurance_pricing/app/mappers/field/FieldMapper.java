package sn.zeitune.olive_insurance_pricing.app.mappers.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.responses.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.Field;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.ConstantMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.FormulaMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.VariableItemMapper;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class FieldMapper {

    private static FieldResponseDTO dtoForField (Field field) {
        if (field instanceof NumericField) return NumericFieldMapper.map((NumericField) field);
        if (field instanceof SelectField) return SelectFieldMapper.map((SelectField) field);
        return null;
    }

    public static FieldResponseDTO map(Field field) {
        return dtoForField(field);
    }

    public static VariableItemResponseDTO createResponseDTO(Field field) {
        if (field instanceof NumericField) return new NumericFieldResponseDTO();
        if (field instanceof SelectField) return new SelectFieldResponseDTO();
        return null;
    }

    public static TypeOfVariable getTypeOfField(Field field) {
        if (field instanceof NumericField) return TypeOfVariable.NUMERIC_FIELD;
        if (field instanceof SelectField) return TypeOfVariable.SELECT_FIELD;
        throw new IllegalArgumentException("Unknown field type: " + field.getClass().getName());
    }
}
