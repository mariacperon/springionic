package com.cursosp.projetosp.services;

import javax.mail.internet.MimeMessage;

import com.cursosp.projetosp.domain.Cliente;
import com.cursosp.projetosp.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
