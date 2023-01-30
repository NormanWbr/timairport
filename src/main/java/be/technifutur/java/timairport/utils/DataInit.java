package be.technifutur.java.timairport.utils;

import be.technifutur.java.timairport.model.entity.*;
import be.technifutur.java.timairport.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class DataInit implements InitializingBean {
    private final CompanyRepository companyRepository;
    private final TypePlaneRepository typePlaneRepository;
    private final AirportRepository airportRepository;

    private final PlaneRepository planeRepository;

    private final PilotRepository pilotRepository;

    public DataInit(CompanyRepository companyRepository, TypePlaneRepository typePlaneRepository,
                    AirportRepository airportRepository, PlaneRepository planeRepository, PilotRepository pilotRepository) {
        this.companyRepository = companyRepository;
        this.typePlaneRepository = typePlaneRepository;
        this.airportRepository = airportRepository;
        this.planeRepository = planeRepository;
        this.pilotRepository = pilotRepository;
    }
//--------------------------------------------------//
    @Override
    public void afterPropertiesSet() throws Exception {
        List<TypePlane> types = new ArrayList<>();

        // Type 1
        TypePlane type = new TypePlane();
        type.setName("big_plane");
        type.setCapacity(300);

        types.add(type);
        typePlaneRepository.save(type);

        // Type 2
        type = new TypePlane();
        type.setName("average_plane");
        type.setCapacity(200);

        types.add(type);
        typePlaneRepository.save(type);

        // Type 3
        type = new TypePlane();
        type.setName("small_plane");
        type.setCapacity(100);

        types.add(type);
        typePlaneRepository.save(type);

        // Company 1
        Company company = new Company();
        company.setName("big money company");
        company.setOriginCountry("USA");

        companyRepository.save(company);

        // Pilot 1
        Pilot pilot = new Pilot();
        pilot.setFirstName("Jack");
        pilot.setLastName("Black");
        pilot.setAddress("casino royale");
        pilot.setEmail("tricher@hotmail.com");
        pilot.setPhone("66677769420");
        pilot.setLicenseId("AAA111");
        pilot.setLicenseAcquisition(LocalDate.of(2000,1,1));
        pilot.setCompany(company);

        pilotRepository.save(pilot);

        // Company 2
        company = new Company();
        company.setName("Deedlamerd");
        company.setOriginCountry("Belgium");

        companyRepository.save(company);

        // Pilot 2
        pilot = new Pilot();
        pilot.setFirstName("Alex");
        pilot.setLastName("Peter");
        pilot.setAddress("3 rue es pissenlits");
        pilot.setEmail("alexpiter@gmail.com");
        pilot.setPhone("0000000000");
        pilot.setLicenseId("B2B2B2");
        pilot.setLicenseAcquisition(LocalDate.of(2010,4,21));
        pilot.setCompany(company);

        pilotRepository.save(pilot);

        // Airport 1
        Airport airport = new Airport();
        airport.setName("aero1");
        airport.setCity("CT1");
        airport.setCountry("musiqueCountry1");
        airport.setAddress("la troidieme a droite");
        airport.setPlaneParking(30);
        airport.setPlaneTypesAllowed(types);

        airportRepository.save(airport);

        types.remove(1);
        // Airport 2
        airport = new Airport();
        airport.setName("super BG inegale");
        airport.setCity("coeur");
        airport.setCountry("toi");
        airport.setAddress("droit dedans");
        airport.setPlaneParking(777);
        airport.setPlaneTypesAllowed(types);

        airportRepository.save(airport);

        //Avion 1
        Plane plane = new Plane();
        plane.setInMaintenance(false);
        plane.setCallSign("N390HA");
        plane.setCompany(company);
        plane.setType(type);
        plane.setRegistrationDate(LocalDate.of(2021,10,01));

        planeRepository.save(plane);

        //Avion 2
        plane.setInMaintenance(false);
        plane.setCallSign("N390HB");
        plane.setCompany(company);
        plane.setType(type);
        plane.setRegistrationDate(LocalDate.of(2019,10,01));

        planeRepository.save(plane);

    }
}
