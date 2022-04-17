package my.com.tcsens.vehiclemanagement.util;

import org.springframework.stereotype.Component;

@Component
public class InputSanitizer {

    public void sanitizeDirectoryTraveller(String input) {
        if(input.indexOf("/") >= 0) {
            throw new IllegalArgumentException("sanitizeDirectoryTraveller : Invalid input [" + input + "]");
        }
    }

    public void sanitizeCommandExecute(String input) {
        if(input.indexOf(";") >= 0 ||
                input.indexOf("&&") >= 0 ||
                 input.indexOf("|") >= 0) {
            throw new IllegalArgumentException("sanitizeCommandExecute : Invalid input [" + input + "]");
        }
    }
}
