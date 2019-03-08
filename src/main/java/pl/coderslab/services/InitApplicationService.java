package pl.coderslab.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.BountyType;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDetails;
import pl.coderslab.repository.BountyTypeRepository;
import pl.coderslab.repository.UserDetailsRepository;
import pl.coderslab.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitApplicationService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final BountyTypeRepository bountyTypeRepository;

    @Autowired
    public InitApplicationService(UserRepository userRepository ,
                                  UserDetailsRepository userDetailsRepository,
                                  BountyTypeRepository bountyTypeRepository) {

        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.bountyTypeRepository = bountyTypeRepository;
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


    public void initBountyTypes(){
        List<String> bountyTypes = new ArrayList<>();
            bountyTypes.add("ubrania, które nadają się do ponownego użycia");
            bountyTypes.add("ubrania do wyrzucenia");
            bountyTypes.add("zabawki");
            bountyTypes.add("książki");
            bountyTypes.add("inne");

        for(String str : bountyTypes){
            BountyType bountyType = new BountyType();
            bountyType.setType(str);
            bountyTypeRepository.save(bountyType);
        }
    }

}
