package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.dto.BountyDetails;
import pl.coderslab.entity.Bounty;
import pl.coderslab.entity.User;

import java.util.List;


public interface BountyDetailsRepository extends JpaRepository<BountyDetails, Long> {

    List<BountyDetails> findAllByArchived(boolean archived);

    List<BountyDetails> findAllByUserAndReceivedAndArchived(User user,boolean received, boolean archived);

//   todo - find all by date - zarówno do panelu admina jak i usera
}
