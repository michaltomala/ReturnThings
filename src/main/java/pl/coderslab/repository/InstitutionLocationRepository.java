package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.InstitutionLocation;

public interface InstitutionLocationRepository extends JpaRepository<InstitutionLocation, Long> {
}
