package hotel.service;

import hotel.controller.dto.CreateUserRequest;
import hotel.controller.dto.UpdateUserRequest;
import hotel.model.User;
import hotel.model.UserRole;
import hotel.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long createUser(CreateUserRequest request) {
        String phone = request.getPhone();
        String password = request.getPassword();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        UserRole role = request.getRole();

        User user = new User(phone, password, firstName, lastName, role);
        return userRepository.save(user).getId();
    }

    public User changePassword(long id, String password) {
        User user = findUserById(id);
        if (user != null) {
            user.setPassword(password);
            user = userRepository.save(user);
        }
        return user;
    }

    public User changeUserInfo(long id, UpdateUserRequest request) {
        User user = findUserById(id);
        if (user != null) {
            user.setPhone(request.getPhone());
            user.setPassword(request.getPassword());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setRole(request.getRole());

            user = userRepository.save(user);
        }
        return user;
    }

    public User findUserById(long userId) {
        return userRepository.findUserById(userId);
    }

    public User findUserByPhone(String phone) {
        return userRepository.findUserByPhone(phone);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
