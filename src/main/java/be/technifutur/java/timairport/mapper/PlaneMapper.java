package be.technifutur.java.timairport.mapper;

import be.technifutur.java.timairport.model.dto.PlaneDTO;
import be.technifutur.java.timairport.model.dto.TypeDTO;
import be.technifutur.java.timairport.model.entity.Plane;
import be.technifutur.java.timairport.model.form.PlaneInsertForm;

//@Service autre facon de mapper
public class PlaneMapper {
    public PlaneDTO toDTO(Plane entity){
        if(entity == null)
                return null;

        return PlaneDTO.builder()
                .id(entity.getId())
                .inMaintenance(entity.getInMaintenance())
                .callSign(entity.getCallSign())
                .registrationDate(entity.getRegistrationDate())
                .type(TypeDTO.from(entity.getType()))
                .company(
                        PlaneDTO.CompanyDTO.builder()
                                .id(entity.getCompany().getId())
                                .name(entity.getCompany().getName())
                                .originCountry(entity.getCompany().getOriginCountry())
                                .build()
                )
                .build();
    }

    public Plane toEntity(PlaneInsertForm form){
        Plane entity = new Plane();

        entity.setCallSign(form.getCallSign());
        entity.setRegistrationDate(form.getRegistrationDate());

        return entity;
    }

}
