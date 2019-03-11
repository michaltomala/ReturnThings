package pl.coderslab.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.BountyType;
import pl.coderslab.entity.InstitutionListOfWhomHelp;
import pl.coderslab.entity.User;
import pl.coderslab.repository.BountyTypeRepository;
import pl.coderslab.repository.InstitutionListOfWhomHelpRepository;
import pl.coderslab.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitApplicationService {

    private final UserRepository userRepository;
    private final BountyTypeRepository bountyTypeRepository;
    private final InstitutionListOfWhomHelpRepository institutionListOfWhomHelpRepository;

    @Autowired
    public InitApplicationService(UserRepository userRepository ,
                                  BountyTypeRepository bountyTypeRepository,
                                  InstitutionListOfWhomHelpRepository institutionListOfWhomHelpRepository) {

        this.userRepository = userRepository;
        this.bountyTypeRepository = bountyTypeRepository;
        this.institutionListOfWhomHelpRepository = institutionListOfWhomHelpRepository;
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
            user.setName("Michał");
            user.setSurname("Staropolski");

            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

            userRepository.save(user);
        }
    }


    public void initBountyTypes() {
        BountyType type = bountyTypeRepository.findOne((long)1);
        if (type == null) {

            List<String> bountyTypes = new ArrayList<>();

            bountyTypes.add("ubrania, które nadają się do ponownego użycia");
            bountyTypes.add("ubrania do wyrzucenia");
            bountyTypes.add("zabawki");
            bountyTypes.add("książki");
            bountyTypes.add("inne");

            for (String str : bountyTypes) {
                BountyType bountyType = new BountyType();
                bountyType.setType(str);
                bountyTypeRepository.save(bountyType);
            }
       }
    }

    public void initListOfWhomHelp(){
        InstitutionListOfWhomHelp ListOfWhomHelp = institutionListOfWhomHelpRepository.findOne((long)1);
        if(ListOfWhomHelp == null) {
            List<String> whomHelpList = new ArrayList<>();


            whomHelpList.add("dzieciom");
            whomHelpList.add("samotnym matkom");
            whomHelpList.add("bezdomnym");
            whomHelpList.add("niepełnosprawnym");
            whomHelpList.add("osobom starszym");

            for (String str : whomHelpList) {
                InstitutionListOfWhomHelp institutionListOfWhomHelp = new InstitutionListOfWhomHelp();
                institutionListOfWhomHelp.setWhomHelp(str);
                institutionListOfWhomHelpRepository.save(institutionListOfWhomHelp);
            }
        }
    }


}
