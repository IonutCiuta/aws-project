package com.aws.privatesector.PrivateSector.rdf;

import com.aws.privatesector.PrivateSector.entities.Company;
import com.aws.privatesector.PrivateSector.entities.ElectionResult;
import com.aws.privatesector.PrivateSector.repo.ElectionResultRepository;
import com.aws.privatesector.PrivateSector.service.CompanyService;
import com.aws.privatesector.PrivateSector.service.ElectionResultService;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

/**
 * ionutciuta24@gmail.com on 13.05.2017.
 */
@Component
public class RdfService {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ElectionResultService electionResultService;

    @Autowired
    private CompanyService companyService;

    public void buildRdf(String location, String county) {
        Map<String, Integer> resultsByParty =
                electionResultService.computeTotalResult(electionResultService.findResultsByLocation(location, county));

        Map<Integer, List<Company>> allCompanies =
                companyService.findAllByLocation(location, county);

        Model model = ModelFactory.createDefaultModel();

        /* County */
        Resource countyRes = buildCounty(county, model);

        /* Location */
        Resource locationRes = buildLocation(location, county, countyRes, model);

        /* Parties */
        Map<String, Resource> partiesRes = buildParties(resultsByParty.keySet(), model);

        /* Election */
        Resource electionRes = buildElection("General Election 2016", 2016, model);

        /* Results */
        buildElectionResults(resultsByParty, partiesRes, electionRes, locationRes, model);

        /* Companies */
        buildCompanyData(allCompanies, locationRes, model);

        try {
            model.write(System.out, "RDF/XML-ABBREV")
                 .write(new FileOutputStream(new File("triplets.rdf")))
                 .close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Resource buildCounty(String county, Model rdf) {
        String countyResourceId = county.toUpperCase();

        return rdf
                .createResource(URLs.COUNTY_URL.concat(countyResourceId))
                .addProperty(Properties.name, county);
    }

    private Resource buildLocation(String location, String county, Resource countyRes, Model rdf) {
        String locationResourceId = county.toUpperCase().concat("_").concat(location);

        return rdf
                .createResource(URLs.LOCATION_URL.concat(locationResourceId))
                .addProperty(Properties.name, location)
                .addProperty(Properties.inCounty, countyRes);
    }

    private Map<String, Resource> buildParties(Set<String> parties, Model rdf) {
        Map<String, Resource> result = new HashMap<>();

        for(String party : parties) {
            result.put(
                    party,
                    rdf.createResource(URLs.PARTY_URL.concat(party))
                       .addProperty(Properties.name, party)

            );
        }

        return result;
    }

    private Resource buildElection(String name, int year, Model rdf) {
        String uri = URLs.ELECTION_URL.concat(name.replace(" ", "_"));

        return rdf
                .createResource(uri)
                .addProperty(Properties.name, name)
                .addProperty(Properties.year, String.valueOf(year));
    }

    private Resource buildCompany(Company company, Resource location, int year, Model rdf) {
        return rdf
                .createResource(URLs.COMPANY_URL.concat(company.getCui().concat("_").concat(String.valueOf(year))))
                .addProperty(Properties.name, company.getName())
                .addProperty(Properties.location, location)
                .addProperty(Properties.cui, company.getCui())
                .addProperty(Properties.year, String.valueOf(year));
    }

    private void buildElectionResults(Map<String, Integer> results, Map<String, Resource> parties,
                                      Resource election, Resource location, Model rdf) {
        for(String party : results.keySet()) {
            int voteCount = results.get(party);
            Resource partyRes = parties.get(party);

            String electionName = election.getProperty(Properties.name).getString().replace(" ", "_");
            String uri = URLs.ELECTION_RESULT_URL.concat(electionName).concat("_").concat(party);

            rdf
              .createResource(uri)
              .addProperty(Properties.locatedIn, location)
              .addProperty(Properties.forParty, partyRes)
              .addProperty(Properties.totalVotes, String.valueOf(voteCount))
              .addProperty(Properties.ofElection, election);
        }
    }

    private void buildCompanyData(Map<Integer, List<Company>> allCompanies, Resource location, Model rdf) {
        for(Integer i : allCompanies.keySet()) {
            allCompanies.get(i).stream().forEach(c -> buildCompany(c, location, i, rdf));
        }
    }
}
