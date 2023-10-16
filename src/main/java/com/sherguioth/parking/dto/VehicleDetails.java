package com.sherguioth.parking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleDetails {
    private String plate;
    private String type;
    private LocalDateTime entryDate;
}
