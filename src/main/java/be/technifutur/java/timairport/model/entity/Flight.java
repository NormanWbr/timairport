package be.technifutur.java.timairport.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Long id;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private Boolean cancelled = false;
    //--------------------------------------------------//
    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departure;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destination;

    @ManyToOne
    @JoinColumn(name = "plane_id", nullable = false)
    private Plane plane;

    @ManyToOne
    @JoinColumn(name = "captain_id", nullable = false)
    private Pilot captain;

    @ManyToOne
    @JoinColumn(name = "first_officer_id")
    private Pilot firstOfficer;

    @OneToMany(mappedBy = "flight")
    private Set<Booking> bookings = new LinkedHashSet<>();
}
