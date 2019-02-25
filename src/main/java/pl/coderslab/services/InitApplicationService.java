package pl.coderslab.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

@Service
public class InitApplicationService {

    private final UserRepository userRepository;

    @Autowired
    public InitApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepository.save(user);
        }
    }

}
