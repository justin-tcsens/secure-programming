package my.com.tcsens.vehiclemanagement.controller;

import lombok.val;
import my.com.tcsens.vehiclemanagement.api.VehicleApi;
import my.com.tcsens.vehiclemanagement.dto.Vehicle;
import my.com.tcsens.vehiclemanagement.model.VehicleModel;
import my.com.tcsens.vehiclemanagement.service.VehicleService;
import my.com.tcsens.vehiclemanagement.util.InputSanitizer;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class VehicleController implements VehicleApi {
    private final VehicleService vehicleService;
    private final InputSanitizer inputSanitizer;

    public VehicleController(
            VehicleService vehicleService,
            InputSanitizer inputSanitizer) {
        this.vehicleService = vehicleService;
        this.inputSanitizer = inputSanitizer;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN','SYS_ADMIN')")
    public ResponseEntity<List<Vehicle>> _getVehicles(Integer pageNo, Integer pageSize, String carPlateNumber) {
        return ResponseEntity.ok(vehicleService.getVehicles(carPlateNumber));
    }

    @Override
    public ResponseEntity<org.springframework.core.io.Resource> getVehicleImageByFileName(String fileName) {

        inputSanitizer.sanitizeDirectoryTraveller(fileName);
        try {
            val resource = getResource(fileName);
            return ResponseEntity.ok().headers(getResourceHttpHeader(fileName))
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .contentLength(resource.contentLength()).body(resource);
        } catch (Exception e) {
            throw new IllegalArgumentException("Resource not found!!");
        }
    }

    private ByteArrayResource getResource(String fileName) throws IOException {
        val filePath = "image" + File.separator + fileName;
        val resourcePath = Paths.get(filePath);
        return new ByteArrayResource(Files.readAllBytes(resourcePath));
    }

    private HttpHeaders getResourceHttpHeader(String fileName) {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName));
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;
    }
}
