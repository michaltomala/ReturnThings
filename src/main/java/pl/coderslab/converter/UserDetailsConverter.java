package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.UserDetails;
import pl.coderslab.repository.UserDetailsRepository;

public class UserDetailsConverter implements Converter<String, UserDetails> {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails convert(String s) {
        return userDetailsRepository.findOne(Long.parseLong(s));
    }
}
