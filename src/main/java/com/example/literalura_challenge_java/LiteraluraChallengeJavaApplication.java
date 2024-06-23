package com.example.literalura_challenge_java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.literalura_challenge_java.controller.CatalogoController;

@SpringBootApplication
public class LiteraluraChallengeJavaApplication implements CommandLineRunner {
    @Autowired
    private CatalogoController catalogoController;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraChallengeJavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        catalogoController.iniciar();
    }
}