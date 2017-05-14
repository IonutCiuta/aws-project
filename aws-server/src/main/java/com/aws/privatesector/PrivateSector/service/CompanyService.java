package com.aws.privatesector.PrivateSector.service;

import com.aws.privatesector.PrivateSector.entities.Company;
import com.aws.privatesector.PrivateSector.repo.Company2013Repository;
import com.aws.privatesector.PrivateSector.repo.Company2014Repository;
import com.aws.privatesector.PrivateSector.repo.Company2015Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * ionutciuta24@gmail.com on 14.05.2017.
 */
@Component
public class CompanyService {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    public Company2013Repository company2013Repository;

    @Autowired
    public Company2014Repository company2014Repository;

    @Autowired
    public Company2015Repository company2015Repository;

    public List<Company> findByLocationAndYear(String location, String county, Integer year) {
        log.info("findByLocationAndYear() {} {} {}", location, county, year);
        List<Company> companies;

        switch (year) {
            case 2013:
                companies = handle2013(location, county);
                break;

            case 2014:
                companies = handle2014(location, county);
                break;

            case 2015:
                companies = handle2015(location, county);
                break;

            default:
                companies = Collections.emptyList();
        }

        //log.info("findByLocationAndYear() {}", companies);
        return companies;
    }

    private List<Company> handle2014(String location, String county) {
        List<Company> companies;

        if(!location.isEmpty() && !county.isEmpty()) {
            companies = company2014Repository.findByLocationIgnoreCaseAndCountyIgnoreCase(location, county);
        } else if(!location.isEmpty()){
            companies = company2014Repository.findByLocationIgnoreCase(location);
        } else if(!county.isEmpty()) {
            companies = company2014Repository.findByCountyIgnoreCase(county);
        } else {
            companies = Collections.emptyList();
        }

        return companies;
    }

    private List<Company> handle2013(String location, String county) {
        List<Company> companies;

        if(!location.isEmpty() && !county.isEmpty()) {
            companies = company2013Repository.findByLocationIgnoreCaseAndCountyIgnoreCase(location, county);
        } else if(!location.isEmpty()){
            companies = company2013Repository.findByLocationIgnoreCase(location);
        } else if(!county.isEmpty()) {
            companies = company2013Repository.findByCountyIgnoreCase(county);
        } else {
            companies = Collections.emptyList();
        }

        return companies;
    }

    private List<Company> handle2015(String location, String county) {
        List<Company> companies;

        if(!location.isEmpty() && !county.isEmpty()) {
            companies = company2015Repository.findByLocationIgnoreCaseAndCountyIgnoreCase(location, county);
        } else if(!location.isEmpty()){
            companies = company2015Repository.findByLocationIgnoreCase(location);
        } else if(!county.isEmpty()) {
            companies = company2015Repository.findByCountyIgnoreCase(county);
        } else {
            companies = Collections.emptyList();
        }

        return companies;
    }
}
