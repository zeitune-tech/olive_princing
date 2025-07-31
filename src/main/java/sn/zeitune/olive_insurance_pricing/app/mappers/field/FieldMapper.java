package sn.zeitune.olive_insurance_pricing.app.mappers.field;

import sn.zeitune.olive_insurance_pricing.app.dtos.responses.field.FieldResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.Constant;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.Field;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;
import sn.zeitune.olive_insurance_pricing.app.entities.field.SelectField;
import sn.zeitune.olive_insurance_pricing.app.mappers.ConstantMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.FormulaMapper;
import sn.zeitune.olive_insurance_pricing.app.mappers.VariableItemMapper;

public class FieldMapper {

//    public static Field map(FieldRequestDTO dto, Field field) {
//        field.setLabel(dto.label());
//        field.setDescription(dto.description());
//        field.setVariableName(dto.variableName());
//        field.setToReturn(dto.toReturn());
//        field.setManagementEntity(dto.managementEntity());
//        field.setProduct(dto.product());
//        field.setCoverage(dto.branch());
//        return field;
//    }

//    public static Field map(FieldRequestDTO dto) {
//        return map(dto, new Field());
//    }

    private static Object dtoForField (Field field) {
        if (field instanceof NumericField) {
            return NumericFieldMapper.map((NumericField) field);
        }
        if (field instanceof SelectField) {
            return SelectFieldMapper.map((SelectField) field);
        }
        // Add other mappings for different VariableItem types if needed
        return null; // or throw an exception if appropriate

    }

    public static FieldResponseDTO map(Field field) {
        return (FieldResponseDTO) dtoForField(field);
    }
}
