package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.converter.*;


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
        registry.addConverter(getBountyConverter());
        registry.addConverter(getInstitutionLocationConverter());
    }

    @Bean
    public UserConverter getUserConverter() {
        return new UserConverter();
    }

    @Bean
    public UserDetailsConverter getUserDetailsConverter() { return new UserDetailsConverter(); }

    @Bean
    public InstitutionConverter getInstitutionConverter() { return new InstitutionConverter(); }

    @Bean
    public InstitutionLocationConverter getInstitutionLocationConverter() { return new InstitutionLocationConverter(); }

    @Bean
    public BountyConverter getBountyConverter() { return new BountyConverter(); }

}