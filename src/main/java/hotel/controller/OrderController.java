package hotel.controller;

import hotel.controller.dto.PlaceOrderRequest;
import hotel.model.ApartmentStatus;
import hotel.model.Order;
import hotel.model.OrderStatus;
import hotel.service.ApartmentService;
import hotel.service.OrderService;
import hotel.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final PermissionService permissionService;
    private final ApartmentService apartmentService;

    public OrderController(OrderService orderService, PermissionService permissionService, ApartmentService apartmentService) {
        this.orderService = orderService;
        this.permissionService = permissionService;
        this.apartmentService = apartmentService;
    }

    @PostMapping
    public long placeOrder(@RequestBody PlaceOrderRequest request) {
        return orderService.placeOrder(request);
    }

    @PutMapping("/status/confirmed/{userId}/{orderId}")
    public Order confirmOrder(@PathVariable long userId, @PathVariable long orderId) {
        permissionService.checkPermission(userId);
        Order order = orderService.updateOrderStatus(orderId, OrderStatus.CONFIRMED);
        apartmentService.editApartmentStatus(order.getApartmentId(), ApartmentStatus.BOOKED);
        return order;
    }

    @PutMapping("/status/payed/{userId}/{orderId}")
    public Order markOrderPayed(@PathVariable long userId, @PathVariable long orderId) {
        permissionService.checkPermission(userId);
        Order order = orderService.updateOrderStatus(orderId, OrderStatus.PAYED);
        apartmentService.editApartmentStatus(order.getApartmentId(), ApartmentStatus.BUSY);
        return order;
    }

    @PutMapping("/status/cancel/{orderId}/{userId}")
    public Order cancelOrder(@PathVariable long userId, @PathVariable long orderId) {
        permissionService.checkPermission(userId);
        Order order = orderService.updateOrderStatus(orderId, OrderStatus.CANCELLED);
        apartmentService.editApartmentStatus(order.getApartmentId(), ApartmentStatus.AVAILABLE);
        return order;
    }

    @GetMapping("/userid/{userId}")
    public List<Order> findOrdersByUserId(@PathVariable long userId) {
        return orderService.findOrdersByUserId(userId);
    }

    @GetMapping("/id/{id}")
    public Order findOrderById(@PathVariable long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping("/status/{status}/{userId}")
    public List<Order> findOrdersByOrderStatus(@PathVariable long userId, @PathVariable OrderStatus status) {
        permissionService.checkPermission(userId);
        return orderService.findOrdersByOrderStatus(status);
    }

    @GetMapping("/{userId}")
    public List<Order> findAllOrders(@PathVariable long userId) {
        permissionService.checkPermission(userId);
        return orderService.findAllOrders();
    }

    @GetMapping("/expired/{userId}")
    public List<Order> findNoPayedExpiredOrders(@PathVariable long userId) {
        permissionService.checkPermission(userId);
        return orderService.findNoPayedExpiredOrders();
    }

    @PutMapping("/cancel/{userId}")
    public List<Order> cancelNoPayedOrders(@PathVariable long userId) {
        permissionService.checkPermission(userId);
        List<Order> expiredOrders = findNoPayedExpiredOrders(userId);
        for (Order order : expiredOrders) {
            order.setStatus(OrderStatus.CANCELLED);
            apartmentService.editApartmentStatus(order.getApartmentId(), ApartmentStatus.AVAILABLE);
        }
        return expiredOrders;
    }
}
