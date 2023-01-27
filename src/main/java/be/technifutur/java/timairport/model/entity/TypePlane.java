package be.technifutur.java.timairport.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class TypePlane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_plane_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private int capacity;
    //--------------------------------------------------//
    @ManyToMany(mappedBy = "planeTypesAllowed")
    private Set<Airport> airports = new LinkedHashSet<>();

    @OneToMany(mappedBy = "type")
    private Set<Plane> planes;
}
