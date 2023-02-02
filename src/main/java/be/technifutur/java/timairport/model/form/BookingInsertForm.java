package be.technifutur.java.timairport.model.form;
//generate booking insert form
import be.technifutur.java.timairport.model.entity.Booking;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class BookingInsertForm {

    @NotNull
    private LocalDateTime bookingDate;

    @NotNull
    private Long flightId;

    @NotNull
    private Long passengerId;

    public Booking toEntity() {
        Booking booking = new Booking();

        booking.setBookingDate(this.bookingDate);

        return booking;
    }
}

