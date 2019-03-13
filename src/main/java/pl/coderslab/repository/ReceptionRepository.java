package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Reception;

public interface ReceptionRepository extends JpaRepository<Reception, Long> {
}
