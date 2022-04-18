package my.com.tcsens.vehiclemanagement.config;

import my.com.tcsens.vehiclemanagement.dto.PlatformError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.OffsetDateTime;
import java.util.ResourceBundle;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public PlatformError handlerGeneralException(Exception exception) {
        return new PlatformError().code("9999")
                .message(exception.getMessage())
                .timestamp(OffsetDateTime.now().toString());
    }
}