package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//aqui temos a camada responsável por fazer a ponte entre a View (telas)
// e a camada de Service
@RestController
@RequestMapping("/curso")
public class CursoController {

    // aqui é instância da camada Service, que contém as regras de negócio, porem agora sem acoplamento
    @Autowired
    private CursoIService service;

    @PostMapping
    public Curso save(@RequestBody Curso curso) {
        return service.save(curso);
    }

    @PutMapping
    public Curso update(@RequestBody Curso curso) {
        return service.update(curso);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Curso curso = service.findById(id);
        service.delete(curso);
    }

    @GetMapping
    public List<Curso> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Curso findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/nome/{name}")
    public Curso findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/codigo/{codigo}")
    public Curso findByCodigo(@PathVariable String codigo) {
        return service.findByCodigo(codigo);
    }
}
