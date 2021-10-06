package br.com.zup.edu.dojo.email.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Arrays;

public class EmailSender implements MailSender {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        log.info("------------ \nEnviando email");
        log.info("De: " + simpleMessage.getFrom());
        log.info("Para: " + Arrays.toString(simpleMessage.getTo()));
        log.info("Assunto: " + simpleMessage.getSubject());
        log.info("Conteúdo: " + simpleMessage.getText());
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        for (SimpleMailMessage simpleMessage:simpleMessages) {
            send(simpleMessage);
        }
    }
}
