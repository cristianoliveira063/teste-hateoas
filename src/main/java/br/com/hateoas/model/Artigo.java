package br.com.hateoas.model;

import br.com.hateoas.model.dtos.ArtigoDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Artigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String conteudo;

    public  static ArtigoDTO convertToDTO(Artigo a){
        return  new ArtigoDTO(a.id,a.titulo,a.conteudo);
    }
}
