package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.dto.AirportDTO;
import be.technifutur.java.timairport.model.form.AirportInsertForm;
import be.technifutur.java.timairport.service.AirportService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService){
        this.airportService = airportService;
    }

    @PostMapping("/add")
    public void create(@RequestBody @Valid AirportInsertForm form){
        airportService.create(form);
    }

    @GetMapping("/view/{id:[0-9]+}")
    public AirportDTO getOne(@PathVariable long id){
        return airportService.getOne(id);
    }

    @GetMapping("/view/all")
    public List<AirportDTO> getAll(){
        return airportService.getAll();
    }

}
