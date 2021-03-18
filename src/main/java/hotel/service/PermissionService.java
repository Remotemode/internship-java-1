package hotel.service;

import hotel.model.User;
import hotel.model.UserRole;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    private final UserService userService;

    public PermissionService(UserService userService) {
        this.userService = userService;
    }

    private boolean isPermitted(long userId) {
        User user = userService.findUserById(userId);
        return user.getRole().equals(UserRole.ADMINISTRATOR);
    }

    public void checkPermission(long userId) {
        if (!isPermitted(userId)) {
            throw new RuntimeException("No permission for this operation. User isn't admin");
        }
    }
}
