package br.com.hateoas.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArtigoDTO {
    private Long id;
    private String titulo;
    private String conteudo;
}
