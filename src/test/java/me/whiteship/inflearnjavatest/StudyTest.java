package me.whiteship.inflearnjavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class StudyTest {

    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create() {
        Study study = new Study(100);
    }

    @SlowTest
    @DisplayName("스터디 만들기 slow")
    void create1() {
        System.out.println("create1");
    }

    @DisplayName("스터디 만들기")
    @RepeatedTest(value = 10, name = "{displayName} , {currentRepetition} / {totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("스터디 만들기!")
    @ParameterizedTest(name = "{index} - {displayName} message = {0}")
    @ValueSource(strings = {"날씨가" ,"많이", "추워지고", "있네요"})
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    @BeforeAll
    static void beforeAll() {
        // 모든 테스트가 실행되기 전에 제일 먼저 실행되는 테스트
        System.out.println("before all");
    }

    @AfterAll
    static void AfterAll() {
        // 모든 테스트가 실행된 이후에 딱 한번만 호출이 됨
        System.out.println("after all");
    }

    @BeforeEach
    void BeforeEach() {
        // 테스트 실행전
        System.out.println("BeforeEach");
    }

    @AfterEach
    void AfterEach() {
        // 테스트 실행후
        System.out.println("AfterEach");
    }
}