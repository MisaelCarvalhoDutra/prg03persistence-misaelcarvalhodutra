package br.com.ifba.curso.repository;

import br.com.ifba.curso.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    //Esta interface herda de JpaRepository, que já fornece  métodos prontos para operações no banco de dados

    //busca curso pelo nome
    Curso findByNome(String nome);

    // vai buscar curso pelo código
    Curso findByCodigo(String codigo);
}
