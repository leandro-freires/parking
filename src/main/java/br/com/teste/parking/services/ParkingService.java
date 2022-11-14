package br.com.teste.parking.services;

import br.com.teste.parking.core.utils.StringUtil;
import br.com.teste.parking.models.Parking;
import br.com.teste.parking.models.Ticket;
import br.com.teste.parking.models.dtos.ParkingDto;
import br.com.teste.parking.models.dtos.ParkingReportDto;
import br.com.teste.parking.models.dtos.VehicleDto;
import br.com.teste.parking.core.exceptions.BusinessException;
import br.com.teste.parking.repositories.ParkingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }

    public ParkingDto registerAccess(VehicleDto vehicleDto) {
        if (this.checkIfVehicleHasAlreadyEntered(vehicleDto.getLicenseNumber()))
            throw new BusinessException("msg.BusinessException.Parking.VehicleHasAlreadyEnteredOrHaveDebits.message");
        ParkingDto parkingDto = new ParkingDto();
        Parking parking = new Parking();
        parking.setTicket(new Ticket());
        parking.setLicenseNumber(vehicleDto.getLicenseNumber());
        parking.setEntryTime(LocalDateTime.now());
        parking.getTicket().setTicketNumber(StringUtil.generateRandomNumber());
        this.save(parking);
        BeanUtils.copyProperties(parking.getTicket(), parkingDto);
        return parkingDto;
    }

    private boolean checkIfVehicleHasAlreadyEntered(String licenseNumber) {
        return this.repository.existsByLicenseNumberAndExitedIsFalse(licenseNumber);
    }

    public Parking registerPayment(String licenseNumber) {
        try {
            Parking parkingSaved = this.findParkingByLicenseNumberAndExitedIsFalseAndPayedIsFalse(licenseNumber);
            parkingSaved.getTicket().setPaymentTime(LocalDateTime.now());
            parkingSaved.getTicket().setPayed(Boolean.TRUE);
            return this.save(parkingSaved);
        } catch (EntityNotFoundException ex) {
            throw new BusinessException("msg.BusinessException.Parking.VehicleDontHaveDebits.message");
        }
    }

    private Parking findParkingByLicenseNumberAndExitedIsFalseAndPayedIsFalse(String licenseNumber) {
        return this.repository.findParkingByLicenseNumberAndExitedAndPayed(licenseNumber, false, false).orElseThrow(EntityNotFoundException::new);
    }

    public Parking registerExit(String licenseNumber) {
        try {
            Parking parkingSaved = this.findParkingByLicenseNumberAndExitedIsFalseAndPayedIsTrue(licenseNumber);
            parkingSaved.setExitTime(LocalDateTime.now());
            parkingSaved.setExited(Boolean.TRUE);
            return this.save(parkingSaved);
        } catch (EntityNotFoundException ex) {
            throw new BusinessException("msg.BusinessException.Parking.VehicleDontHaveDebits.message");
        }
    }

    private Parking findParkingByLicenseNumberAndExitedIsFalseAndPayedIsTrue(String licenseNumber) {
        return this.repository.findParkingByLicenseNumberAndExitedAndPayed(licenseNumber, false, true).orElseThrow(EntityNotFoundException::new);
    }

    private Parking save(Parking parking) {
        return this.repository.save(parking);
    }

    public Page<ParkingReportDto> findAllParkingsPageableByLicenseNumber(String licenseNumber, Pageable pageable) {
        Page<Parking> page = this.repository.findAllByLicenseNumber(licenseNumber, pageable);
        return new PageImpl<>(
            page.getContent().stream().map(
                parking -> new ParkingReportDto(
                      parking.getId()
                    , ParkingService.getTotalTimeInMinutes(parking)
                    , parking.getTicket().isPayed()
                    , parking.isExited()
                )
            ).collect(Collectors.toList()), pageable, page.getTotalElements()
        );
    }

    private static String getTotalTimeInMinutes(Parking parking) {
        LocalDateTime endTime = ObjectUtils.isEmpty(parking.getExitTime()) ? LocalDateTime.now() : parking.getExitTime();
        long totalTimeInMinutes = parking.getEntryTime().until(endTime, ChronoUnit.MINUTES);
        return  String.format("%d minutes", totalTimeInMinutes);
    }
}
