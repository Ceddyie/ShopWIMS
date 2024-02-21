package org.example.shopbackend.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private OrderService orderService;

    @PostMapping("/produce")
    public ResponseEntity<Order> saveAndProduceOrder (@RequestBody Order order) {
        Order orderWithId = orderRepository.save(order);
        System.out.println(orderWithId);
        orderProducer.produceOrder(orderWithId);
        System.out.println("Order sent");
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    public void updateOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.setShipped(true);
        orderRepository.save(order);
        System.out.println(order);
        System.out.println("Order is shipped");
    }

    @GetMapping("/getByCustomer/{customerId}")
    public List<Order> getOrdersByCustomer (@PathVariable long customerId) {
        return orderService.getOrdersByCustomer(customerId);
    }
}
