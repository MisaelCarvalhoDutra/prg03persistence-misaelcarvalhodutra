package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.StringUtil;

import java.util.List;

//Aqui são realizadas validações 
//a Regra de negocio

public class CursoService implements CursoIService {

    // DAO responsável pela comunicação com o banco de dados
    private final CursoDao dao = new CursoDao();

    
    
     // Salva um novo curso no sistema.
     // só q antes de salvar, aplica validações.
    @Override
    public Curso save(Curso curso) {

         //faz a verificação se o objeto curso é nulo
        if (curso == null) {
            throw new RuntimeException("Curso não pode ser nulo!");
        }

        // esse verifica se o nome está vazio ou nulo usando StringUtil
        if (StringUtil.isNullOrEmpty(curso.getNome())) {
            throw new RuntimeException("O nome do curso não pode ser vazio!");
        }

        // esse aqui verifica q não pode existir outro curso com o mesmo nome
        if (dao.findByName(curso.getNome()) != null) {
            throw new RuntimeException("Já existe um curso com esse nome!");
        }
        
        if (dao.findByCodigo(curso.getCodigo()) != null) {
        throw new RuntimeException("Já existe um curso com esse código!");
        }


        //se depois de tudo isso estiver certo, salva o curso
        return dao.save(curso);
    }

    
    
     //edita ou melhor, atualiza um curso existente.
     //e aplica validações antes de atualizar.
    @Override
    public Curso update(Curso curso) {

    if (curso == null) {
        throw new RuntimeException("Curso não pode ser nulo!");
    }

    if (curso.getId() == null) {
        throw new RuntimeException("ID do curso é obrigatório para atualizar.");
    }

    if (StringUtil.isNullOrEmpty(curso.getNome())) {
        throw new RuntimeException("O nome do curso não pode ser vazio!");
    }

    // Verifica se já existe outro curso com o mesmo nome
    Curso cursoNome = dao.findByName(curso.getNome());
    if (cursoNome != null && !cursoNome.getId().equals(curso.getId())) {
        throw new RuntimeException("Já existe outro curso com esse nome!");
    }

    // Verifica se já existe outro curso com o mesmo código
    Curso cursoCodigo = dao.findByCodigo(curso.getCodigo());
    if (cursoCodigo != null && !cursoCodigo.getId().equals(curso.getId())) {
        throw new RuntimeException("Já existe outro curso com esse código!");
    }

    return dao.update(curso);
    }

    //esse deleta um curso.
     //mas igualmente aplica validações antes de excluir.
    @Override
    public void delete(Curso curso) {

        if (curso == null) {
            throw new RuntimeException("Curso não pode ser nulo!");
        }

        if (curso.getId() == null) {
            throw new RuntimeException("ID é obrigatório para deletar.");
        }

        dao.delete(curso);
    }

    @Override
    public List<Curso> findAll() { //lista todos os cursos cadastrados.
        return dao.findAll();
    }

    @Override
    public Curso findById(Long id) { //busca um curso pelo ID, e com validação.

        if (id == null) {
            throw new RuntimeException("ID não pode ser nulo!");
        }

        return dao.findById(id);
    }

    @Override
    public Curso findByName(String name) { //busca curso pelo nome

        if (StringUtil.isNullOrEmpty(name)) {
            throw new RuntimeException("Nome não pode ser vazio!");
        }

        return dao.findByName(name);
    }

    @Override
    public Curso findByCodigo(String codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
