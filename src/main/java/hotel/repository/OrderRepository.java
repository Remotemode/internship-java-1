package hotel.repository;

import hotel.model.Order;
import hotel.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(long userId);

    List<Order> findAllByStatus(OrderStatus status);

    List<Order> findByStatusAndStartDateLessThan(OrderStatus status, Timestamp twoDaysAgo);

    Order findOrderById(long orderId);
}
