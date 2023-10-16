package com.sherguioth.parking.service;

import com.sherguioth.parking.dto.VehicleDetails;
import com.sherguioth.parking.dto.VehicleInput;
import com.sherguioth.parking.dto.VehicleSummary;

import java.util.List;
import java.util.Optional;

public interface ParkingService {

    Optional<List<VehicleDetails>> getAllVehicles();

    Optional<VehicleSummary> getVehicleByPlate(String plate);

    Optional<VehicleDetails> enterVehicle(VehicleInput vehicleInput);

    Optional<Boolean> exitVehicle(String plate);
}
