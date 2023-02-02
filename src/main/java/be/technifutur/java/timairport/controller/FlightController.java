package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.repository.FlightRepository;
import be.technifutur.java.timairport.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;
    private final FlightRepository flightRepository;

    public FlightController(FlightService flightService,
                            FlightRepository flightRepository) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
    }

    @PostMapping("/add")
    public void create(@RequestBody @Valid FlightInsertForm form){
        if(flightService.verifForm(form))
            flightService.create(form);
    }

    @GetMapping("/{id:[0-9]+}")
    public FlightDTO getOne(@PathVariable Long id){
        return flightService.getOne(id);
    }

    @GetMapping("/all")
    public List<FlightDTO> getAll()    {
        return flightService.getAll();
    }

    @PatchMapping(path = "/{id:[0-9]+}/update")
    public void update(@PathVariable long id, @RequestParam Map<String, String> params) {
        Map<String, Object> mapValues = new HashMap<>();

        if (params.containsKey("departureTime"))
            mapValues.put("departureTime", LocalDateTime.parse(params.get("departureTime")));
        if (params.containsKey("arrivalTime"))
            mapValues.put("arrivalTime", LocalDateTime.parse(params.get("arrivalTime")));
        if (params.containsKey("departureId"))
            mapValues.put("departureId", Long.parseLong(params.get("departureId")));
        if (params.containsKey("destinationId"))
            mapValues.put("destinationId", Long.parseLong(params.get("destinationId")));
        if (params.containsKey("firstOfficerId"))
            mapValues.put("firstOfficerId", Long.parseLong(params.get("firstOfficerId")));
        if (params.containsKey("captainId"))
            mapValues.put("captainId", Long.parseLong(params.get("captainId")));
        if (params.containsKey("cancelled"))
            mapValues.put("cancelled", Boolean.parseBoolean(params.get("cancelled")));
        if (params.containsKey("planeId"))
            mapValues.put("planeId", Long.parseLong(params.get("planeId")));
        if (params.containsKey("bookings"))
            mapValues.put("bookings", List.of(params.get("bookings")));

        flightService.update(id, mapValues);
    }

    @DeleteMapping("/delete/{id:[0-9]+}")
    public void delete(@PathVariable long id) {
        flightService.delete(id);
    }

}
