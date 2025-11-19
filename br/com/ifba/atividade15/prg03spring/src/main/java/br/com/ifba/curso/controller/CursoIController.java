package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

public interface CursoIController {

    //aqui salva um novo Curso no sistema.
    Curso save(Curso curso);

    //atualiza um Curso existente ou seja, edita
    Curso update(Curso curso);

    //exclui um curso selecionado
    void delete(Curso curso);

    //aqui é opra retornar  todos os cursos cadastrados
    List<Curso> findAll();
    
    Curso findByCodigo(String codigo);


    //busca um curso por id
    Curso findById(Long id);

    //e aqui busca um curso por nome
    Curso findByName(String name);
}
