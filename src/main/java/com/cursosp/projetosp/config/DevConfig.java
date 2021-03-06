package com.cursosp.projetosp.config;

import com.cursosp.projetosp.services.DBService;
import com.cursosp.projetosp.services.EmailService;
import com.cursosp.projetosp.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantianteDatabase() throws ParseException {
        if (!"create".equals(strategy)){
            return false;
        }else{
            dbService.instantiateTestDatabase();
            return true;
        }
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
