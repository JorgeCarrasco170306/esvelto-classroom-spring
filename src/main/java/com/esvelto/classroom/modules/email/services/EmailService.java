package com.esvelto.classroom.modules.email.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.esvelto.classroom.errors.GlobalError;
import com.esvelto.classroom.modules.email.models.Email;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${application.mail.sender}")
    private String fromEmail;

    @Async // ejecuta envio en un hilo secundario para no bloquear la petición del usuario
    public void sendSimpleEmail(Email email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(email.getTo());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());

            mailSender.send(message);
        } catch (Exception e) {
            throw GlobalError.BadRequest("Error sending email");
        }
    }

}
