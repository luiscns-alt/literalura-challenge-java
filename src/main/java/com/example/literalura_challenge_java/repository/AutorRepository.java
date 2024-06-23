package com.example.literalura_challenge_java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.literalura_challenge_java.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(int anoNascimento, int anoFalecimento);

    Autor findByNomeAndAnoNascimentoAndAnoFalecimento(String nome, int anoNascimento, int anoFalecimento);
}