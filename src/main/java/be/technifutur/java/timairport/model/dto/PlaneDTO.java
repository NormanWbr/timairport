package be.technifutur.java.timairport.model.dto;

import be.technifutur.java.timairport.model.entity.Plane;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PlaneDTO {
    private Long id;
    private String callSign;
    private LocalDate registrationDate;
    private Boolean inMaintenance;
    private TypeDTO type;
    private CompanyDTO company;

    @Data
    @Builder
    public static class TypeDTO {
        private Long id;
        private String name;
        private Integer capacity;
    }

    @Data
    @Builder
    public static class CompanyDTO{
        private Long id;
        private String name;
        private String originCountry;
    }

    public static PlaneDTO from(Plane entity){
        if(entity == null)
            return null;

        return PlaneDTO.builder()
                .id(entity.getId())
                .inMaintenance(entity.getInMaintenance())
                .callSign(entity.getCallSign())
                .registrationDate(entity.getRegistrationDate())
                .type(
                        PlaneDTO.TypeDTO.builder()
                                .id(entity.getType().getId())
                                .name(entity.getType().getName())
                                .capacity(entity.getType().getCapacity())
                                .build()
                )
                .company(
                        PlaneDTO.CompanyDTO.builder()
                                .id(entity.getCompany().getId())
                                .name(entity.getCompany().getName())
                                .originCountry(entity.getCompany().getOriginCountry())
                                .build()
                )
                .build();
    }
}
