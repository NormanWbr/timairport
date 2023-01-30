package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exception.RessourceNotFoundException;
import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.entity.Airport;
import be.technifutur.java.timairport.model.entity.Flight;
import be.technifutur.java.timairport.model.entity.Pilot;
import be.technifutur.java.timairport.model.entity.Plane;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.repository.AirportRepository;
import be.technifutur.java.timairport.repository.FlightRepository;
import be.technifutur.java.timairport.repository.PilotRepository;
import be.technifutur.java.timairport.repository.PlaneRepository;
import be.technifutur.java.timairport.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final AirportRepository airportRepository;

    private final PlaneRepository planeRepository;

    private final PilotRepository pilotRepository;

    public FlightServiceImpl(FlightRepository flightRepository, AirportRepository airportRepository, PlaneRepository planeRepository, PilotRepository pilotRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.planeRepository = planeRepository;
        this.pilotRepository = pilotRepository;
    }

    @Override
    public void create(FlightInsertForm form) {
        Flight flight = form.toEntity();

        Airport airport = airportRepository.findById(form.getDepartureId())
                        .orElseThrow(RessourceNotFoundException::new);
        flight.setDeparture(airport);

        airport = airportRepository.findById(form.getDestinationId())
                .orElseThrow(RessourceNotFoundException::new);
        flight.setDestination(airport);

        Plane plane = planeRepository.findById(form.getPlaneId())
                        .orElseThrow(RessourceNotFoundException::new);
        flight.setPlane(plane);

        Pilot pilot = pilotRepository.findById(form.getCaptainId())
                        .orElseThrow(RessourceNotFoundException::new);
        flight.setCaptain(pilot);

        pilot = pilotRepository.findById(form.getFirstOfficerId())
                        .orElseThrow(RessourceNotFoundException::new);
        flight.setFirstOfficer(pilot);

        flightRepository.save(flight);
    }

    @Override
    public FlightDTO getOne(Long id) {
        return flightRepository.findById(id)
                .map(FlightDTO::from)
                .orElseThrow(RessourceNotFoundException::new);
    }

    @Override
    public List<FlightDTO> getAll() {
        return flightRepository.findAll().stream().map(FlightDTO::from).toList();
    }
}
