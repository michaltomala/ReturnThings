package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.BountyDetails;


public interface BountyDetailsRepository extends JpaRepository<BountyDetails, Long> {
}
