package pl.coderslab.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.BountyType;
import pl.coderslab.repository.BountyTypeRepository;
import java.util.List;

@Service
public class BountyService {

    private final BountyTypeRepository bountyTypeRepository;
    private final InstitutionService institutionService;
    private final InstitutionLocationService institutionLocationService;
    @Autowired
    public BountyService(BountyTypeRepository bountyTypeRepository,
                         InstitutionService institutionService,
                         InstitutionLocationService institutionLocationService) {
        this.bountyTypeRepository = bountyTypeRepository;
        this.institutionService = institutionService;
        this.institutionLocationService = institutionLocationService;
    }

    public List<BountyType> returnListOfBountyTypes(){ return bountyTypeRepository.findAll(); }



}
