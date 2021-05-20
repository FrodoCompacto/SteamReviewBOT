package io.steamreviewbot.resources;

import io.steamreviewbot.services.PageStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value="/statistics")
public class PageStatisticsResource {

    @Autowired
    private PageStatisticsService service;

    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    public String pulse() throws IOException {

        service.generateStatsPost();

        return "done";
    }

}
