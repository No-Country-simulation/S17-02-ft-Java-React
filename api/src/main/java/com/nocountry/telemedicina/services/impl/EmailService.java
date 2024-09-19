package com.nocountry.telemedicina.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.nocountry.telemedicina.exception.CustomException;

@Service
@Slf4j
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendHtmlEmail(String toEmail, String subject, String htmlBody) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // El segundo parámetro indica que el contenido es HTML
        javaMailSender.send(mimeMessage);
    }

    public void registerConfirmation(String toEmail) {
        String htmlBody = String.format(
                "<div style=\"max-width: 600px; margin: 20px auto; background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5; color: #333;\">"
                        +
                        "<div style=\"text-align: center; padding-bottom: 20px;\">" +
                        "</div>" +
                        "<p>Hola, %s! Bienvenid@ a Hey doc!.</p>" +
                        "<p>Gracias por registrarte en nuestro sitio.</p>",
                toEmail);

        try {
            sendHtmlEmail(toEmail, "Confirm register", htmlBody);
        } catch (Exception e) {
            throw new CustomException(500, e.getMessage());
        }
    }

}
