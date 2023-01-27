package be.technifutur.java.timairport.repository;

import be.technifutur.java.timairport.model.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot,Long> {
}
