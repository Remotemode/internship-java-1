package hotel.service;

import hotel.controller.dto.PlaceOrderRequest;
import hotel.model.Order;
import hotel.model.OrderStatus;
import hotel.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public long placeOrder(PlaceOrderRequest request) {
        long apartmentId = request.getApartmentId();
        long userId = request.getUserId();
        double price = request.getPrice();

        Order order = new Order(apartmentId, userId, price, OrderStatus.CREATED, Timestamp.from(Instant.now()));

        return orderRepository.save(order).getId();
    }

    public Order updateOrderStatus(long orderId, OrderStatus orderStatus) {
        Order order = findOrderById(orderId);
        order.setStatus(orderStatus);
        return orderRepository.save(order);
    }

    public Order findOrderById(long orderId) {
        return orderRepository.findOrderById(orderId);
    }

    public List<Order> findOrdersByUserId(long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public List<Order> findOrdersByOrderStatus(OrderStatus status) {
        return orderRepository.findAllByStatus(status);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> findNoPayedExpiredOrders() {
        Timestamp twoDaysAgo = Timestamp.from(Instant.now().minusSeconds(2*24*60*60));
        return orderRepository.findByStatusAndStartDateLessThan(OrderStatus.CONFIRMED, twoDaysAgo);
    }
}
