package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {


}
