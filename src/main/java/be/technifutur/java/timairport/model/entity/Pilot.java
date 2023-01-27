package be.technifutur.java.timairport.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter @Setter
public class Pilot extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pilot_id")
    private long id;

    @Column(name = "licence_id", nullable = false, unique = true)
    private String licenseId;

    @Column(name = "license_acquisition", nullable = false)
    private LocalDate licenseAcquisition;
//--------------------------------------------------//
    @OneToMany(mappedBy = "captain")
    private Set<Flight> flightsAsCaptain;

    @OneToMany(mappedBy = "firstOfficer")
    private Set<Flight> flightsAsFirstOfficer;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
