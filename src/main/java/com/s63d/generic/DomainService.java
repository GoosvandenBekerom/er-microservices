package com.s63d.generic;

import java.util.List;

public class DomainService<T, TID> {
    Repository<T, TID> repo;
    public DomainService() {}
    public DomainService(Repository<T, TID> repo) { this.repo = repo; }

    public List<T> getAll() {
        return repo.getAll();
    }

    public T getById(TID id) {
        T entity = repo.getById(id);
        if (entity == null) throw repo.notFound(id);
        return entity;
    }

    public boolean exists(TID id) {
        return repo.exists(id);
    }

    public T save(T entity) {
        return repo.save(entity);
    }

    public void deleteById(TID id) {
        repo.deleteById(id);
    }

    public void delete(T entity) {
        repo.delete(entity);
    }
}
