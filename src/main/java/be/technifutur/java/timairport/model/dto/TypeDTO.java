package be.technifutur.java.timairport.model.dto;

import be.technifutur.java.timairport.model.entity.TypePlane;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeDTO {
    private Long id;
    private String name;
    private Integer capacity;

    public static TypeDTO from(TypePlane entity){
        if(entity == null)
            return null;

        return TypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .capacity(entity.getCapacity())
                .build();
    }
}
