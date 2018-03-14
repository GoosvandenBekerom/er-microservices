package com.s63d.account.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

abstract public class Repository<T, TId> {
    @PersistenceContext(unitName = "er-account")
    EntityManager em;

    private Class<T> entityClass;

    Repository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    public boolean exists(TId id) {
        return em.find(entityClass, id) != null;
    }

    /**
     * Get an entity by its id
     * @param id
     * @return instance of entity, can be null
     */
    public T getById(TId id) {
        return em.find(entityClass, id);
    }

    public List<T> getAll() { return this.getAll(50); }
    public List<T> getAll(int limit) {
        return em.createQuery("SELECT x FROM "+entityClass.getSimpleName()+" x", entityClass)
                .setMaxResults(limit).getResultList();
    }

    public void delete(T entity) {
        em.remove(entity);
    }

    public void deleteById(TId id) {
        em.remove(getById(id));
    }

    /**
     * Create a clean formatted 404 error for this entity
     * @param id
     * @return Error with statuscode 404 that can be interpreted by a jaxrs exception handler
     */
    public NotFoundException notFound(TId id) {
        return new NotFoundException(entityClass.getSimpleName()+" with id "+id+" not found.");
    }
}