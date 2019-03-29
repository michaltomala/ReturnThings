package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.dto.BountyDetails;

import java.util.List;


public interface BountyDetailsRepository extends JpaRepository<BountyDetails, Long> {

    List<BountyDetails> findAllByArchived(boolean archived);

}
