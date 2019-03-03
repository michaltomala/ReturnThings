package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.InstitutionLocation;
import pl.coderslab.repository.InstitutionLocationRepository;


public class InstitutionLocationConverter implements Converter<String, InstitutionLocation> {

    @Autowired
    private InstitutionLocationRepository institutionLocationRepository;

    @Override
    public InstitutionLocation convert(String s) {
        return institutionLocationRepository.findOne(Long.parseLong(s));
    }
}
