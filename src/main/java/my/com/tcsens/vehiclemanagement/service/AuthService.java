package my.com.tcsens.vehiclemanagement.service;

import lombok.val;
import my.com.tcsens.vehiclemanagement.config.TokenProvider;
import my.com.tcsens.vehiclemanagement.dto.AuthToken;
import my.com.tcsens.vehiclemanagement.dto.LoginCredential;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public AuthService(
            AuthenticationManager authenticationManager,
            TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public AuthToken authenticateUser(LoginCredential loginCredential) {
        val authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginCredential.getLoginId(),
                loginCredential.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new AuthToken().token(tokenProvider.generateToken(authentication));
    }
}
