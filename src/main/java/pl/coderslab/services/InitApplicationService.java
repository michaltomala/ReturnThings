package pl.coderslab.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDetails;
import pl.coderslab.repository.UserDetailsRepository;
import pl.coderslab.repository.UserRepository;

@Service
public class InitApplicationService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public InitApplicationService(UserRepository userRepository , UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    /**
     * In my app there is always minimum one admin - created during server started
     */
    public void initAdmin(){
        System.out.println("INIT APP");
        User userToCheck = userRepository.findOne((long) 1);
        if( userToCheck == null) {
            User user = new User();
            user.setEmail("admin@admin.pl");
            user.setPassword("123456");
            user.setAdmin(true);

            UserDetails userDetails = new UserDetails();
            userDetails.setName("Michał");
            userDetails.setSurname("Staropolski");
            user.setUserDetails(userDetails);
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

            userDetailsRepository.save(userDetails);
            userRepository.save(user);
        }
    }

}
