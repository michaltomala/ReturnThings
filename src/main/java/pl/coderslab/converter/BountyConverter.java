package pl.coderslab.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Bounty;
import pl.coderslab.repository.BountyRepository;


public class BountyConverter implements Converter<String, Bounty> {

    @Autowired
    private BountyRepository bountyRepository;

    @Override
    public Bounty convert(String s) {
        return bountyRepository.findOne(Long.parseLong(s));
    }
}
