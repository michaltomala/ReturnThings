package pl.coderslab.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Bounty;

public interface BountyRepository extends JpaRepository<Bounty, Long> {
}
