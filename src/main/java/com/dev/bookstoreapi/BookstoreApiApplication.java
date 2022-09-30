package com.dev.bookstoreapi;

import com.dev.bookstoreapi.domains.Categoria;
import com.dev.bookstoreapi.domains.Livro;
import com.dev.bookstoreapi.repositories.CategoriaRepository;
import com.dev.bookstoreapi.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BookstoreApiApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;


    public static void main(String[] args) {
        SpringApplication.run(BookstoreApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Informática", "Livros de TI");

        Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem Ipsum", cat1);

        cat1.getLivros().addAll(Arrays.asList(l1));

        this.categoriaRepository.saveAll(Arrays.asList(cat1));

        this.livroRepository.saveAll(Arrays.asList(l1));
    }
}
