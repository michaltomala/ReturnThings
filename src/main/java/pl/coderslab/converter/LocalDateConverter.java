package pl.coderslab.converter;

import org.springframework.core.convert.converter.Converter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {

        try{
            return LocalDate.parse( source, DateTimeFormatter.ISO_LOCAL_DATE);
        }catch(DateTimeParseException e){
            return null;
        }
    }

}


