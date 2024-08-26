package me.whiteship.inflearnjavatest.toyProject.mock;

import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnjavatest.toyProject.common.service.port.UuidHolder;

@RequiredArgsConstructor
public class TestUuidHolder implements UuidHolder {

    private final String uuid;

    @Override
    public String random() {
        return uuid;
    }
}
