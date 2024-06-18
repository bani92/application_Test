package me.whiteship.inflearnjavatest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Test
    void create() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
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