package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exception.*;
import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.entity.*;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.repository.*;
import be.technifutur.java.timairport.service.FlightService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final AirportRepository airportRepository;

    private final PlaneRepository planeRepository;

    private final PilotRepository pilotRepository;
    private final CompanyRepository companyRepository;

    public FlightServiceImpl(FlightRepository flightRepository, AirportRepository airportRepository, PlaneRepository planeRepository, PilotRepository pilotRepository,
                             CompanyRepository companyRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.planeRepository = planeRepository;
        this.pilotRepository = pilotRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void create(FlightInsertForm form) {
        Flight flight = form.toEntity();

        Airport departure = airportRepository.findById(form.getDepartureId())
                .orElseThrow(RessourceNotFoundException::new);
        flight.setDeparture(departure);

        Airport destination = airportRepository.findById(form.getDestinationId())
                .orElseThrow(NoAirportFoundException::new);
        flight.setDestination(destination);

        Company company = companyRepository.findById(form.getCompanyId())
                .orElseThrow(RessourceNotFoundException::new);
        //types ok, company ok
        List<Plane> planes = planeRepository.findForFlight(departure.getPlaneTypesAllowed(), destination.getPlaneTypesAllowed(), company);
        //dispo pdnt le laps de temps
        planes.stream()
                .filter(
                        p -> p.getFlights().stream()
                                .filter(
                                        f -> form.getDepartureTime().isAfter(f.getArrivalTime())
                                                || form.getArrivalTime().isBefore(f.getDepartureTime()))
                                .toList().isEmpty())
                .toList();

        //est a l'aeroport
        /*
        prendre la liste des vols de l'avion
        prendre le vol juste avant la date form.departuretime
        comparer flight.destinationid a form.departureid
         */

        List<Plane> planesPresent = new ArrayList<>(
                planes.stream()
                        .filter(
                                p -> p.getFlights().stream()
                                        .filter(f -> f.getArrivalTime().isBefore(form.getDepartureTime()))
                                        .sorted(Comparator.comparing(Flight::getArrivalTime).reversed())
                                        .findFirst()
                                        .orElse(new Flight())
                                        .getDestination().equals(form.getDepartureId())
                        )
                        .toList()
        );


        flight.setPlane(planesPresent.isEmpty() ? planes.get(1) : planesPresent.get(1));

        Pilot pilot = pilotRepository.findById(form.getCaptainId())
                .orElseThrow(NoPilotAvailableException::new);
        flight.setCaptain(pilot);

        pilot = pilotRepository.findById(form.getFirstOfficerId())
                .orElseThrow(NoPilotAvailableException::new);
        flight.setFirstOfficer(pilot);

        flightRepository.save(flight);
    }

    @Override
    public FlightDTO getOne(Long id) {
        return flightRepository.findById(id)
                .map(FlightDTO::from)
                .orElseThrow(FlightNotFoundException::new);
    }

    @Override
    public List<FlightDTO> getAll() {
        return flightRepository.findAll().stream().map(FlightDTO::from).toList();
    }

    public boolean verifForm(FlightInsertForm form) {
        return form.getFirstOfficerId() != form.getCaptainId()
                && form.getDepartureId() != form.getDestinationId()
                && form.getDepartureTime().isBefore(form.getArrivalTime())
                && Duration.between(form.getDepartureTime(), form.getArrivalTime()).toDays() <= 2;
    }

//    private boolean verifAvion(FlightInsertForm form){
//        Plane plane = planeRepository.findById(form.getPlaneId())
//                .orElseThrow(RessourceNotFoundException::new);
//        Airport departure = airportRepository.findById(form.getDepartureId())
//                .orElseThrow(RessourceNotFoundException::new);
//        Airport arrival = airportRepository.findById(form.getDestinationId())
//                .orElseThrow(RessourceNotFoundException::new);
//
//        return departure.getPlaneTypesAllowed().contains(plane.getType().getId())
//                && arrival.getPlaneTypesAllowed().contains(plane.getType().getId())
//                && !plane.getInMaintenance();
//    }
}
