package com.chagas.bookstoreapi.dtos;

import com.chagas.bookstoreapi.domains.Livro;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.xml.transform.sax.SAXResult;
import java.io.Serializable;

public class LivroDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Campo TITULO é requerido")
    @Length(min = 3, max = 50, message = "O Campo TITULO deve ter entre 3 e 50 caracteres")
    private String titulo;

    public LivroDTO() {}

    public LivroDTO(Livro obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
