package com.sherguioth.parking.repository;

import com.sherguioth.parking.entity.Parked;
import com.sherguioth.parking.entity.Vehicle;

import java.util.Optional;
import java.util.UUID;

public interface ParkedRepository {
    Optional<Parked> getParkedByVehicle(Vehicle vehicle);

    Optional<Parked> saveParked(Vehicle vehicle);
}
