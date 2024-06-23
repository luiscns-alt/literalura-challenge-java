package com.example.literalura_challenge_java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.literalura_challenge_java.model.Autor;
import com.example.literalura_challenge_java.model.Livro;
import com.example.literalura_challenge_java.repository.AutorRepository;
import com.example.literalura_challenge_java.repository.LivroRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void salvarLivros(List<Livro> livros) {
        for (Livro livro : livros) {
            Autor autor = livro.getAutor();
            Autor autorExistente = autorRepository.findByNomeAndAnoNascimentoAndAnoFalecimento(
                    autor.getNome(), autor.getAnoNascimento(), autor.getAnoFalecimento());
            if (autorExistente == null) {
                autorRepository.saveAndFlush(autor);
                livro.setAutor(autor);
            } else {
                autorExistente = entityManager.merge(autorExistente);
                livro.setAutor(autorExistente);
            }
            livroRepository.save(livro);
        }
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public List<Livro> findByIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }

    public List<Autor> findAllAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> findAutoresVivos(int ano) {
        return autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
    }
}
