package pl.coderslab.converter;


import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//import org.springframework.stereotype.Component;


//@Component
public class LocalTimeConverter implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String source) {

        try{
            return LocalTime.parse( source, DateTimeFormatter.ISO_LOCAL_TIME );
        }catch(Exception e){
            return null;
        }
    }


}




