package io.steamreviewbot.resources;

import io.steamreviewbot.services.PageStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value="/statistics")
public class PageStatisticsResource {

    @Autowired
    private PageStatisticsService service;

    @RequestMapping(value = "/generate/{date}", method = RequestMethod.POST)
    public String pulse(@PathVariable String date) throws IOException {

        try {
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            service.generateStatsPost(data);
        } catch(ParseException ex){
            return "data com formato errado, esperado: dd/MM/yyyy";
        }

        return "done";
    }

}
