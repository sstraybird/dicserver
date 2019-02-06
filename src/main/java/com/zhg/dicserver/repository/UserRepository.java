package com.zhg.dicserver.repository;

import org.springframework.data.repository.CrudRepository;

import com.zhg.dicserver.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}