package com.Ecorp.solution.repository;


import com.Ecorp.solution.model.User;
import com.Ecorp.solution.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User user set user.password = ?1 where user.id = ?2")
    void updatePassword(String password, Long id);

    @Modifying
    @Transactional
    @Query("update User user set user.role = ?1 where user.id = ?2")
    void updateRole(UserRole role, Long id);

//
//    @Modifying
//    @Transactional
//    @Query("select user from User user where user.role = 'USER'")
//    List<User> getAllNonAdmins();

    @Modifying
    @Transactional
    @Query("select user from User user")
    List<User> getAllNonAdmins();
}


