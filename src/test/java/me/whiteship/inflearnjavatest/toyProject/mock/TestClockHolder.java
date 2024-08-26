package me.whiteship.inflearnjavatest.toyProject.mock;

import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnjavatest.toyProject.common.service.port.ClockHolder;

import java.time.Clock;

@RequiredArgsConstructor
public class TestClockHolder implements ClockHolder {

    private final long millis;

    @Override
    public long millis() {
        return millis;
    }
}
