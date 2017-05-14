package com.aws.privatesector.PrivateSector.endpoints;

import com.aws.privatesector.PrivateSector.rdf.RdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ionutciuta24@gmail.com on 14.05.2017.
 */
@Controller
public class RdfController {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private RdfService rdfService;

    @GetMapping("/aws/rdf")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String buildRdf(@RequestParam(value = "location") String location,
                                         @RequestParam(value = "county") String county) {
        log.info("buildRdf {} {}", location, county);
        rdfService.buildRdf(location, county);
        return "Wrote RDF with success.";
    }
}
