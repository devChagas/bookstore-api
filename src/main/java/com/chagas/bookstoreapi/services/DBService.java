package com.chagas.bookstoreapi.services;

import com.chagas.bookstoreapi.domains.Categoria;
import com.chagas.bookstoreapi.domains.Livro;
import com.chagas.bookstoreapi.repositories.CategoriaRepository;
import com.chagas.bookstoreapi.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;


    public void instaciaBaseDeDados() {
        Categoria cat1 = new Categoria(null, "Informática", "Livros de TI");
        Categoria cat2 = new Categoria(null, "Ficção Científica", "Livros de Ficcção Científica");
        Categoria cat3 = new Categoria(null, "Biografias", "Livros de Biografia");

        Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem Ipsum", cat1);
        Livro l2 = new Livro(null, "Engenharia de Software", "Louis V. Gerstner", "Lorem Ipsum", cat1);
        Livro l3 = new Livro(null, "The Time Machine", "H.G. Wells", "Lorem Ipsum", cat2);
        Livro l4 = new Livro(null, "The War of the Worlds", "H.G. Wells", "Lorem Ipsum", cat2);
        Livro l5 = new Livro(null, "I, Robot", "Isaac Asimov", "Lorem Ipsum", cat2);
        Livro l6 = new Livro(null, "Biografia do Pondé", "Pondé", "Lorem Ipsum", cat3);

        cat1.getLivros().addAll(Arrays.asList(l1, l2));
        cat2.getLivros().addAll(Arrays.asList(l3, l4, l5));
        cat3.getLivros().addAll(Arrays.asList(l6));

        this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        this.livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5, l6));
    }
}
