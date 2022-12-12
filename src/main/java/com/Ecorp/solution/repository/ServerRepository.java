package com.Ecorp.solution.repository;

import com.Ecorp.solution.model.Server;
import com.Ecorp.solution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

}
