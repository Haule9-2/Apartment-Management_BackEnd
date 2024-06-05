package com.dmp.fomatters;

import com.dmp.pojo.Services;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ServicesFormatter implements Formatter<Services> {
    @Override
    public Services parse(String id, Locale locale) throws ParseException {
        Services service = new Services();
        service.setId(Integer.parseInt(id));
        return service;
    }

    @Override
    public String print(Services service, Locale locale) {
        return String.valueOf(service.getId());
    }
}
