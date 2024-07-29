package me.whiteship.inflearnjavatest.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculationRequestReader {

    public CalculationRequest read() {
        String word = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter two numbers and aqn operator (e.g 1 + 2): ");
            word = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] parts = word.split(" ");
        return new CalculationRequest(parts);
    }
}
