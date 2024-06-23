package me.whiteship.inflearnjavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@ExtendWith(FindSlowTestExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    @Order(2)
    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create() {
        System.out.println(this);
        Study study = new Study(100);
    }

    @Order(1)
    @SlowTest
    @DisplayName("스터디 만들기 slow")
    void create1() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println(this);
        System.out.println("create1");
    }


    @DisplayName("스터디 만들기")
    @RepeatedTest(value = 10, name = "{displayName} , {currentRepetition} / {totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @Order(3)
    @DisplayName("스터디 만들기!")
    @ParameterizedTest(name = "{index} - {displayName} message = {0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void parameterizedTest(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    // 인자값이 여러개일때 ArgumentsAggregator(인터페이스)
    static class StudyAggregator implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }
    // 인자값이 하나일때 SimpleArgumentConverter(클래스)
    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> aClass) throws ArgumentConversionException {
            assertEquals(Study.class, aClass, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
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