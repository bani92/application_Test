package me.whiteship.inflearnjavatest.sample;

public class BadRequestException1 extends RuntimeException {

    public BadRequestException1() {
        super("Invalid input size, you must input 3 length");
    }
}
