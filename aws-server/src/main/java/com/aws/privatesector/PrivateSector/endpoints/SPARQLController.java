package com.aws.privatesector.PrivateSector.endpoints;

import com.aws.privatesector.PrivateSector.service.SPARQLQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ionutciuta24@gmail.com on 14.05.2017.
 */
@Controller
public class SPARQLController {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private SPARQLQueryService sparqlQueryService;

    @GetMapping("aws/sparql/test")
    public @ResponseBody Response test(@RequestParam("location") String location,
                                     @RequestParam("county") String county) {
        //sparqlQueryService.query();
        log.info("SPARQL Query with: {}, {}", location, county);
        return new Response("Success");
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
