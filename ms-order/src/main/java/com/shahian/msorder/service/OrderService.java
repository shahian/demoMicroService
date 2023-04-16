package com.shahian.msorder.service;



import com.shahian.msorder.model.Order;
import com.shahian.msorder.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order update(Long id,Order order) {
        return orderRepository.save(order);
    }

    public void delete(Long id ) {
        Order  order1 = orderRepository.findById(id).orElse(null);
        if (order1 !=null){
            orderRepository.delete(order1);
        }
        orderRepository.delete(order1);
    }


}