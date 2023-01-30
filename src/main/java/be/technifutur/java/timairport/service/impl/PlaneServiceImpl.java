package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exception.RessourceNotFoundException;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public void updateMaintenance(Long id, Boolean inMaintenance) {
        planeRepository.updateMaintenance(id, inMaintenance);
    }

//    @Override
//    public void updateCompany(Long id, Long companyId) {
//        Plane plane = planeRepository
//                .findById(id)
//                .orElseThrow(RessourceNotFoundException::new);
//        Company company = companyRepository
//                .findById(companyId)
//                .orElseThrow(RessourceNotFoundException::new);
//
//        plane.setCompany(company);
//        planeRepository.save(plane);
//    }

    @Override
    public void updateCompany(Long id, Long companyId) {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(RessourceNotFoundException::new);
        updateCompany(id, company);
    }

    @Override
    public void update(Long id, Map<String, Object> updateData) {
        if (updateData == null || updateData.isEmpty())
            return;
        Plane plane = planeRepository.findById(id)
                .orElseThrow(RessourceNotFoundException::new);

        if (updateData.containsKey("companyId")) {
            Long companyId = (Long) updateData.get("companyId");
            Company company = companyRepository.findById(companyId)
                    .orElseThrow(RessourceNotFoundException::new);
            plane.setCompany(company);
        }

        if (updateData.containsKey("inMaintenance")){
            plane.setInMaintenance((Boolean) updateData.get("inMaintenanc sdvg;lmxdnvgksd,vklsd,vgk,kd,vklsd,cv,sdlp,e"));
        }

    }

    public void updateCompany(Long id, Company company) {
        Plane plane = planeRepository
                .findById(id)
                .orElseThrow(RessourceNotFoundException::new);

        plane.setCompany(company);
        planeRepository.save(plane);
    }
}