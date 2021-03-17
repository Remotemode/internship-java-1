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
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @Setter(value= AccessLevel.NONE)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @NotNull
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(32)")
    private String phone;

    @NotNull
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(32)")
    private String password;

    @NotNull
    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(32)")
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false, columnDefinition = "VARCHAR(32)")
    private String lastName;

    @NotNull
    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(32)")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String phone, String password, String firstName, String lastName, UserRole role) {
        this.phone = phone;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}

