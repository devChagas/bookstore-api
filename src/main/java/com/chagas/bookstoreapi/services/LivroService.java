package com.chagas.bookstoreapi.services;

import com.chagas.bookstoreapi.domains.Categoria;
import com.chagas.bookstoreapi.domains.Livro;
import com.chagas.bookstoreapi.repositories.LivroRepository;
import com.chagas.bookstoreapi.services.exceptions.ObjectNotFoundException;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    public Livro update(Integer id, Livro obj) {
        Livro newObj = findById(id);
        updateData(newObj, obj);
        return livroRepository.save(newObj);
    }

    private void updateData(Livro newObj, Livro obj) {
        newObj.setTitulo(obj.getTitulo());
        newObj.setNomeAutor(obj.getNomeAutor());
        newObj.setTexto(obj.getTexto());
    }

    public Livro create(Integer id_cat, Livro obj) {
        obj.setId(null);
        Categoria cat = categoriaService.findById(id_cat);
        obj.setCategoria(cat);
        return livroRepository.save(obj);
    }

    public void delete(Integer id) {
        Livro obj = findById(id);
        livroRepository.delete(obj);
    }
}
