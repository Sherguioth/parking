package com.sherguioth.parking.repository;

import com.sherguioth.parking.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehiclesRepository {
    Optional<Vehicle> getVehicleByPlate(String plate);

    Optional<List<Vehicle>> getAllVehicles();

    Optional<Vehicle> saveVehicle(Vehicle vehicle);

    Optional<Vehicle> deleteVehicle(String plate);
}
