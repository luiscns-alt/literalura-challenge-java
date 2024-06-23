package com.example.literalura_challenge_java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.literalura_challenge_java.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idioma);
}
