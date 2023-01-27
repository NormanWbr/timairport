package be.technifutur.java.timairport.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class TypePlane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_plane_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Integer capacity;
    //--------------------------------------------------//
    @ManyToMany(mappedBy = "planeTypesAllowed")
    private List<Airport> airports = new ArrayList<>();

    @OneToMany(mappedBy = "type")
    private List<Plane> planes = new ArrayList<>();
}
