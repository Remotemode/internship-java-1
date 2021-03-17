package hotel.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class User {
    @Setter(value = AccessLevel.NONE)
    private long id;
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;

    public User(String phone, String password, String firstName, String lastName, UserRole role) {
        this.phone = phone;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}

