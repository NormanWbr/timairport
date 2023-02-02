package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.dto.PlaneDTO;
import be.technifutur.java.timairport.model.form.PlaneInsertForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PlaneService {
    void create(PlaneInsertForm form);

    PlaneDTO getOne(long id);

    List<PlaneDTO> getAll();

    void updateMaintenance(Long id, Boolean inMaintenance);

    void updateCompany(Long id, Long companyId);
    void update(Long id, Map<String, Object> updateData);

    void delete(long id);
}
