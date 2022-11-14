package br.com.teste.parking.models;

import br.com.teste.parking.core.models.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Ticket extends SuperEntity {

    private String ticketNumber;

    private LocalDateTime paymentTime;

    private boolean payed = false;

}
