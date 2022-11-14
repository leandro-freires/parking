package br.com.teste.parking.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParkingReportDto {

    private Long id;

    private String time;

    private boolean paid;

    private boolean exit;

}
