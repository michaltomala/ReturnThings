package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.BountyType;

public interface BountyTypeRepository extends JpaRepository<BountyType, Long> {
}
