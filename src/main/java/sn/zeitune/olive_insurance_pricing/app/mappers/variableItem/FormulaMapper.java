package sn.zeitune.olive_insurance_pricing.app.mappers.variableItem;

import sn.zeitune.olive_insurance_pricing.app.dtos.requests.variableItem.FormulaRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.dtos.responses.variableItem.FormulaResponseDTO;
import sn.zeitune.olive_insurance_pricing.app.entities.variableItem.Formula;

public class FormulaMapper extends GenericMapper<Formula, FormulaRequestDTO, FormulaResponseDTO> {

    private static FormulaMapper instance;

    private  FormulaMapper() {}

    public static FormulaMapper getInstance() {
        if (instance == null) {
            instance = new FormulaMapper();
        }
        return instance;
    }

    @Override
    public void putRequestValue(FormulaRequestDTO dto, Formula formula) {
        if (formula == null || dto == null) return;
        VariableItemMapper.getInstance().putRequestValue(dto, formula);
        formula.setExpression(dto.getExpression());
    }

    @Override
    public FormulaResponseDTO map(Formula formula) {
        if (formula == null) return null;
        FormulaResponseDTO formulaResponseDTO = (FormulaResponseDTO) VariableItemMapper.getInstance().map(formula);
        formulaResponseDTO.setExpression(formula.getExpression());
        formulaResponseDTO.setVariables(
                formula.getVariables().stream()
                        .map(VariableItemMapper.Utils::getVariableItemResponseDTO)
                        .toList()
        );
        formulaResponseDTO.setCoverage(formula.getCoverage());
        return formulaResponseDTO;
    }
}
