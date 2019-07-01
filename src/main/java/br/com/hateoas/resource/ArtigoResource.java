package br.com.hateoas.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import br.com.hateoas.model.Artigo;
import br.com.hateoas.model.dtos.ArtigoDTO;
import br.com.hateoas.repository.ArtigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artigos")
public class ArtigoResource  {

    @Autowired
    private ArtigoRepository artigoRepository;

    @GetMapping
    public List<Artigo> getAll(){
        return artigoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Resource<ArtigoDTO> getID(@PathVariable Long id){
        Optional<Artigo> artigo = artigoRepository.findById(id);
            Resource<ArtigoDTO> resource = new Resource<>(Artigo.convertToDTO(artigo.get()));
            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAll());
            resource.add(linkTo.withRel("all-artigos"));
            return resource;
    }
}

