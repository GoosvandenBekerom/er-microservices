package com.s63d.account.repository;

import com.s63d.account.domain.User;
import com.s63d.generic.Repository;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Stateless
public class UserRepository extends Repository<User, Long> {
    public UserRepository() { super(User.class); }

    public User findByEmail(String email) {
        Query q = em.createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email);
        try {
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
