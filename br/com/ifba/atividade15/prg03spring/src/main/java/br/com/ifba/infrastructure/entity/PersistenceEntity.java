package br.com.ifba.infrastructure.entity;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass //anotação do JPA,indica que a classe não será diretamente mapeada para uma tabela
//mas suas propriedades serão herdadas por outras classes.

public abstract class PersistenceEntity { 

    @Id //marca o campo como chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)//gera automatico o ID pelo banco de dados.
    private Long id;

    public Long getId() {
        return id; //vai retornar o id da entidade
    }

    public void setId(Long id) {
        this.id = id; //definir o id da entidade
    }
}
