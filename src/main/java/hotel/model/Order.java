package hotel.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @Setter(value = AccessLevel.NONE)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @NotNull
    @Column(name = "apartment_id", nullable = false, columnDefinition = "BIGINT(20)")
    private long apartmentId;

    @NotNull
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT(20)")
    private long userId;

    @NotNull
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,8)")
    private double price;

    @NotNull
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(32)")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull
    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMP")
    private Timestamp startDate;

    public Order(long apartmentId, long userId, double price, OrderStatus status, Timestamp startDate) {
        this.apartmentId = apartmentId;
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.startDate = startDate;
    }
}
