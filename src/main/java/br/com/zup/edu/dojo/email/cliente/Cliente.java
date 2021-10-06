package br.com.zup.edu.dojo.email.cliente;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Cliente {

    @Id
    private UUID id;
    private String email;
    private String nome;

    @Deprecated
    public Cliente() {

    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }


}
