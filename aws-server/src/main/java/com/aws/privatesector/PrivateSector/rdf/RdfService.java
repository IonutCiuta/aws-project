package com.aws.privatesector.PrivateSector.rdf;

import com.aws.privatesector.PrivateSector.entities.ElectionResult;
import com.aws.privatesector.PrivateSector.repo.ElectionResultRepository;
import com.aws.privatesector.PrivateSector.service.ElectionResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * ionutciuta24@gmail.com on 13.05.2017.
 */
@Component
public class RdfService {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ElectionResultService electionResultService;

    public void buildRdf(String location, String county) {
        Map<String, Integer> resultsByParty =
                electionResultService.computeTotalResult(electionResultService.findResultsByLocation(location, county));



    }


}
