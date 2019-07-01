package br.com.hateoas.repository;

import br.com.hateoas.model.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtigoRepository extends JpaRepository<Artigo,Long> {
}
