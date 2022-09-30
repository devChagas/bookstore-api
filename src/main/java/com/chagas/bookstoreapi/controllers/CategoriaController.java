package com.chagas.bookstoreapi.controllers;

import com.chagas.bookstoreapi.domains.Categoria;
import com.chagas.bookstoreapi.dtos.CategoriaDTO;
import com.chagas.bookstoreapi.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id){
        Categoria obj = categoriaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDTO> listDTO = list.stream().map(CategoriaDTO::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody Categoria obj){
        obj = categoriaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @RequestBody CategoriaDTO objDTO) {
        Categoria newObj = categoriaService.update(id, objDTO);
        return ResponseEntity.ok().body(new CategoriaDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
