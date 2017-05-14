package com.aws.privatesector.PrivateSector.repo;

import com.aws.privatesector.PrivateSector.entities.Company;
import com.aws.privatesector.PrivateSector.entities.Company2013;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ionutciuta24@gmail.com on 13.05.2017.
 */
public interface Company2013Repository extends MongoRepository<Company2013, String> {
    List<Company> findByLocationIgnoreCase(String location);

    List<Company> findByLocationIgnoreCaseAndCountyIgnoreCase(String location, String county);

    List<Company> findByCountyIgnoreCase(String county);
}
