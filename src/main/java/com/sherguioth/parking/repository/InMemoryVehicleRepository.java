package com.sherguioth.parking.repository;

import com.sherguioth.parking.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryVehicleRepository implements VehiclesRepository {

    private final Map<String, Vehicle> vehiclesMap;

    public InMemoryVehicleRepository() {
        this.vehiclesMap = new HashMap<>();
    }

    @Override
    public Optional<Vehicle> getVehicleByPlate(String plate) {
        return Optional.of(this.vehiclesMap.get(plate));
    }

    @Override
    public Optional<List<Vehicle>> getAllVehicles() {
        return Optional.of(vehiclesMap.values().stream().toList());
    }

    @Override
    public Optional<Vehicle> saveVehicle(Vehicle vehicle) {
        this.vehiclesMap.put(vehicle.getPlate(), vehicle);
        return Optional.of(vehicle);
    }

    @Override
    public Optional<Vehicle> deleteVehicle(String plate) {
        return Optional.of(this.vehiclesMap.remove(plate));
    }
}
