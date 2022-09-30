package com.chagas.bookstoreapi.services;

import com.chagas.bookstoreapi.domains.Livro;
import com.chagas.bookstoreapi.repositories.LivroRepository;
import com.chagas.bookstoreapi.services.exceptions.ObjectNotFoundException;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id){
        Optional<Livro> obj = livroRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Livro.class.getName()));
    }

    public List<Livro> findAll(Integer id_cat) {
        categoriaService.findById(id_cat);
        return livroRepository.findAllByCategoria(id_cat);
    }
}
