package hotel.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hotel.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@JsonDeserialize
public class UpdateUserRequest {
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
}
