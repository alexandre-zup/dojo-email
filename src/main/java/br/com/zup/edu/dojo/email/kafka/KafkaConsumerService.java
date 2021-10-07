package br.com.zup.edu.dojo.email.kafka;

import br.com.zup.edu.dojo.email.cliente.Cliente;
import br.com.zup.edu.dojo.email.cliente.ClienteRepository;
import br.com.zup.edu.dojo.email.email.Email;
import br.com.zup.edu.dojo.email.email.EmailRepository;
import br.com.zup.edu.dojo.email.email.EmailSender;
import br.com.zup.edu.dojo.email.transacao.TransacaoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class KafkaConsumerService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Transactional
    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void consume(TransacaoRequest request) {
        /*Instanciar e persistir email*/
        Cliente cliente = clienteRepository.getById(request.getIdCliente());

        Email email = new Email(request, cliente);
        emailRepository.save(email);

        /*Enviar email*/
        MailSender sender = new EmailSender();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contato@ot-bank.com");
        message.setTo(cliente.getEmail());
        message.setSubject("Houve uma operação");
        message.setText(email.getMensagem());
        sender.send(message);

    }

}
