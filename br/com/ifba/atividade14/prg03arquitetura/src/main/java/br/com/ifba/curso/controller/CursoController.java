package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import br.com.ifba.curso.service.CursoService;
import java.util.List;

//aqui temos a camada responsável por fazer a ponte entre a View (telas)
 // e a camada de Service
public class CursoController implements CursoIController {

    // aqui é instância da camada Service, que contém as regras de negócio
    private final CursoIService service = new CursoService();

    @Override
    public Curso save(Curso curso) {
        return service.save(curso);
    }

    @Override
    public Curso update(Curso curso) {
        return service.update(curso);
    }

    @Override
    public void delete(Curso curso) {
        service.delete(curso);
    }

    @Override
    public List<Curso> findAll() {
        return service.findAll();
    }

    @Override
    public Curso findById(Long id) {
        return service.findById(id);
    }
    
    


    @Override
    public Curso findByName(String name) {
        return service.findByName(name);
    }

    @Override
    public Curso findByCodigo(String codigo) {
        return service.findByCodigo(codigo);
    }
}
