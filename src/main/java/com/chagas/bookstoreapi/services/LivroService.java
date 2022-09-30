package com.chagas.bookstoreapi.services;

import com.chagas.bookstoreapi.domains.Livro;
import com.chagas.bookstoreapi.repositories.LivroRepository;
import com.chagas.bookstoreapi.services.exceptions.ObjectNotFoundException;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro findById(Integer id){
        Optional<Livro> obj = livroRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Livro.class.getName()));
    }
}
