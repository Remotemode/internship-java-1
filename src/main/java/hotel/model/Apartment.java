package hotel.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Apartment {
    @Setter(value = AccessLevel.NONE)
    private long id;
    private long apartmentNumber;
    private long guestsNumber;
    private ApartmentCategory category;
    private ApartmentStatus status;
    private double price;

    public Apartment(long apartmentNumber, long guestsNumber, ApartmentCategory category, ApartmentStatus status, double price) {
        this.apartmentNumber = apartmentNumber;
        this.guestsNumber = guestsNumber;
        this.category = category;
        this.status = status;
        this.price = price;
    }
}

