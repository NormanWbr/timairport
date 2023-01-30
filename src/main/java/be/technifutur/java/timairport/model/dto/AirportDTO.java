package be.technifutur.java.timairport.model.dto;

import be.technifutur.java.timairport.model.entity.Airport;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AirportDTO {
    private Long id;
    private String city;
    private String address;
    private Integer planeParking;
    private List<TypeDTO> planesTypesAllowed;

    public static AirportDTO from(Airport entity){
        if(entity == null)
            return null;

        return AirportDTO.builder()
                .id(entity.getId())
                .city(entity.getCity() + ", " + entity.getCountry())
                .address(entity.getAddress())
                .planeParking(entity.getPlaneParking())
                .planesTypesAllowed(
                        entity.getPlaneTypesAllowed().stream()
                                .map(t -> TypeDTO.from(t))
                                .toList()
                )
                .build();
    }
}
