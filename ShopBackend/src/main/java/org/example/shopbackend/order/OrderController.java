package org.example.shopbackend.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {
    private final OrderProducer orderProducer;

    @Autowired
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/produce")
    public String saveAndProduceOrder (@RequestBody Order order) {
        Order orderWithId = orderRepository.save(order);
        System.out.println(orderWithId);
        orderProducer.produceOrder(orderWithId);
        return "Saved order and produced to kafka";
    }

    public void updateOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setShipped(true);
        orderRepository.save(order);
        System.out.println(order);
        System.out.println("Order is shipped");
    }
}
