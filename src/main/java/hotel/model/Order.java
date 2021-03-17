package hotel.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.sql.Timestamp;

@Data
public class Order {
    @Setter(value = AccessLevel.NONE)
    private long id;
    private long apartmentId;
    private long userId;
    private double price;
    private OrderStatus status;
    private Timestamp startDate;

    public Order(long apartmentId, long userId, double price, OrderStatus status, Timestamp startDate) {
        this.apartmentId = apartmentId;
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.startDate = startDate;
    }
}
