package com.Ecorp.solution.DAO;

import com.Ecorp.solution.model.Orders;
import com.Ecorp.solution.repository.OrdersRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrdersDAO {

    private final OrdersRepository ordersRepository;

    public OrdersDAO(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> getAllOrdersByID(Long id) {
        return ordersRepository.getAllOrdersByUserId(id);
    }

    public void addNewOrders(List<Orders> orders) {
        ordersRepository.saveAll(orders);
    }
}
