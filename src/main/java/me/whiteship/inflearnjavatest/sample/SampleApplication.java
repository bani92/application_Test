package me.whiteship.inflearnjavatest.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SampleApplication {

    public static void main(String[] args) {

        CalculationRequest calculationRequest = new CalculationRequestReader().read();
        long answer = new Calculator().calculate(calculationRequest.getNum1(), calculationRequest.getOperator(), calculationRequest.getNum2());

        System.out.println(answer);

    }
}
