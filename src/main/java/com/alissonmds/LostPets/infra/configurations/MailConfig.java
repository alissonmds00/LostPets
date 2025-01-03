package com.alissonmds.LostPets.infra.configurations;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        Dotenv dotenv = Dotenv.load();

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(dotenv.get("SPRING.MAIL.HOST"));
        mailSender.setPort(Integer.parseInt(dotenv.get("SPRING.MAIL.PORT")));
        mailSender.setUsername(dotenv.get("SPRING.MAIL.USERNAME"));
        mailSender.setPassword(dotenv.get("SPRING.MAIL.PASSWORD"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", dotenv.get("SPRING.MAIL.PROPERTIES.MAIL.SMTP.AUTH"));
        props.put("mail.smtp.starttls.enable", dotenv.get("SPRING.MAIL.PROPERTIES.MAIL.SMTP.STARTTLS.ENABLE"));
        props.put("mail.debug", "false");

        return mailSender;

    }
}
