package br.com.hateoas.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import br.com.hateoas.model.Artigo;
import br.com.hateoas.repository.ArtigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/artigos")
public class ArtigoResource {

    @Autowired
    private ArtigoRepository artigoRepository;


    @GetMapping
    ResponseEntity<Resources<Resource<Artigo>>> getAll() {
        List<Resource<Artigo>> employeeResources = StreamSupport.stream(artigoRepository.findAll().spliterator(), false)
                .map(employee -> new Resource<>(employee,
                        linkTo(methodOn(ArtigoResource.class).getID(employee.getId())).withSelfRel(),
                        linkTo(methodOn(ArtigoResource.class).getAll()).withRel("artigos")
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new Resources<>(employeeResources, linkTo(methodOn(ArtigoResource.class)
                .getAll()).withSelfRel()));
    }


//    @GetMapping("/employees/{id}")
//    ResponseEntity<Resource<Employee>> findOne(@PathVariable long id) {
//
//        return repository.findById(id)
//                .map(employee -> new Resource<>(employee,
//                        linkTo(methodOn(EmployeeController.class).findOne(employee.getId())).withSelfRel()
//                                .andAffordance(afford(methodOn(EmployeeController.class).updateEmployee(null, employee.getId())))
//                                .andAffordance(afford(methodOn(EmployeeController.class).deleteEmployee(employee.getId()))),
//                        linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees")
//                ))
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource<Artigo>> getID(@PathVariable Long id) {
        return artigoRepository.findById(id)
                .map(employee -> new Resource<>(employee,
                        linkTo(methodOn(ArtigoResource.class).getID(employee.getId())).withSelfRel(),
                        linkTo(methodOn(ArtigoResource.class).getAll()).withRel("artigos")
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}


