package my.com.tcsens.vehiclemanagement.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CryptoUtil {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CryptoUtil(BCryptPasswordEncoder bCryptPasswordEncoder) {this.bCryptPasswordEncoder = bCryptPasswordEncoder;}

    public String encode(String input) {
        return bCryptPasswordEncoder.encode(input);
    }

}
