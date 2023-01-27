package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exception.RessourceNotFoundException;
import be.technifutur.java.timairport.mapper.PlaneMapper;
import be.technifutur.java.timairport.model.dto.PlaneDTO;
import be.technifutur.java.timairport.model.entity.Company;
import be.technifutur.java.timairport.model.entity.Plane;
import be.technifutur.java.timairport.model.entity.TypePlane;
import be.technifutur.java.timairport.model.form.PlaneInsertForm;
import be.technifutur.java.timairport.repository.CompanyRepository;
import be.technifutur.java.timairport.repository.PlaneRepository;
import be.technifutur.java.timairport.repository.TypePlaneRepository;
import be.technifutur.java.timairport.service.PlaneService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final TypePlaneRepository typePlaneRepository;
    private final CompanyRepository companyRepository;

    public PlaneServiceImpl(
            PlaneRepository planeRepository,
            TypePlaneRepository typePlaneRepository,
            CompanyRepository companyRepository) {
        this.planeRepository = planeRepository;
        this.typePlaneRepository = typePlaneRepository;
        this.companyRepository = companyRepository;
    }

    //--------------------------------------------------//
    @Override
    public void create(PlaneInsertForm form) {
        Plane plane = form.toEntity();

        TypePlane type = typePlaneRepository.findById(form.getTypeId())
                .orElseThrow(RessourceNotFoundException::new); //NORMAN DU FUTUR! C'EST LE MEME QUE AU DESSOUS! PS : TU ES CON NORMAN DU
        plane.setType(type);

        Company company = companyRepository.findById(form.getCompanyId())
                .orElseThrow(() -> new RessourceNotFoundException());
        plane.setCompany(company);

        planeRepository.save(plane);
    }

    @Override
    public PlaneDTO getOne(long id) {
        return planeRepository.findById(id)
                .map(PlaneDTO::from)
                .orElseThrow(RessourceNotFoundException::new);
    }

    @Override
    public List<PlaneDTO> getAll() {
        return planeRepository.findAll().stream().map(PlaneDTO::from).toList();
    }
}