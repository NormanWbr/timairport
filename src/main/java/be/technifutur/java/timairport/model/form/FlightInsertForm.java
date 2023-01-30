package be.technifutur.java.timairport.model.form;

import be.technifutur.java.timairport.model.entity.Flight;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightInsertForm {
    @NotNull
    @Future
    private LocalDateTime departureTime;
    @NotNull
    @Future()
    private LocalDateTime arrivalTime;
    @NotNull
    private Long departureId;
    @NotNull
    private Long destinationId;
    @NotNull
    private Long planeId;
    @NotNull
    private Long firstOfficerId;
    @NotNull
    private Long captainId;

    public Flight toEntity(){
        Flight flight = new Flight();

        flight .setDepartureTime(this.departureTime);
        flight.setArrivalTime(this.arrivalTime);

        return flight;
    }
}
