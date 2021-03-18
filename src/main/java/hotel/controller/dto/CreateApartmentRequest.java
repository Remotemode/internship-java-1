package hotel.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hotel.model.ApartmentCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@JsonDeserialize
public class CreateApartmentRequest {
    private long apartmentNumber;
    private long guestsNumber;
    private ApartmentCategory category;
    private double price;
}
