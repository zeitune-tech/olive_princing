package sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.field.FieldRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.VariableItemResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.NumericFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.field.SelectFieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.Field;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.GenericMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.variableItem.VariableItemMapper;
import sn.zeitune.olive_insurance_pricing.enums.TypeOfVariable;

public class FieldMapper extends GenericMapper<Field, FieldRequestDTO, FieldResponseDTO> {

    public static class Utils {

        public static VariableItemResponseDTO newResponseDTO(Field field) {
            if (field instanceof NumericField) return new NumericFieldResponseDTO();
            if (field instanceof SelectField) return new SelectFieldResponseDTO();
            throw  new IllegalArgumentException("Invalid field type: " + field.getClass().getName());
        }

        public static TypeOfVariable getTypeOfField(Field field) {
            if (field instanceof NumericField) return TypeOfVariable.NUMERIC_FIELD;
            if (field instanceof SelectField) return TypeOfVariable.SELECT_FIELD;
            throw new IllegalArgumentException("Unknown field type: " + field.getClass().getName());
        }
    }

    private static FieldMapper instance;

    private FieldMapper() {}

    public static FieldMapper getInstance() {
        if (instance == null) {
            instance = new FieldMapper();
        }
        return instance;
    }

    public void putRequestValue(FieldRequestDTO fieldRequestDTO, Field field) {
        VariableItemMapper.getInstance().putRequestValue(fieldRequestDTO, field);
    }

    public FieldResponseDTO map(Field field) {
        if (field == null) return null;
        return (FieldResponseDTO) VariableItemMapper.getInstance().map(field);
    }

}
