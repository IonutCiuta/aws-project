package com.aws.privatesector.PrivateSector.repo;

import com.aws.privatesector.PrivateSector.entities.Company;
import com.aws.privatesector.PrivateSector.entities.Company2016;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * ionutciuta24@gmail.com on 14.05.2017.
 */
public interface Company2016Repository extends MongoRepository<Company2016, String> {
    List<Company> findByLocationIgnoreCase(String location);

    List<Company> findByLocationIgnoreCaseAndCountyIgnoreCase(String location, String county);

    List<Company> findByCountyIgnoreCase(String county);
}
