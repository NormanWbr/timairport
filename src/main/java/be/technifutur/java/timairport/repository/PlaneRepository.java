package be.technifutur.java.timairport.repository;

import be.technifutur.java.timairport.model.entity.Airport;
import be.technifutur.java.timairport.model.entity.Company;
import be.technifutur.java.timairport.model.entity.Plane;
import be.technifutur.java.timairport.model.entity.TypePlane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

    //UPDATE Plane p SET p.inMaintenance = ?2 WHERE p.id = ?1
    @Modifying
    @Transactional
    @Query("UPDATE Plane p SET p.inMaintenance = ?2 WHERE p.id = ?1")
    void updateMaintenance(Long id, Boolean maintenance);

    @Query("SELECT p FROM Plane p WHERE p.type IN ?1 AND p.type IN ?2 AND p.company = ?3 AND p.inMaintenance = false")
    List<Plane> findForFlight(List<TypePlane> departureTypes, List<TypePlane> destinationTypes, Company company);


    /*
    SELECT p
    FROM Plane p
    WHERE p.type IN departure.planeTypesAllowed
    AND p.type IN destination.planeTypesAllowed
    AND p.companyId = companyId
    AND p.inMaintenance = false
     */
}
