package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.dto.BountyDetails;
import pl.coderslab.entity.User;

import java.util.List;


public interface BountyDetailsRepository extends JpaRepository<BountyDetails, Long> {


    List<BountyDetails> findAllByArchivedOrderByReceptionDate(boolean archived);
    List<BountyDetails> findAllByArchivedOrderByReceptionDateDesc(boolean archived);

    List<BountyDetails> findAllByUserAndReceivedAndArchivedOrderByReceptionDate(User user,boolean received, boolean archived);

}
