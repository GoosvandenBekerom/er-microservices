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

    public T getById(TId id) {
        T entity = em.find(entityClass, id);
        if (entity == null) throw notFound(id);
        return entity;
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

    public NotFoundException notFound(TId id) {
        return new NotFoundException(entityClass.getSimpleName()+" with id "+id+" not found.");
    }
}