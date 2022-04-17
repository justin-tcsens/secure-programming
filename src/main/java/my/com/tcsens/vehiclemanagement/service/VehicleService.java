package my.com.tcsens.vehiclemanagement.service;


import lombok.val;
import my.com.tcsens.vehiclemanagement.dao.VehicleDao;
import my.com.tcsens.vehiclemanagement.dto.Vehicle;
import my.com.tcsens.vehiclemanagement.model.VehicleModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final VehicleDao vehicleDao;

    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public List<Vehicle> getVehicles(String carPlateNum) {
        return vehicleDao.getVehicleByCarPlateNumber(carPlateNum)
                .stream()
                .filter(Objects::nonNull)
                .map(this::mapDTO)
                .collect(Collectors.toList());
    }

    private Vehicle mapDTO(VehicleModel vehicleProfile) {
        return new Vehicle()
                .id(Long.valueOf(vehicleProfile.getId()))
                .carPlatNumber(vehicleProfile.getCarplatNumber())
                .carMake(vehicleProfile.getMake())
                .carModel(vehicleProfile.getModel())
                .chassisNumber(vehicleProfile.getChassisNumber())
                .axlesCount(vehicleProfile.getAxlesNumber())
                .tyreCount(vehicleProfile.getTyreNumber())
                .roadTaxExpiryDate(vehicleProfile.getRoadtaxExpiryDate())
                .manufactureDate(vehicleProfile.getManufactureYear())
                .imageName(vehicleProfile.getImageName());
    }
}
