package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.entity.Plane;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/plane")
public class PlaneController {
    @GetMapping("")
    public List<Plane> getAll(){
        return null;
    }
}
