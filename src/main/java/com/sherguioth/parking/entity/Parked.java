package com.sherguioth.parking.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Parked {
    private UUID id;
    private Vehicle vehicle;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;

    public Parked(Vehicle vehicle) {
        this.id = UUID.randomUUID();
        this.vehicle = vehicle;
        this.entryDate = LocalDateTime.now();
        this.exitDate = null;
    }
}
