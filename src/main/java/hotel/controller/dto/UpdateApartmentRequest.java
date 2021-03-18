package hotel.controller.dto;

import hotel.model.ApartmentCategory;
import hotel.model.ApartmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UpdateApartmentRequest {
    private long apartmentNumber;
    private long guestsNumber;
    private ApartmentCategory category;
    private ApartmentStatus status;
    private double price;
}
