package com.aws.privatesector.PrivateSector.endpoints;

import com.aws.privatesector.PrivateSector.entities.Company;
import com.aws.privatesector.PrivateSector.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * ionutciuta24@gmail.com on 13.05.2017.
 */
@Controller
public class CompanyController {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/aws/company")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String getCompaniesByLocation(@RequestParam("location") String location,
                                  @RequestParam("county") String county,
                                  @RequestParam("year") Integer year) {
        log.info("getCompaniesByLocation {} {}", location, county);
        List<Company> result = companyService.findByLocationAndYear(location, county, year);

        log.info("getCompaniesByLocation\n: {}", result);
        return String.format("Found %s companies in %s %s %s.", result.size(), location, county, year);
    }
}
