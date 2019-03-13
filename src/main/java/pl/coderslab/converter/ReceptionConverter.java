package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Reception;
import pl.coderslab.repository.ReceptionRepository;

public class ReceptionConverter implements Converter<String, Reception> {

    @Autowired
    private ReceptionRepository receptionRepository;


    @Override
    public Reception convert(String s) {
        return receptionRepository.findOne(Long.parseLong(s));
    }


}
