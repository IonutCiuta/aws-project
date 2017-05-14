package com.aws.privatesector.PrivateSector.rdf;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResourceFactory;

/**
 * ionutciuta24@gmail.com on 13.05.2017.
 */
public class Properties {
    private static final String PROPERTY_IN_COUNTY = "inCounty";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_CUI = "cui";
    private static final String PROPERTY_LOCATED_IN = "locatedIn";
    private static final String PROPERTY_HAS_STATUS = "hasStatus";
    private static final String PROPERTY_SHORT_NAME = "shortName";
    private static final String PROPERTY_YEAR = "year";
    private static final String PROPERTY_LOCATION = "location";
    private static final String PROPERTY_FOR_PARTY = "forParty";
    private static final String PROPERTY_TOTAL_VOTES = "totalVotes";
    private static final String PROPERTY_OF_ELECTION = "ofElection";

    public static final Property name =
            ResourceFactory.createProperty(URLs.OD_URL + PROPERTY_NAME, PROPERTY_NAME);

    public static final Property inCounty =
            ResourceFactory.createProperty(URLs.LOCATION_URL + PROPERTY_IN_COUNTY, PROPERTY_IN_COUNTY);

    public static final Property cui =
            ResourceFactory.createProperty(URLs.COMPANY_URL + PROPERTY_CUI, PROPERTY_CUI);

    public static final Property locatedIn =
            ResourceFactory.createProperty(URLs.COMPANY_URL + PROPERTY_LOCATED_IN, PROPERTY_LOCATED_IN);

    public static final Property hasStatus =
            ResourceFactory.createProperty(URLs.COMPANY_URL + PROPERTY_HAS_STATUS, PROPERTY_HAS_STATUS);

    public static final Property shortName =
            ResourceFactory.createProperty(URLs.PARTY_URL + PROPERTY_SHORT_NAME, PROPERTY_SHORT_NAME);

    public static final Property year =
            ResourceFactory.createProperty(URLs.ELECTION_URL + PROPERTY_YEAR, PROPERTY_YEAR);

    public static final Property location =
            ResourceFactory.createProperty(URLs.OD_URL + PROPERTY_LOCATION, PROPERTY_LOCATION);

    public static final Property forParty =
            ResourceFactory.createProperty(URLs.ELECTION_RESULT_URL + PROPERTY_FOR_PARTY, PROPERTY_FOR_PARTY);

    public static final Property totalVotes =
            ResourceFactory.createProperty(URLs.ELECTION_RESULT_URL + PROPERTY_TOTAL_VOTES, PROPERTY_TOTAL_VOTES);

    public static final Property ofElection =
            ResourceFactory.createProperty(URLs.ELECTION_RESULT_URL + PROPERTY_OF_ELECTION, PROPERTY_OF_ELECTION);
}
