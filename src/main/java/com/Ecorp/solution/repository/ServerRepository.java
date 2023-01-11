package com.Ecorp.solution.repository;

import com.Ecorp.solution.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Server s set s.cpu = ?1, s.name = ?2, s.price = ?3, s.ram = ?4, s.space = ?5, s.stype = ?6 WHERE s.id = ?7")
    void updateServer(int cpu, String name, double price, int ram, int space, String stype, Long id);
}
