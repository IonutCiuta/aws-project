package com.aws.privatesector.PrivateSector.endpoints;

import com.aws.privatesector.PrivateSector.service.SPARQLQueryService;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ionutciuta24@gmail.com on 14.05.2017.
 */
@Controller
public class SPARQLController {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private SPARQLQueryService sparqlQueryService;

    @GetMapping("aws/sparql/test")
    public @ResponseBody
    List<JsonNode> test(@RequestParam("location") String location,
                        @RequestParam("county") String county) {

        log.info("SPARQL Query with: {}, {}", location, county);
        return sparqlQueryService.query();
    }

    private class Response {
        String message;

        public Response(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
