package com.Ecorp.solution.repository;

import com.Ecorp.solution.model.Orders;
import com.aspose.pdf.internal.imaging.system.collections.Generic.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.userid = ?1")
    List<Orders> getAllOrdersByUserId(Long id);

}
