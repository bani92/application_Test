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
    }

}