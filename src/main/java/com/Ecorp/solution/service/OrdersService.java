package com.Ecorp.solution.service;

import com.Ecorp.solution.DAO.OrdersDAO;
import com.Ecorp.solution.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdersService {

    OrdersDAO ordersDAO;

    public List<Orders> getAllOrdersByID(Long id) {
        return ordersDAO.getAllOrdersByID(id);
    }

    public void addNewOrders(List<Orders> orders) {
        ordersDAO.addNewOrders(orders);
    }
}
