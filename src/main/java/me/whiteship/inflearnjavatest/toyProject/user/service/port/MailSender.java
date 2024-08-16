package me.whiteship.inflearnjavatest.toyProject.user.service.port;

public interface MailSender {

    void send(String email, String title, String content);
}
