package com.aws.privatesector.PrivateSector.endpoints;

import com.aws.privatesector.PrivateSector.entities.ElectionResult;
import com.aws.privatesector.PrivateSector.repo.ElectionResultRepository;
import com.aws.privatesector.PrivateSector.service.ElectionResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

/**
 * ionutciuta24@gmail.com on 13.05.2017.
 */
@Controller
public class ElectionResultController {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @GetMapping(value = "/aws/result")
    @ResponseStatus(HttpStatus.OK)
    public void getElectionResultByLocation(@RequestParam(value = "location") String location,
                                            @RequestParam(value = "county")String county) {
        log.info("getElectionResultByLocation(): {} {}", location, county);
        List<ElectionResult> results;

    }
}
