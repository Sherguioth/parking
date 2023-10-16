package com.sherguioth.parking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleSummary {
    private String plate;
    private String type;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private double balance;
}
