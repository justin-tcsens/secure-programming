package my.com.tcsens.vehiclemanagement.controller;

import my.com.tcsens.vehiclemanagement.api.UserApi;
import my.com.tcsens.vehiclemanagement.dto.User;
import my.com.tcsens.vehiclemanagement.service.UserService;
import my.com.tcsens.vehiclemanagement.util.TokenSecUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController implements UserApi {
    private final TokenSecUtil tokenSecUtil;
    private final UserService userService;

    public UserController(
            TokenSecUtil tokenSecUtil,
            UserService userService) {
        this.tokenSecUtil = tokenSecUtil;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> getUserThumbprint(String userId) {
        return ResponseEntity.ok(tokenSecUtil.verifyKey(userId));
    }

    @Override
    public ResponseEntity<User> getUserById(String userId) {
        return ResponseEntity.ok(userService.getUserByLoginId(userId));

    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
