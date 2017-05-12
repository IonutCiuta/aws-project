package com.aws.privatesector.PrivateSector;

import org.apache.jena.rdf.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * ionutciuta24@gmail.com on 11.05.2017.
 */
public class Engine {
    public static final Logger log = LoggerFactory.getLogger("Engine");

    public static void main(String[] args) {
        log.info("Apllication started.");
        String companyUrl = "http://od.cs.pub.ro/privatesector/company/";
        String locationUrl = "http://od.cs.pub.ro/privatesector/location/";
        String partyUrl = "http://od.cs.pub.ro/privatesector/party/";
        String electionUrl = "http://od.cs.pub.ro/privatesector/election/";
        String resultUrl = "http://od.cs.pub.ro/privatesector/result/";

        Property companyName = ResourceFactory
                .createProperty(companyUrl, "companyName");

        Property locationName = ResourceFactory
                .createProperty(locationUrl, "locationName");

        Property companyRegNo = ResourceFactory
                .createProperty(companyUrl, "companyRegNo");

        Property partyName = ResourceFactory
                .createProperty(partyUrl, "partyName");

        Property electionDate = ResourceFactory
                .createProperty(electionUrl, "electionDate");


        Model defaultModel = ModelFactory.createDefaultModel();

        Resource company1 = defaultModel
                .createResource(companyUrl.concat("123123"))
                .addProperty(companyName, "Company 1 SRL")
                .addProperty(companyRegNo, "regNo1");

        Resource location = defaultModel
                .createResource(locationUrl.concat("Location1"))
                .addProperty(locationName, "Location1");

        Resource party = defaultModel
                .createResource(partyUrl.concat("PPP"))
                .addProperty(partyName, "Political Party P");

        Resource election = defaultModel
                .createResource(electionUrl.concat("election_general_2016"))
                .addProperty(electionDate, "16.10.2016");

        Property resultParty = ResourceFactory
                .createProperty(resultUrl, "party");

        Property resultLocation = ResourceFactory
                .createProperty(resultUrl, "location");

        Property resultValue = ResourceFactory
                .createProperty(resultUrl, "value");

        Resource result = defaultModel
                .createResource(resultUrl.concat("result_election_general_2016_PPP_Location1"))
                .addProperty(resultParty, party)
                .addProperty(resultLocation, location)
                .addProperty(resultValue, "23");

        try {
            defaultModel.write(System.out, "RDF/XML-ABBREV").write(new FileOutputStream(new File("rdf.txt"))).close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
