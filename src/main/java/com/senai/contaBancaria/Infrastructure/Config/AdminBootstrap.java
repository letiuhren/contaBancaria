package com.senai.contaBancaria.Infrastructure.Config;

import com.senai.contaBancaria.domain.entity.Gerente;
import com.senai.contaBancaria.domain.enums.Role;
import com.senai.contaBancaria.domain.repository.GerenteRepository;
import lombok.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AdminBootstrap implements CommandLineRunner {

    private final GerenteRepository gerenteRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${sistema.admin.email}")
    private String adminEmail;

    @Value("${sistema.admin.senha}")
    private String adminSenha;

    @Override
    public void run(String... args) {
        gerenteRepository.findByEmail(adminEmail).ifPresentOrElse(
                gerent -> {
                    if (!gerent.isAtiva()) {
                        gerent.setAtiva(true);
                        gerenteRepository.(gerent);
                    }
                },
                () -> {
                    Gerente admin = Gerente.builder()
                            .nome("Administrador Provisório")
                            .email(adminEmail)
                            .cpf("000.000.000-00")
                            .senha(passwordEncoder.encode(adminSenha))
                            .role(Role.ADMIN)
                            .build();
                    gerenteRepository.save(admin);
                    System.out.println("⚡ Usuário admin provisório criado: " + adminEmail);
                }
        );
    }
}
