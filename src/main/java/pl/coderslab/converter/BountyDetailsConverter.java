package pl.coderslab.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.BountyDetails;
import pl.coderslab.repository.BountyDetailsRepository;


public class BountyDetailsConverter implements Converter<String, BountyDetails> {

    @Autowired
    private BountyDetailsRepository bountyDetailsRepository;

    @Override
    public BountyDetails convert(String s) {
        return bountyDetailsRepository.findOne(Long.parseLong(s));
    }

}
