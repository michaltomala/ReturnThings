package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.BountyType;
import pl.coderslab.repository.BountyTypeRepository;

public class BountyTypeConverter implements Converter<String, BountyType> {

    @Autowired
    private BountyTypeRepository bountyTypeRepository;

    @Override
    public BountyType convert(String s) {
        return bountyTypeRepository.findOne(Long.parseLong(s));
    }
}
