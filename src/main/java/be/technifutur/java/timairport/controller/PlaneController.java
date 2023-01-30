package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.dto.PlaneDTO;
import be.technifutur.java.timairport.model.form.PlaneInsertForm;
import be.technifutur.java.timairport.service.PlaneService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plane")
public class PlaneController {

    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @PostMapping("/add")
    public void create(@RequestBody @Valid PlaneInsertForm form) {
        planeService.create(form);
    }

    @GetMapping("/view/{id:[0-9]+}")
    public PlaneDTO getOne(@PathVariable long id) {
        return planeService.getOne(id);
    }

    @GetMapping("/view/all")
    public List<PlaneDTO> getAll() {
        return planeService.getAll();
    }

    @PatchMapping("/update-maintenance/{id}")
    public void updateMaintenance(@PathVariable("id") Long idPlane, @RequestBody Map<Long, Boolean> map) {
        map.forEach((key, value) -> planeService.updateMaintenance(key, value));
    }

    @PatchMapping("/update-company/{id}")
    public void updateCompany(@PathVariable("id") Long idPLane, @RequestBody Map<Long, Long> map) {
        map.forEach((key, value) -> planeService.updateCompany(key, value));
    }
}