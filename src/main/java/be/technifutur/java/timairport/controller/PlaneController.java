package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.exception.RessourceNotFoundException;
import be.technifutur.java.timairport.model.dto.PlaneDTO;
import be.technifutur.java.timairport.model.form.PlaneInsertForm;
import be.technifutur.java.timairport.service.PlaneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plane")
public class PlaneController {

    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public void create(@RequestBody @Valid PlaneInsertForm form) {
        planeService.create(form);
    }

    @GetMapping("/view/{id:[0-9]+}")
    public PlaneDTO getOne(@PathVariable long id) {
        try {
            return planeService.getOne(id);
        }catch (RessourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Wrong id", e);
        }
    }

    @GetMapping("/view/all")
    public List<PlaneDTO> getAll() {
        return planeService.getAll();
    }

    @PatchMapping("/update-maintenance")//j'ai plus rien
    public void updateCompany(@RequestParam Long id, @RequestParam Boolean inMaintenance) {
        planeService.updateMaintenance(id, inMaintenance);
    }

    @PatchMapping("/update-company")
    public void updateCompany(@RequestParam Long id, @RequestParam Long companyId) {
        planeService.updateCompany(id, companyId);
    }

    @PatchMapping(path = "/{id:[0-9]+}/update")
    public void update(@PathVariable long id, @RequestParam Map<String, String> params) {
        Map<String, Object> mapValues = new HashMap<>();
        if (params.containsKey("companyId"))
            mapValues.put("companyId", Long.parseLong(params.get("companyId")));
        if (params.containsKey("maintenance"))
            mapValues.put("maintenance", Boolean.parseBoolean(params.get("maintenance")));

        planeService.update(id, mapValues);
    }

    @DeleteMapping("/delete/{id:[0-9]+}")
    public void delete(@PathVariable long id) {
        planeService.delete(id);
    }

}