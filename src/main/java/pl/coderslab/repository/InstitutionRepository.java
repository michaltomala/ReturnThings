package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    Institution findFirstByName (String name);

}
