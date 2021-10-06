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

import java.util.Optional;

@Service
public class KafkaConsumerService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void consume(TransacaoRequest request) {
        /*Instanciar e persistir email*/
        Optional<Cliente> cliente = clienteRepository.findById(request.getIdCliente());
        if (cliente.isPresent()) {
            Email email = new Email(request, cliente.get());
            emailRepository.save(email);

            /*Enviar email*/
            MailSender sender = new EmailSender();
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("contato@ot-bank.com");
            message.setTo(cliente.get().getEmail());
            message.setSubject("Houve uma operação");
            message.setText(email.getMensagem());
            sender.send(message);

        } else {
            log.error("Cliente inexistente");
        }

    }

}
