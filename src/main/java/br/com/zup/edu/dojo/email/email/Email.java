package br.com.zup.edu.dojo.email.email;

import br.com.zup.edu.dojo.email.cliente.Cliente;
import br.com.zup.edu.dojo.email.transacao.TransacaoRequest;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant horaDeEnvio;
    private String mensagem;
    @ManyToOne
    private Cliente cliente;

    @Deprecated
    public Email() {
    }

    public Email(TransacaoRequest request, Cliente cliente) {
        this.horaDeEnvio = Instant.now();
        this.mensagem = request.getTipoOperacao().getMensagem(request);
        this.cliente = cliente;
    }

    public String getMensagem() {
        return mensagem;
    }
}
