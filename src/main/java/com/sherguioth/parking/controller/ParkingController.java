package com.sherguioth.parking.controller;

import com.sherguioth.parking.dto.VehicleDetails;
import com.sherguioth.parking.dto.VehicleInput;
import com.sherguioth.parking.dto.VehicleSummary;
import com.sherguioth.parking.service.ParkingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDetails>> getAllVehicles() {
        return ResponseEntity.of(parkingService.getAllVehicles());
    }

    @GetMapping("/vehicle/{plate}")
    public ResponseEntity<VehicleSummary> getVehicleByPlate(@PathVariable String plate) {
        return ResponseEntity.of(parkingService.getVehicleByPlate(plate));
    }

    @PostMapping("/vehicle")
    public ResponseEntity<VehicleDetails> enterVehicle(@RequestBody VehicleInput vehicle) {
        return ResponseEntity.of(parkingService.enterVehicle(vehicle));
    }

    @PutMapping("/vehicle/{plate}")
    public ResponseEntity<Boolean> exitVehicle(@PathVariable String plate) {
        return ResponseEntity.of(parkingService.exitVehicle(plate));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> greetings() {
        return ResponseEntity.ok().body("Hello from the parking");
    }

}
