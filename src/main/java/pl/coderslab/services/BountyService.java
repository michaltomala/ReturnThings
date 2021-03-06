package pl.coderslab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.dto.BountyDetails;
import pl.coderslab.entity.User;
import pl.coderslab.repository.BountyDetailsRepository;

import java.util.List;


@Service
public class BountyService {

    private final BountyDetailsRepository bountyDetailsRepository;

    @Autowired
    public BountyService(BountyDetailsRepository bountyDetailsRepository) {
        this.bountyDetailsRepository = bountyDetailsRepository;
    }


    public BountyDetails findBountyDetail(Long id){
        return bountyDetailsRepository.findOne(id);
    }


    public List<BountyDetails> returnListOfBounties(){
        return bountyDetailsRepository.findAllByArchivedOrderByReceptionDate(false);
    }


    public List<BountyDetails> returnListOfArchivedBounties(){
        return bountyDetailsRepository.findAllByArchivedOrderByReceptionDateDesc(true);
    }


    public List<BountyDetails> returnUserListOfBounties(User user,boolean received){
        return bountyDetailsRepository.findAllByUserAndReceivedAndArchivedOrderByReceptionDate(user,received,false);
    }


    public void changeAttributeReceived(Long id){

        BountyDetails bountyDetails = findBountyDetail(id);
        if(bountyDetails.isReceived()){
            bountyDetails.setReceived(false);
            bountyDetailsRepository.save(bountyDetails);
        }else{
            bountyDetails.setReceived(true);
            bountyDetailsRepository.save(bountyDetails);
        }
    }

    public void changeAttributeArchived(Long id){

        BountyDetails bountyDetails = findBountyDetail(id);
        bountyDetails.setArchived(true);
        bountyDetailsRepository.save(bountyDetails);
    }

    public boolean isEnableToArchive(Long id){

        BountyDetails bountyDetails = findBountyDetail(id);
        return bountyDetails.isReceived();
    }

}
