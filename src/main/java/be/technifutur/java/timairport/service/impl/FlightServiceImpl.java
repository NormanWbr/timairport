package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exception.*;
import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.entity.*;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.repository.*;
import be.technifutur.java.timairport.service.FlightService;
import be.technifutur.java.timairport.service.PlaneService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

    @Override
    public void update(Long id, Map<String, Object> updateData) {
        if (updateData == null || updateData.isEmpty())
            return;
        Flight flight = flightRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);

        if (updateData.containsKey("departureTime")){
            LocalDateTime departureTime = (LocalDateTime) updateData.get("companyId");
            flight.setDepartureTime(departureTime);
        }
        if (updateData.containsKey("arrivalTime")){
            LocalDateTime arrivalTime = (LocalDateTime) updateData.get("arrivalTime");
            flight.setArrivalTime(arrivalTime);
        }
        if (updateData.containsKey("departureId")){
            Long departureId = (Long) updateData.get("departureId");
            Airport departure = airportRepository.findById(departureId)
                    .orElseThrow(NoAirportFoundException::new);
            flight.setDeparture(departure);
        }
        if (updateData.containsKey("destinationId")){
            Long destinationId = (Long) updateData.get("destinationId");
            Airport destination = airportRepository.findById(destinationId)
                    .orElseThrow(NoAirportFoundException::new);
            flight.setDestination(destination);
        }
        if (updateData.containsKey("firstOfficerId")){
            Long firstOfficerId = (Long) updateData.get("firstOfficerId");
            Pilot firstOfficer = pilotRepository.findById(firstOfficerId)
                    .orElseThrow(RessourceNotFoundException::new);
            flight.setFirstOfficer(firstOfficer);
        }
        if (updateData.containsKey("captainId")){
            Long captainId = (Long) updateData.get("captainId");
            Pilot captain = pilotRepository.findById(captainId)
                    .orElseThrow(RessourceNotFoundException::new);
            flight.setCaptain(captain);
        }
        if (updateData.containsKey("cancelled")){
            Boolean cancelled = (Boolean) updateData.get("cancelled");
            flight.setCancelled(cancelled);
        }
        if (updateData.containsKey("planeId")){
            Long planeId = (Long) updateData.get("planeId");
            Plane plane = planeRepository.findById(planeId)
                    .orElseThrow(RessourceNotFoundException::new);
            flight.setPlane(plane);
        }
        if (updateData.containsKey("bookings")){
            List<Booking> bookings = (List<Booking>) updateData.get("bookings");
            flight.setBookings(bookings);
        }

        //mmmmmh

        flightRepository.save(flight);
    }

    @Override
    public void delete(long id) {
        flightRepository.deleteById(id);
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
