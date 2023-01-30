package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exception.RessourceNotFoundException;
import be.technifutur.java.timairport.model.dto.AirportDTO;
import be.technifutur.java.timairport.model.entity.Airport;
import be.technifutur.java.timairport.model.entity.TypePlane;
import be.technifutur.java.timairport.model.form.AirportInsertForm;
import be.technifutur.java.timairport.repository.AirportRepository;
import be.technifutur.java.timairport.repository.TypePlaneRepository;
import be.technifutur.java.timairport.service.AirportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;
    private final TypePlaneRepository typePlaneRepository;

    public AirportServiceImpl(
            AirportRepository airportRepository,
            TypePlaneRepository typePlaneRepository){
        this.airportRepository = airportRepository;
        this.typePlaneRepository = typePlaneRepository;
    }

    @Override
    public void create(AirportInsertForm form){
        Airport airport = form.toEntity();

        List<TypePlane> types = form.getTypeIds().stream()
                .map(
                        t -> typePlaneRepository.findById(t)
                                .orElseThrow(RessourceNotFoundException::new)
                )
                .toList();
        airport.setPlaneTypesAllowed(types);

        airportRepository.save(airport);
    }

    @Override
    public AirportDTO getOne(long id){
        return airportRepository.findById(id)
                .map(AirportDTO::from)
                .orElseThrow(RessourceNotFoundException::new);
    }

    @Override
    public List<AirportDTO> getAll(){
        return airportRepository.findAll().stream()
                .map(AirportDTO::from)
                .toList();
    }
}
