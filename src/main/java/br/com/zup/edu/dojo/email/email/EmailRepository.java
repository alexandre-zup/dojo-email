package br.com.zup.edu.dojo.email.email;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
