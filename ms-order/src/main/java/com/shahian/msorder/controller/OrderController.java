package com.shahian.msorder.controller;

import com.shahian.msorder.model.Order;
import com.shahian.msorder.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/v1/orders")
    public ResponseEntity<List<Order>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/v1/order")
    public ResponseEntity<?> findById(@RequestParam Long id) {
        Optional<Order> order = orderService.findById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);

    }
    @GetMapping("/v1/orderByCustomerId")
    public ResponseEntity<?> getOrdersByCustomerId(@RequestParam Long customerId) {
//        List<Order> orders =  orderService.getOrdersByCustomerId(id);
//        if (orders.size() == 0) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(orders, HttpStatus.OK);
        return new ResponseEntity<>("your Order was created",HttpStatus.OK);

    }
    @PostMapping("/v1/order")
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @PutMapping("/v1/order")
    public ResponseEntity<Order> update(@RequestParam Long id, @RequestBody Order order) {
        return new ResponseEntity<>(orderService.update(id, order), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}