package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


 //Representa a entidade Curso mapeada para o banco de dados.
 
// essa classe aqui herda de PersistenceEntity, que já define o ID da entidade.
@Entity
public class Curso extends PersistenceEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; //o nome do curso
    private String codigo; // o codigon do curso

    // aqui é construtor padrão exigido pelo JPA para instanciar a entidade sem parametros
    public Curso() {}

    //o construtor auxiliar para criar um novo curso
    public Curso(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    // getters e setters.
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}