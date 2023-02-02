package be.technifutur.java.timairport.model.form;

import be.technifutur.constraints.Not0;
import be.technifutur.java.timairport.model.entity.Airport;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AirportInsertForm {
    @NotNull
    private String name;

    private String country;

    private String city;

    private String address;

    @NotNull
    @Not0
    private Integer planeParking;

    private List<Long> typeIds;

    public Airport toEntity(){
        Airport airport = new Airport();

        airport.setName(this.name);
        airport.setCountry(this.country);
        airport.setCity(this.city);
        airport.setAddress(this.address);
        airport.setPlaneParking(this.planeParking);

        return airport;
    }
}
