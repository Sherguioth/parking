package com.sherguioth.parking.repository;

import com.sherguioth.parking.entity.Parked;
import com.sherguioth.parking.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryParkedRepository implements ParkedRepository{

    private final Map<UUID, Parked> parkedMap;

    public InMemoryParkedRepository() {
        this.parkedMap = new HashMap<>();
    }

    @Override
    public Optional<Parked> getParkedByVehicle(Vehicle vehicle) {
        return this.parkedMap.values().stream().filter(parked -> parked.getVehicle().equals(vehicle)).findFirst();
    }

    @Override
    public Optional<Parked> saveParked(Vehicle vehicle) {
        var parked = new Parked(vehicle);
        this.parkedMap.put(parked.getId(), parked);
        return Optional.of(parked);
    }
}
