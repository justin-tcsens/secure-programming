package my.com.tcsens.vehiclemanagement.controller;

import my.com.tcsens.vehiclemanagement.api.ToolApi;
import my.com.tcsens.vehiclemanagement.util.AESUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ToolController implements ToolApi {

    private final AESUtil aesUtil;

    public ToolController(AESUtil aesUtil) { this.aesUtil = aesUtil;}

    @Override
    public ResponseEntity<String> encryptTextWithAES(String text) {
        return ResponseEntity.ok(aesUtil.encrypt(text));
    }

}
