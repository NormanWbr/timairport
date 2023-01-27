package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exception.RessourceNotFoundException;
import be.technifutur.java.timairport.model.entity.Company;
import be.technifutur.java.timairport.model.entity.Plane;
import be.technifutur.java.timairport.model.entity.TypePlane;
import be.technifutur.java.timairport.model.form.PlaneInsertForm;
import be.technifutur.java.timairport.repository.CompanyRepository;
import be.technifutur.java.timairport.repository.PlaneRepository;
import be.technifutur.java.timairport.repository.TypePlaneRepository;
import be.technifutur.java.timairport.service.PlaneService;
import org.springframework.stereotype.Service;

@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final TypePlaneRepository typePlaneRepository;
    private final CompanyRepository companyRepository;

    public PlaneServiceImpl(
            PlaneRepository planeRepository,
            TypePlaneRepository typePlaneRepository,
            CompanyRepository companyRepository
    ) {
        this.planeRepository = planeRepository;
        this.typePlaneRepository = typePlaneRepository;
        this.companyRepository = companyRepository;
    }
//--------------------------------------------------//
    @Override
    public void create(PlaneInsertForm form) {
        Company company = companyRepository.findById(form.getCompanyId())
                .orElseThrow(() -> new RessourceNotFoundException());
        TypePlane type = typePlaneRepository.findById(form.getTypeId())
                .orElseThrow(RessourceNotFoundException::new); //NORMAN DU FUTUR! C'EST LE MEME QUE AU DESSUS!

        Plane plane = new Plane();
        plane.setCallSign(form.getCallSign());
        plane.setRegistrationDate(form.getRegistrationDate());
        plane.setCompany(company);
        plane.setType(type);

        planeRepository.save(plane);
    }
}
