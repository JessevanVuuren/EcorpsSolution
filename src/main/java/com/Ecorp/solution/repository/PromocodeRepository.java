package com.Ecorp.solution.repository;

import com.Ecorp.solution.model.Orders;
import com.Ecorp.solution.model.Promocode;
import com.aspose.pdf.internal.imaging.system.collections.Generic.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PromocodeRepository extends JpaRepository<Promocode, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Promocode p SET p.name = ?2, p.amountoff = ?3 WHERE p.id = ?1")
    void update(Long id, String name, double amountoff);

}
