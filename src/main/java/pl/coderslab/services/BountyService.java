package pl.coderslab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.dto.BountyDetails;
import pl.coderslab.repository.BountyDetailsRepository;
import pl.coderslab.repository.BountyRepository;

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
        return bountyDetailsRepository.findAll();
    }

}
