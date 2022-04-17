package my.com.tcsens.vehiclemanagement.service;

import lombok.val;
import my.com.tcsens.vehiclemanagement.config.TokenProvider;
import my.com.tcsens.vehiclemanagement.dto.AuthToken;
import my.com.tcsens.vehiclemanagement.dto.LoginCredential;
import my.com.tcsens.vehiclemanagement.util.AESUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final AESUtil aesUtil;

    public AuthService(
            AuthenticationManager authenticationManager,
            TokenProvider tokenProvider,
            AESUtil aesUtil) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.aesUtil = aesUtil;
    }

    public AuthToken authenticateUser(LoginCredential loginCredential) {
        val authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                aesUtil.decrypt(loginCredential.getLoginId()),
                loginCredential.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new AuthToken().token(tokenProvider.generateToken(authentication));
    }
}
