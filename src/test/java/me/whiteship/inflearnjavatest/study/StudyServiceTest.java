package me.whiteship.inflearnjavatest.study;

import me.whiteship.inflearnjavatest.domain.Member;
import me.whiteship.inflearnjavatest.domain.Study;
import me.whiteship.inflearnjavatest.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    /**
     * 1. 객체 정의 후 사용 Mockito.mock(MemberService.class)
     * 2. 애너테이션 선언 @Mock MemberService memberService
     * 3. 생성자로 처리 createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository)
     */

    @Test
    void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {

        // Given
        StudyService studyService = new StudyService(memberService,studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("keesun@email.com");

        Study study = new Study(10, "테스트");

        // memberService 객체에 findById 메소드를 1L 값으로 호출하면 member 객체를 리턴하도록 Stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        // studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        when(studyRepository.save(study)).thenReturn(study);

        // When
        studyService.createNewStudy(1L, study);

        // Then
        assertNotNull(study.getOwnerId());
        verify(memberService, times(1)).notify(study);
    }

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더 자바 테스트");
        // TODO studyRepositoy Mock 객체의 save 메소드를 호출 시 study를 리턴하도록 만들기
        when(studyRepository.save(study)).thenReturn(study);
        // When
        studyService.openStudy(study);

        // Then
        // TODO study의 status가 OPENED로 변경됐는지 확인
        assertEquals(StudyStatus.OPENED,study.getStatus());
        // TODO study의 openedDataTime이 null이 아닌지 확인
        assertNotNull(study.getOpenedDateTime());
        // TODO memberService의 notify(study)가 호출 됐는지 확인
        verify(memberService, times(1)).notify(study);
    }

}