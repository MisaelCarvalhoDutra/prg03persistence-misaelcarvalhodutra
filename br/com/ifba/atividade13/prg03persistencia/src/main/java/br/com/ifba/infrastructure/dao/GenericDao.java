package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity> {

    //faz o EntityManager para toda a aplicação
    
    // Cria  uma instância estática de EntityManagerFactory para ser compartilhada por toda a aplicação
    protected static EntityManagerFactory entityManagerFactory 
            = Persistence.createEntityManagerFactory("cursoPU");

    @Override
    public Entity salvar(Entity entity) {
        
        // Cria um novo EntityManager para manipular transações com o banco de dados
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try {//inicia uma trnasação
            entityManager.getTransaction().begin();
            entityManager.persist(entity);  // Salva no banco
            entityManager.getTransaction().commit(); //confirma transação
            return entity;
        } catch (Exception e) {
            //se ocorrer algum erro, desfaz
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;  // Lançar a exceção para ser tratada em um nível mais alto
        } finally {
            entityManager.close(); //garante que o EntityManager seja fechado, independente
        }
    }

    @Override
    public Entity atualizar(Entity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity); // aqui atualiza a entidade no banco de dados
            entityManager.getTransaction().commit(); //confirma transação
            return entity;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();  //garante que o EntityManager seja fechado, independente
        }
    }

    @Override
    public void excluir(Entity entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            //iniciaa transação
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(entity));  // esse remove do banco de dados
            entityManager.getTransaction().commit(); //confirma transação
        } catch (Exception e) {
            //se houver erro, desfaz
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            entityManager.close();  //garante que o EntityManager seja fechado, independente
        }
    }

    @Override
    public List<Entity> listarTodos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // faz a execução de uma consulta JPQL para buscar todas as entidades do tipo da classe genérica
            return entityManager.createQuery("from " + getTypeClass().getName(), getTypeClass()).getResultList();
        } finally {
            entityManager.close();  //garante que o EntityManager seja fechado, independente
        }
    }

    @Override
    public Entity buscarId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(getTypeClass(), id);  // esse faz a busca da entidade pelo ID
        } finally {
            entityManager.close(); //garante que o EntityManager seja fechado, independente
        }
    }

    // esse é o método para recuperar a classe da entidade
    protected Class<Entity> getTypeClass() {
        
        // faz o acesso a classe genérica usando reflexão, permitindo reuso da classe para diferentes tipos de entidades
        return (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
}
