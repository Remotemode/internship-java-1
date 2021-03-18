package hotel.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import hotel.controller.dto.CreateUserRequest;
import hotel.controller.dto.UpdateUserRequest;
import hotel.model.User;
import hotel.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public long createUser(@JsonProperty @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/password/{id}")
    public User changePassword(@PathVariable long id, @RequestParam String password) {
        return userService.changePassword(id, password);
    }

    @PutMapping("/{id}")
    public User changeUserInfo(@PathVariable long id, @JsonProperty @RequestParam UpdateUserRequest request) {
        return userService.changeUserInfo(id, request);
    }

    @GetMapping("/id/{id}")
    public User findUserById(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/phone/{phone}")
    public User findUserByPhone(@PathVariable String phone) {
        return userService.findUserByPhone(phone);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }
}
