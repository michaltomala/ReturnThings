package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Institution;
import pl.coderslab.repository.InstitutionRepository;

public class InstitutionConverter implements Converter<String, Institution> {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public Institution convert(String s) {
        return institutionRepository.findOne(Long.parseLong(s));
    }
}
