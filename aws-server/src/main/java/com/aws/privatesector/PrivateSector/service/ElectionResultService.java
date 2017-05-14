package com.aws.privatesector.PrivateSector.service;

import com.aws.privatesector.PrivateSector.entities.ElectionResult;
import com.aws.privatesector.PrivateSector.repo.ElectionResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * ionutciuta24@gmail.com on 13.05.2017.
 */
@Component
public class ElectionResultService {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ElectionResultRepository resultRepository;

    public List<ElectionResult> findResultsByLocation(String location, String county) {
        log.info("findResults() {} {}", location, county);
        List<ElectionResult> results;

        if(!location.isEmpty() && !county.isEmpty()) {
            results = resultRepository.findByLocationIgnoreCaseAndCountyIgnoreCase(county, location);
        } else if(!location.isEmpty()){
            results = resultRepository.findByLocationIgnoreCase(location);
        } else if(!county.isEmpty()) {
            results = resultRepository.findByCountyIgnoreCase(county);
        } else {
            results = Collections.emptyList();
        }

        //log.info("findResult() {}", results);
        return results;
    }

    public Map<String, Integer> computeTotalResult(List<ElectionResult> input) {
        Map<String, Integer> result = new HashMap<>();
        input.stream().forEach(electionResult -> countVotes(electionResult, result));
        return result;
    }

    private void countVotes(ElectionResult electionResult, Map<String, Integer> accumulator) {
        Field[] fields = electionResult.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            if(isPartyField(field)) {
                String partyName = field.getName();
                if(accumulator.containsKey(partyName)) {
                    accumulator.put(partyName, accumulator.get(partyName) + getVotes(field, electionResult));
                } else {
                    accumulator.put(partyName, getVotes(field, electionResult));
                }
            }
        });
    }

    private boolean isPartyField(Field field) {
        return field.getName().charAt(0) >= 'A' && field.getName().charAt(0) <= 'Z';
    }

    private int getVotes(Field partyField, ElectionResult source) {
        try {
            partyField.setAccessible(true);
            return partyField.getInt(source);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
