package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Institution;
import pl.coderslab.repository.InstitutionsRepository;

public class InstitutionConverter implements Converter<String, Institution> {

    @Autowired
    private InstitutionsRepository institutionsRepository;

    @Override
    public Institution convert(String s) {
        return institutionsRepository.findOne(Long.parseLong(s));
    }
}
