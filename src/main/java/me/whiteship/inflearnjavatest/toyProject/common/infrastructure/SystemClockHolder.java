package me.whiteship.inflearnjavatest.toyProject.common.infrastructure;

import me.whiteship.inflearnjavatest.toyProject.common.service.port.ClockHolder;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class SystemClockHolder implements ClockHolder {

    @Override
    public long millis() {
        return Clock.systemUTC().millis();
    }
}
