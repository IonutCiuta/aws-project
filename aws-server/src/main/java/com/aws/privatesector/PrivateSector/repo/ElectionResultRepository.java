package com.aws.privatesector.PrivateSector.repo;

import com.aws.privatesector.PrivateSector.entities.ElectionResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * ionutciuta24@gmail.com on 13.05.2017.
 */
public interface ElectionResultRepository extends MongoRepository<ElectionResult, String> {
    List<ElectionResult> findByLocationIgnoreCase(String location);

    List<ElectionResult> findByCountyIgnoreCase(String county);

    List<ElectionResult> findByLocationIgnoreCaseAndCountyIgnoreCase(String county, String location);
}
