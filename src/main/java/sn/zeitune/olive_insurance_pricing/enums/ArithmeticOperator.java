package sn.zeitune.olive_insurance_pricing.enums;

public enum ArithmeticOperator {
    ADDITION("+"),        // +
    SUBTRACTION("-"),     // -
    MULTIPLICATION("*"),  // *
    DIVISION("/");

    private final String symbol;

    ArithmeticOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static ArithmeticOperator fromSymbol(String symbol) {
        for (ArithmeticOperator operator : values()) {
            if (operator.symbol.equals(symbol)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + symbol);
    }
}
