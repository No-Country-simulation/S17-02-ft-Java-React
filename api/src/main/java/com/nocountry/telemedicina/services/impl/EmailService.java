package com.nocountry.telemedicina.services.impl;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    public void sendEmail(String toEmail, String subject, String htmlBody) {
        try {
            SimpleMailMessage mailMsg = new SimpleMailMessage();
            mailMsg.setFrom(emailSender);
            mailMsg.setTo(toEmail);
            mailMsg.setText(htmlBody);
            mailMsg.setSubject(subject);
            javaMailSender.send(mailMsg);
            log.info("Mail sent successfully");
        }catch (MailException exception) {
            log.debug("Failure occurred while sending email");
        }
    }

    public void registerConfirmation(String toEmail, String name, String token) {
        sendEmail(toEmail, "Confirm register",
                getHtmlBody("register", name, token));
    }

    public void resetPasswordConfirmation(String toEmail, String name, String token) throws MessagingException {
        sendEmail(toEmail, "Reset Password",
                getHtmlBody("resetPassword", name, token));
    }

    public void sendTokenChangeEmail(String toEmail, String name, String token) throws MessagingException {
        sendEmail(toEmail, "Change email",
                getHtmlBody("changeEmail", name, token));
    }

    public String getHtmlBody(String typeMessage, String name, String token){
        if(typeMessage.equals("register")){
            return String.format(
                    "<div style=\"max-width: 600px; margin: 20px auto; background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5; color: #333;\">"
                            +
                            "<div style=\"text-align: center; padding-bottom: 20px;\">" +
                            // "<img src=\"https://example.com/logo.png\" alt=\"Logo\" style=\"max-width:
                            // 150px;\">" +
                            "</div>" +
                            "<p>Hola, %s!</p>" +
                            "<p>Gracias por registrarte en nuestro sitio. El Link vence en 15min. Para completar el registro, por favor haz clic en el siguiente enlace:</p>"
                            +
                            "<a href=\"http://localhost:5173/confirm-account/%s\" style=\"display: inline-block; padding: 10px 20px; margin: 20px 0; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;\">Confirmar mi cuenta</a>"
                            +
                            "<p>Si no puedes hacer clic en el enlace, copia y pega la URL en tu navegador: <a href=\"http://localhost:5173/confirm-account/%s\" style=\"color: #007bff;\">http://localhost:5173/confirm-account/%s</a></p>"
                            +
                            "<div style=\"text-align: center; font-size: 14px; color: #888;\">" +
                            "<p>Si tienes alguna pregunta, no dudes en contactarnos.</p>" +
                            "<p>&copy; 2024 Tu Empresa</p>" +
                            "</div>" +
                            "</div>",
                    name, token, token, token);
        } else if (typeMessage.equals("resetPassword")) {
            return String.format(
                    "<div style=\"max-width: 600px; margin: 20px auto; background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5; color: #333;\">"
                            +
                            "<div style=\"text-align: center; padding-bottom: 20px;\">" +
                            // "<img src=\"https://example.com/logo.png\" alt=\"Logo\" style=\"max-width:
                            // 150px;\">" +
                            "</div>" +
                            "<p>Hi, %s!</p>" +
                            "<p>Enter the following link to reset your password. Link expires in 15min:</p>"
                            +
                            "<a href=\"http://localhost:5173/recover-password/%s\" style=\"display: inline-block; padding: 10px 20px; margin: 20px 0; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 5px;\">Reset password</a>"
                            +
                            "<p>If you can't click on the link, copy and paste the URL in your browser: <a href=\"http://localhost:5173/recover-password/%s\" style=\"color: #007bff;\">http://localhost:5173/recover-password/%s</a></p>"
                            +
                            "<div style=\"text-align: center; font-size: 14px; color: #888;\">" +
                            "<p>If you have any questions, please do not hesitate to contact us.</p>" +
                            "<p>&copy; 2024 Tu Empresa</p>" +
                            "</div>" +
                            "</div>",
                    name, token, token, token);
        } else if (typeMessage.equals("changeEmail")) {
            return String.format(
                    "<div style=\"max-width: 600px; margin: 20px auto; background: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); font-family: Arial, sans-serif; font-size: 16px; line-height: 1.5; color: #333;\">"
                            +
                            "<div style=\"text-align: center; padding-bottom: 20px;\">" +
                            // "<img src=\"https://example.com/logo.png\" alt=\"Logo\" style=\"max-width:
                            // 150px;\">" +
                            "</div>" +
                            "<p>Hi, %s!</p>" +
                            "<p>Enter this code to change your email address.</p>" +
                            "<p>This code will expire in 30 mi.</p>"
                            +

                            "<p>this is the code: %s</p>"
                            +
                            "<div style=\"text-align: center; font-size: 14px; color: #888;\">" +
                            "<p>If you have any questions, please do not hesitate to contact us.</p>" +
                            "<p>&copy; 2024 Tu Empresa</p>" +
                            "</div>" +
                            "</div>",
                    name, token);
        } else {
            return null;
        }
    }

}
