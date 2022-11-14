package br.com.teste.parking.repositories;

import br.com.teste.parking.models.Parking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    boolean existsByLicenseNumberAndExitedIsFalse(String licenseNumber);

    Page<Parking> findAllByLicenseNumber(String licenseNumber, Pageable pageable);

    @Query("SELECT p FROM Parking p JOIN p.ticket t WHERE p.licenseNumber = :licenseNumber AND p.exited = :exited AND t.payed = :payed")
    Optional<Parking> findParkingByLicenseNumberAndExitedAndPayed(
        @Param("licenseNumber") String licenseNumber,
        @Param("exited") boolean exited,
        @Param("payed") boolean payed
    );

}
