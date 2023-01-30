package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.dto.AirportDTO;
import be.technifutur.java.timairport.model.form.AirportInsertForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AirportService {
    void create(AirportInsertForm form);

    AirportDTO getOne(long id);

    List<AirportDTO> getAll();
}
