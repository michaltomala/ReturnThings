package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Institution;

public interface InstitutionsRepository extends JpaRepository<Institution, Long> {
}
