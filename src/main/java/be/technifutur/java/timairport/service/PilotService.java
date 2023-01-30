package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.dto.PilotDTO;
import be.technifutur.java.timairport.model.entity.Pilot;
import be.technifutur.java.timairport.repository.PilotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotService {
    private final PilotRepository pilotRepository;

    public PilotService(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    //--------------------------------------------------//
    public List<PilotDTO> getAll() {
        return pilotRepository.findAll().stream()
                .map(PilotDTO::from)
                .toList();
    }
}