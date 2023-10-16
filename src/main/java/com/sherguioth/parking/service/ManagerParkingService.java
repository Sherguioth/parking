package com.sherguioth.parking.service;

import com.sherguioth.parking.dto.VehicleDetails;
import com.sherguioth.parking.dto.VehicleInput;
import com.sherguioth.parking.dto.VehicleSummary;
import com.sherguioth.parking.entity.Vehicle;
import com.sherguioth.parking.repository.ParkedRepository;
import com.sherguioth.parking.repository.VehiclesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ManagerParkingService implements ParkingService {

    private final ParkedRepository parkedRepository;
    private final VehiclesRepository vehiclesRepository;

    @Override
    public Optional<List<VehicleDetails>> getAllVehicles() {
        List<VehicleDetails> vehicles = new ArrayList<>();
        vehiclesRepository.getAllVehicles().ifPresent(allVehicles -> allVehicles.forEach(vehicle -> {
            var vehicleDetails = new VehicleDetails();
            vehicleDetails.setPlate(vehicle.getPlate());
            vehicleDetails.setType(vehicle.getType());
            parkedRepository.getParkedByVehicle(vehicle)
                    .ifPresent(parked -> vehicleDetails.setEntryDate(parked.getEntryDate()));
            vehicles.add(vehicleDetails);
        }));
        return Optional.of(vehicles);
    }

    @Override
    public Optional<VehicleSummary> getVehicleByPlate(String plate) {
        var vehicleSummary = new VehicleSummary();
        this.vehiclesRepository.getVehicleByPlate(plate).ifPresent(vehicle -> {
            vehicleSummary.setPlate(vehicle.getPlate());
            vehicleSummary.setType(vehicle.getType());
            parkedRepository.getParkedByVehicle(vehicle).ifPresent(parked -> {
                parked.setExitDate(LocalDateTime.now());
                vehicleSummary.setEntryDate(parked.getEntryDate());
                vehicleSummary.setExitDate(parked.getExitDate());
                vehicleSummary.setBalance(this.calculateBalance(vehicleSummary.getEntryDate(),
                        vehicleSummary.getExitDate(), vehicle.getType()));
            });
        });
        return Optional.of(vehicleSummary);
    }

    @Override
    public Optional<VehicleDetails> enterVehicle(VehicleInput vehicleInput) {
        var vehicleDetails = new VehicleDetails();
        var vehicle = vehiclesRepository.saveVehicle(new Vehicle(vehicleInput.getPlate(), vehicleInput.getType()));
        if (vehicle.isPresent()){
            var parked = parkedRepository.saveParked(vehicle.get());
            parked.ifPresent(parkedAux -> {
                vehicleDetails.setPlate(parkedAux.getVehicle().getPlate());
                vehicleDetails.setType(parkedAux.getVehicle().getType());
                vehicleDetails.setEntryDate(parkedAux.getEntryDate());
            });
        }

        return Optional.of(vehicleDetails);
    }

    @Override
    public Optional<Boolean> exitVehicle(String plate) {
        var vehicleToDelete = this.vehiclesRepository.deleteVehicle(plate);
        return vehicleToDelete.isPresent() ? Optional.of(Boolean.TRUE): Optional.of(Boolean.FALSE);
    }

    private double calculateBalance(LocalDateTime start, LocalDateTime end, String type) {
        Map<String, Double> fees = new HashMap<>(3);
        fees.put("Car", 2500.0);
        fees.put("Motorcycle", 1200.0);
        fees.put("Bicycle", 800.0);

        var time = Duration.between(start, end).toMinutes();
        var hours = time / 60;
        hours = time % 60 > 0 ? hours + 1 : hours;

        return hours * fees.get(type);
    }
}
