package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.repository.FlightRepository;
import be.technifutur.java.timairport.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
