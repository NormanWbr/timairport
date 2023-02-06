package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface FlightService {

    void create(FlightInsertForm form);

    FlightDTO getOne(Long id);

    List<FlightDTO> getAll();

    boolean verifForm(FlightInsertForm form);

    void delete(Long id);

    void update(Long id, Map<String, Object> updateData);
}
