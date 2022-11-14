package br.com.teste.parking.models;

import br.com.teste.parking.core.models.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Parking extends SuperEntity {

    private String licenseNumber;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private boolean exited = false;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    private Ticket ticket;

}
