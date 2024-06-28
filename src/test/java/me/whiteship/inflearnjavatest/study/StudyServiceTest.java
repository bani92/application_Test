package me.whiteship.inflearnjavatest.study;

import me.whiteship.inflearnjavatest.domain.Member;
import me.whiteship.inflearnjavatest.domain.Study;
import me.whiteship.inflearnjavatest.member.MemberService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    /**
     * 1. 객체 정의 후 사용 Mockito.mock(MemberService.class)
     * 2. 애너테이션 선언 @Mock MemberService memberService
     * 3. 생성자로 처리 createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository)
     */

    @Test
    void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {

        StudyService studyService = new StudyService(memberService,studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("keesun@email.com");

        Study study = new Study(10, "테스트");

        // memberService 객체에 findById 메소드를 1L 값으로 호출하면 member 객체를 리턴하도록 Stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        Optional<Member> byId = memberService.findById(1L);

        assertEquals("keesun@email.com",byId.get().getEmail());

        // studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);

        assertNotNull(study.getOwnerId());
        assertEquals(1, study.getOwnerId());
    }

}