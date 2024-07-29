package me.whiteship.inflearnjavatest.sample;

import org.apache.coyote.BadRequestException;

public class CalculationRequest {

    private final long num1;
    private final long num2;
    private final String operator;

    public CalculationRequest(String[] parts) {
        if(parts.length != 3) {
            throw new BadRequestException1();
        }
        String operator = parts[1];
        if(operator.length() != 1 || isaBoolean(operator)) {
            throw new InvalidOperatorException();
        }

        this.num1 = Long.parseLong(parts[0]);
        this.num2 = Long.parseLong(parts[2]);
        this.operator = operator;
    }

    private static boolean isaBoolean(String operator) {
        return !operator.equals("+") &&
                !operator.equals("-") &&
                !operator.equals("*") &&
                !operator.equals("/");
    }

    public long getNum1() {
        return num1;
    }

    public long getNum2() {
        return num2;
    }

    public String getOperator() {
        return operator;
    }
}
