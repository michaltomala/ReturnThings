package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.converter.InstitutionConverter;
import pl.coderslab.converter.UserConverter;
import pl.coderslab.converter.UserDetailsConverter;


@Configuration
@ComponentScan("pl.coderslab")
@EnableWebMvc
@EnableTransactionManagement
public class FormatterConfig implements WebMvcConfigurer {


    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(getUserConverter());
        registry.addConverter(getUserDetailsConverter());
        registry.addConverter(getInstitutionConverter());
    }

    @Bean
    public UserConverter getUserConverter() {
        return new UserConverter();
    }

    @Bean
    public UserDetailsConverter getUserDetailsConverter() { return new UserDetailsConverter(); }

    @Bean
    public InstitutionConverter getInstitutionConverter() { return new InstitutionConverter(); }

}