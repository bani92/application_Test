package me.whiteship.inflearnjavatest.toyProject.user.service;

import me.whiteship.inflearnjavatest.toyProject.mock.FakeMailSender;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CertificationServiceTest {

    @Test
    public void 이메일과_컨텐츠가_제대로_만들어졌는지_테스트한다() {
        // given
        FakeMailSender fakeMailSender = new FakeMailSender();
        CertificationService certificationService = new CertificationService(fakeMailSender);

        // when
        certificationService.send("banseok@naver.com",1,"aaaaaaa-aa");

        // then
        assertThat(fakeMailSender.email).isEqualTo("banseok@naver.com");
        assertThat(fakeMailSender.title).isEqualTo("Please certify your email address");
        assertThat(fakeMailSender.content).isEqualTo("Please click the following link to certify your email address: " +
                "http://localhost:8080/api/users/1/verify?certificationCode=aaaaaaa-aa");

    }
}