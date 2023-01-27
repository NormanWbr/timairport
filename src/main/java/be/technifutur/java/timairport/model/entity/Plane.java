package be.technifutur.java.timairport.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plane_id")
    private Long id;
    
    @Column(name = "call_sign", nullable = false, unique = true)
    private String callSign;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "in_maintenance", nullable = false)
    private Boolean inMaintenance;
//--------------------------------------------------//
    @OneToMany(mappedBy = "plane")
    private Set<Flight> flights = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "plane_type_id", nullable = false)
    private TypePlane type;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}