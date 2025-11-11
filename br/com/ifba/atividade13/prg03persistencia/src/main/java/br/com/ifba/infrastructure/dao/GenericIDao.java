/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import java.util.List;

/**
 *
 * @author dudan
 * @param <Entity>
 */

//interface Generica para as operações CRUD
// Define os métodos básicos para manipulação de entidades no banco de dados.
public interface GenericIDao<Entity extends PersistenceEntity> {

    // esse metodo salva a entidade no banco de dados
    public abstract Entity salvar(Entity obj);

    // esse atualiza os dados de uma entidade existente
    public abstract Entity atualizar(Entity obj);

    // esse exclui a entidade do banco de dados
    public abstract void excluir(Entity obj);

    // esse retorna uma lista de todas as entidades
    public abstract List<Entity> listarTodos();

    // busca uma entidade pelo seu ID
    public abstract Entity buscarId(Long id);
}
