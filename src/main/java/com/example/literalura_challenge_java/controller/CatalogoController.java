package com.example.literalura_challenge_java.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.literalura_challenge_java.model.Autor;
import com.example.literalura_challenge_java.model.Livro;
import com.example.literalura_challenge_java.service.GutendexService;
import com.example.literalura_challenge_java.service.LivroService;

@Controller
public class CatalogoController {
    @Autowired
    private GutendexService gutendexService;

    @Autowired
    private LivroService livroService;

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Buscar livro por título");
            System.out.println("2. Listar todos os livros");
            System.out.println("3. Listar livros por idioma");
            System.out.println("4. Listar autores");
            System.out.println("5. Listar autores vivos em determinado ano");
            System.out.println("6. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    String titulo = scanner.nextLine();
                    try {
                        List<Livro> livros = gutendexService.buscarLivrosPorTitulo(titulo);
                        livroService.salvarLivros(livros);
                        System.out.println("Livros encontrados e salvos no banco de dados.");
                    } catch (Exception e) {
                        System.out.println("Erro ao buscar livros: " + e.getMessage());
                    }
                    break;
                case 2:
                    List<Livro> todosLivros = livroService.findAll();
                    todosLivros.forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Digite o idioma:");
                    String idioma = scanner.nextLine();
                    List<Livro> livrosPorIdioma = livroService.findByIdioma(idioma);
                    livrosPorIdioma.forEach(System.out::println);
                    break;
                case 4:
                    List<Autor> todosAutores = livroService.findAllAutores();
                    todosAutores.forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Digite o ano:");
                    int ano = scanner.nextInt();
                    scanner.nextLine();
                    List<Autor> autoresVivos = livroService.findAutoresVivos(ano);
                    autoresVivos.forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}