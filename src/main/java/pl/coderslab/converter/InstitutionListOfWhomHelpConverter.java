package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.InstitutionListOfWhomHelp;
import pl.coderslab.repository.InstitutionListOfWhomHelpRepository;

public class InstitutionListOfWhomHelpConverter implements Converter<String, InstitutionListOfWhomHelp> {

    @Autowired
    private InstitutionListOfWhomHelpRepository institutionListOfWhomHelpRepository;

    @Override
    public InstitutionListOfWhomHelp convert(String s) {
        return institutionListOfWhomHelpRepository.findOne(Long.parseLong(s));
    }

}

