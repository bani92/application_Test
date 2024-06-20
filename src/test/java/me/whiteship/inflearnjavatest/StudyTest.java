package me.whiteship.inflearnjavatest;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create() {

        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("limit은 0보다 커야한다.",exception.getMessage());

//        assertAll(
//                () -> assertNotNull(study),
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
//                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.")
//        );

    }

    @Test
    @Disabled
    void create1() {
        System.out.println("create1");
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