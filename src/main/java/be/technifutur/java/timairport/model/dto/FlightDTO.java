package be.technifutur.java.timairport.model.dto;

import be.technifutur.java.timairport.model.entity.Airport;
import be.technifutur.java.timairport.model.entity.Flight;
import be.technifutur.java.timairport.model.entity.Pilot;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FlightDTO {

    private Long id;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private AirportDTO destination;

    private AirportDTO departure;

    public static FlightDTO from(Flight entity) {
        if (entity == null)
            return null;

        return FlightDTO.builder()
                .id(entity.getId())
                .departureTime(entity.getDepartureTime())
                .arrivalTime(entity.getArrivalTime())
                .destination(AirportDTO.from(entity.getDestination()))
                .departure(AirportDTO.from(entity.getDeparture()))
                .build();


    }


}

