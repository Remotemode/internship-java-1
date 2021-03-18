package hotel.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "apartments")
@NoArgsConstructor
public class Apartment {
    @Id
    @Setter(value = AccessLevel.NONE)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @NotNull
    @Column(name = "apartment_number", nullable = false, columnDefinition = "BIGINT(20)")
    private long apartmentNumber;

    @NotNull
    @Column(name = "guests_number", nullable = false, columnDefinition = "BIGINT(20)")
    private long guestsNumber;

    @NotNull
    @Column(name = "category", nullable = false, columnDefinition = "VARCHAR(32)")
    @Enumerated(EnumType.STRING)
    private ApartmentCategory category;

    @NotNull
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(32)")
    @Enumerated(EnumType.STRING)
    private ApartmentStatus status;

    @NotNull
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(5,2)")
    private double price;

    public Apartment(long apartmentNumber, long guestsNumber, ApartmentCategory category, ApartmentStatus status, double price) {
        this.apartmentNumber = apartmentNumber;
        this.guestsNumber = guestsNumber;
        this.category = category;
        this.status = status;
        this.price = price;
    }
}

