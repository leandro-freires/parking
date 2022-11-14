package br.com.teste.parking.resources.api.v1;

import br.com.teste.parking.models.dtos.ParkingReportDto;
import br.com.teste.parking.models.dtos.VehicleDto;
import br.com.teste.parking.core.validations.annotations.LicenseNumberBr;
import br.com.teste.parking.models.Parking;
import br.com.teste.parking.services.ParkingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/v1/parking")
public class ParkingResource {

    private ParkingService service;

    public ParkingResource(ParkingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> registerAccess(@RequestBody @Valid VehicleDto vehicle, HttpServletResponse response
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.registerAccess(vehicle));
    }

    @PutMapping("/{licenseNumber}/pay")
    public ResponseEntity<?> registerPayment(@PathVariable @LicenseNumberBr String licenseNumber, HttpServletResponse response
    ) {
        Parking parkingSaved = this.service.registerPayment(licenseNumber);
        return ResponseEntity.ok(parkingSaved);
    }

    @PutMapping("/{licenseNumber}/out")
    public ResponseEntity<?> registerExit(@PathVariable @LicenseNumberBr String licenseNumber, HttpServletResponse response
    ) {
        Parking parkingSaved = this.service.registerExit(licenseNumber);
        return ResponseEntity.ok(parkingSaved);
    }

    @GetMapping("/{licenseNumber}")
    public ResponseEntity<?> findAllParkingsByLicenseNumber(@PathVariable @LicenseNumberBr String licenseNumber, Pageable pageable) {
        Page<ParkingReportDto> parkingsPageable = this.service.findAllParkingsPageableByLicenseNumber(licenseNumber, pageable);
        return ResponseEntity.ok(parkingsPageable);
    }

}
