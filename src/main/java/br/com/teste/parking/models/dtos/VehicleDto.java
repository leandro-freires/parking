package br.com.teste.parking.models.dtos;

import br.com.teste.parking.core.validations.annotations.LicenseNumberBr;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class VehicleDto {

    @LicenseNumberBr
    @NotEmpty(message = "{msg.validacao.NotEmpty.Vehicle.plate.message}")
    private String licenseNumber;

}
