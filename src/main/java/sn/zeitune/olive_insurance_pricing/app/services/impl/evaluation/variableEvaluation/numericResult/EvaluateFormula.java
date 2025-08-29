package sn.zeitune.olive_insurance_pricing.app.services.impl.evaluation.variableEvaluation.numericResult;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import sn.zeitune.olive_insurance_pricing.app.entities.Formula;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableCondition;
import sn.zeitune.olive_insurance_pricing.app.entities.VariableItem;
import sn.zeitune.olive_insurance_pricing.app.entities.field.NumericField;

import java.util.Map;

public class EvaluateFormula extends EvaluateNumericResultVariable {

    Formula formula;
    Map<String, Object> fields;

    public EvaluateFormula(Formula formula, Map<String, Object> fields) {
        this.formula = formula;
        this.fields = fields;
    }

    @Override
    public Double execute() {
        for (VariableItem variableItem : formula.getVariables()) {
            if (!(variableItem instanceof VariableCondition || variableItem instanceof Formula || variableItem instanceof NumericField))
                throw new IllegalArgumentException("Unsupported variable item type: " + variableItem.getClass().getName());

            EvaluateNumericResultVariable evaluateVariableItem = EvaluateNumericResultVariable.create(variableItem, fields);
            Double value = evaluateVariableItem.execute();
            if (value == null) {
                throw new IllegalArgumentException("Variable " + variableItem.getVariableName() + " is not defined");
            }
            // Replace the variable in the formula expression with its value
            System.err.print("Expression to evaluate: " + formula.getExpression());
            formula.setExpression(formula.getExpression().replace("{{" + variableItem.getVariableName() + "}}", String.valueOf(value)));
            System.err.println(" => " + formula.getExpression());
        }
        Expression expression = new ExpressionBuilder(formula.getExpression()).build();
        return expression.evaluate();
    }
}

