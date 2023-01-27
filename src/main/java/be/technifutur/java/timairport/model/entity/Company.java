package be.technifutur.java.timairport.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "onigin_country", nullable = false)
    private String originCountry;
//--------------------------------------------------//
    @OneToMany(mappedBy = "company")
    private Set<Plane> planes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "company")
    private Set<Pilot> pilotes = new LinkedHashSet<>();
}
