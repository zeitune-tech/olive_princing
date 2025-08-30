package sn.zeitune.olive_insurance_pricing.app.utils;

import sn.zeitune.olive_insurance_pricing.enums.ArithmeticOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {

    public static class ParsedExpression {
        public final List<String> variables;
        public final List<ArithmeticOperator> operators;

        public ParsedExpression(List<String> variables, List<ArithmeticOperator> operators) {
            this.variables = variables;
            this.operators = operators;
        }
    }

    public static ParsedExpression parseExpression(String expression) throws IllegalArgumentException {
        List<String> variables = new ArrayList<>();
        List<ArithmeticOperator> operators = new ArrayList<>();

        // Nettoyage des espaces
        String cleanExp = expression.replaceAll("\\s+", "");

        // Regex pour identifier variables et opérateurs
        Pattern pattern = Pattern.compile("([a-zA-Z_][a-zA-Z0-9_]*)|([+\\-*/])");
        Matcher matcher = pattern.matcher(cleanExp);

        while (matcher.find()) {
            String token = matcher.group();

            if (token.matches("[+\\-*/]")) {
                try {
                    ArithmeticOperator op = ArithmeticOperator.fromSymbol(token);
                    operators.add(op);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Opérateur non supporté : " + token, e);
                }
            } else {
                variables.add(token);
            }
        }

        return new ParsedExpression(variables, operators);
    }

    // Exemple d'utilisation
    public static void main(String[] args) {
        String exp = "Var1 + Var2 - Var3 * Var4 / Var5";

        try {
            ParsedExpression parsed = parseExpression(exp);
            System.out.println("Variables trouvées : " + parsed.variables);
            System.out.println("Opérateurs trouvés : " + parsed.operators);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
