package com.s63d.account.repository;

import com.s63d.account.domain.User;

import javax.ejb.Stateless;

@Stateless
public class UserRepository extends Repository<User, String> {
    public UserRepository() { super(User.class); }
}
