package me.whiteship.inflearnjavatest.member;

import me.whiteship.inflearnjavatest.domain.Member;
import me.whiteship.inflearnjavatest.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

//    void validate(Long memberId);

//    void notify(Study newstudy);

//    void notify(Member member);
}
