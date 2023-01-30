package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.dto.PlaneDTO;
import be.technifutur.java.timairport.model.form.PlaneInsertForm;

import java.util.List;

public interface PlaneService {
    void create(PlaneInsertForm form);

    PlaneDTO getOne(long id);

    List<PlaneDTO> getAll();

    void updateMaintenance(Long id, Boolean updateMaintenance);

    void updateCompany(Long id, Long companyId);
}
