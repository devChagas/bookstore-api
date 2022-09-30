package com.chagas.bookstoreapi.services;

import com.chagas.bookstoreapi.domains.Categoria;
import com.chagas.bookstoreapi.dtos.CategoriaDTO;
import com.chagas.bookstoreapi.repositories.CategoriaRepository;
import com.chagas.bookstoreapi.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() ->
                new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria create(Categoria obj) {
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Integer id, CategoriaDTO objDTO){
        Categoria obj = findById(id);
        obj.setNome(objDTO.getNome());
        obj.setDescricao(objDTO.getDescricao());
        return categoriaRepository.save(obj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new com.chagas.bookstoreapi.services.exceptions.DataIntegrityViolationException("Objeto não pode ser deletado!" +
                    " Possui livros associados");
        }


    }
}
