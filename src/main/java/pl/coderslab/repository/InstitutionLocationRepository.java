package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.InstitutionLocation;

import java.util.List;

public interface InstitutionLocationRepository extends JpaRepository<InstitutionLocation, Long> {

    List<InstitutionLocation> findAllByInstitutionIsNull();
}
