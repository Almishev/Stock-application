package com.store.demo.repository;

import com.store.demo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    @Query("SELECT u from User u Where u.username = :username")
    User getUserByUsername(@Param("username") String username);
}
