package com.Ecorp.solution.controller;

import com.Ecorp.solution.model.Orders;
import com.Ecorp.solution.model.Server;
import com.Ecorp.solution.service.OrdersService;
import com.Ecorp.solution.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrdersService ordersService;

    @PostMapping()
    public void newServer(@RequestBody List<Orders> orders) {
        ordersService.addNewOrders(orders);
    }

    @GetMapping(value = "{id}")
    public List<Orders> getAllOrdersByID(@PathVariable Long id) {
        return ordersService.getAllOrdersByID(id);
    }
}