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
        when(memberService.findById(any())).thenReturn(Optional.of(member)).thenThrow(new RuntimeException()).thenReturn(Optional.empty());

        Optional<Member> byId = memberService.findById(1L);
        assertEquals("keesun@email.com",byId.get().getEmail());


        assertThrows(RuntimeException.class, () -> {
            memberService.findById(1L);
        });

        Optional<Member> byId3 = memberService.findById(1L);
        assertEquals(Optional.empty(), byId3);



        assertEquals("keesun@email.com",memberService.findById(1L).get().getEmail());
        assertEquals("keesun@email.com",memberService.findById(2L).get().getEmail());

        // 1이라는 값으로 find가 되면 런타임 익셉션을 던짐(스터빙)
        // when(memberService.findById(1L)).thenThrow(new RuntimeException());

        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });

    }

}