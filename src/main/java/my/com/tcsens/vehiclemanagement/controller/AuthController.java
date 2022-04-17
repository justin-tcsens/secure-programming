package my.com.tcsens.vehiclemanagement.controller;

import my.com.tcsens.vehiclemanagement.api.AuthApi;
import my.com.tcsens.vehiclemanagement.dto.AuthToken;
import my.com.tcsens.vehiclemanagement.dto.LoginCredential;
import my.com.tcsens.vehiclemanagement.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController implements AuthApi {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthToken> authenticateUser(LoginCredential loginCredential) {
        return ResponseEntity.ok(authService.authenticateUser(loginCredential));
    }
}
