package com.example.literalura_challenge_java.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.literalura_challenge_java.model.Autor;
import com.example.literalura_challenge_java.model.Livro;
import com.example.literalura_challenge_java.repository.AutorRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GutendexService {
    private static final String BASE_URL = "https://gutendex.com/books/";

    @Autowired
    private AutorRepository autorRepository;

    public List<Livro> buscarLivrosPorTitulo(String titulo) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?search=" + URLEncoder.encode(titulo, StandardCharsets.UTF_8)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.body());

        List<Livro> livros = new ArrayList<>();
        for (JsonNode bookNode : rootNode.path("results")) {
            Livro livro = new Livro();
            livro.setTitulo(bookNode.path("title").asText());
            livro.setIdioma(bookNode.path("languages").get(0).asText());
            livro.setNumeroDownloads(bookNode.path("download_count").asInt());

            JsonNode authorNode = bookNode.path("authors").get(0);
            Autor autor = new Autor();
            autor.setNome(authorNode.path("name").asText());
            autor.setAnoNascimento(authorNode.path("birth_year").asInt());
            autor.setAnoFalecimento(authorNode.path("death_year").asInt());

            Autor autorExistente = autorRepository.findByNomeAndAnoNascimentoAndAnoFalecimento(
                    autor.getNome(), autor.getAnoNascimento(), autor.getAnoFalecimento());
            if (autorExistente != null) {
                livro.setAutor(autorExistente);
            } else {
                autor = autorRepository.saveAndFlush(autor);
                livro.setAutor(autor);
            }

            livros.add(livro);
        }
        return livros;
    }
}