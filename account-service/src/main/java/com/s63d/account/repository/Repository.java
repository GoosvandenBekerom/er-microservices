package com.s63d.account.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

public abstract class Repository<T, TID> {
    @PersistenceContext(unitName = "er-account")
    EntityManager em;

    private Class<T> entityClass;

    Repository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Persist this entity to the database
     * @param entity to persist
     * @return persisted entity
     */
    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    /**
     * Check if an entity exists in the database
     * @param id of this entity
     */
    public boolean exists(TID id) {
        return em.find(entityClass, id) != null;
    }

    /**
     * Get an entity by its id
     * @param id
     * @return instance of entity, can be null
     */
    public T getById(TID id) {
        return em.find(entityClass, id);
    }

    public List<T> getAll() { return this.getAll(50); }
    public List<T> getAll(int limit) {
        return em.createQuery("SELECT x FROM "+entityClass.getSimpleName()+" x", entityClass)
                .setMaxResults(limit).getResultList();
    }

    /**
     * Remove an entity from the database context
     * @param entity to delete
     */
    public void delete(T entity) {
        em.remove(entity);
    }

    /**
     * Remove an entity from the database context by its identifier
     * @param id of the entity to delete
     */
    public void deleteById(TID id) {
        em.remove(getById(id));
    }

    /**
     * Create a clean formatted 404 error for this entity
     * @param id
     * @return Error with statuscode 404 that can be interpreted by a jaxrs exception handler
     */
    public NotFoundException notFound(TID id) {
        return new NotFoundException(entityClass.getSimpleName()+" with id "+id+" not found.");
    }
}