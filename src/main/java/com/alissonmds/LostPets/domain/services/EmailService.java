package com.alissonmds.LostPets.domain.services;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final String endpoint = "";
    private final JavaMailSender mailSender;
    private Dotenv dotenv;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        this.dotenv = Dotenv.load();
    }


    public void enviarEmailRecuperacaoSenha(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Recuperação de senha LostPets");
        message.setText("""
                Alguém solicitou a alteração de senha nos nossos serviços, LostPets.
                Se não foi você o solicitante, desconsidere este email. Caso contrário, acesse o link abaixo para redefinir sua senha
                %s%s
                """.formatted(endpoint, token));
        mailSender.send(message);
    }
}
