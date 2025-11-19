package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.repository.CursoRepository;
import br.com.ifba.infrastructure.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Aqui são realizadas validações 
//a Regra de negocio

@Service
public class CursoService implements CursoIService {

    @Autowired 
    private CursoRepository repository; //injeção de dependência
    //isso reduz o acoplamento, deixa o código mais limpo

    
    
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
        if (repository.findByNome(curso.getNome()) != null) {
            throw new RuntimeException("Já existe um curso com esse nome!");
        }
        
        if (repository.findByCodigo(curso.getCodigo()) != null) {
        throw new RuntimeException("Já existe um curso com esse código!");
        }


        //se depois de tudo isso estiver certo, salva o curso
        return repository.save(curso);
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
    Curso cursoNome = repository.findByNome(curso.getNome());
    if (cursoNome != null && !cursoNome.getId().equals(curso.getId())) {
        throw new RuntimeException("Já existe outro curso com esse nome!");
    }

    // Verifica se já existe outro curso com o mesmo código
    Curso cursoCodigo = repository.findByCodigo(curso.getCodigo());
    if (cursoCodigo != null && !cursoCodigo.getId().equals(curso.getId())) {
        throw new RuntimeException("Já existe outro curso com esse código!");
    }

    return repository.save(curso);  //update automático no Spring Data
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

        repository.delete(curso);
    }

    @Override
    public List<Curso> findAll() { //lista todos os cursos cadastrados.
        return repository.findAll();
    }

    @Override
    public Curso findById(Long id) { //busca um curso pelo ID, e com validação.

        if (id == null) {
            throw new RuntimeException("ID não pode ser nulo!");
        }

        return repository.findById(id).orElse(null);
    }

    @Override
    public Curso findByName(String name) { //busca curso pelo nome

        if (StringUtil.isNullOrEmpty(name)) {
            throw new RuntimeException("Nome não pode ser vazio!");
        }

        return repository.findByNome(name);
    }

    @Override
    public Curso findByCodigo(String codigo) {

        if (StringUtil.isNullOrEmpty(codigo)) {
            throw new RuntimeException("Código não pode ser vazio!");
        }

        return repository.findByCodigo(codigo);
    }
}
