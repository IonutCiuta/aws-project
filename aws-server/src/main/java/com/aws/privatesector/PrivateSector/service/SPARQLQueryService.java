package com.aws.privatesector.PrivateSector.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.jena.atlas.json.JSON;
import org.apache.jena.atlas.json.JsonObject;
import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.sparql.resultset.SPARQLResult;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * ionutciuta24@gmail.com on 14.05.2017.
 */
@Component
public class SPARQLQueryService {

    private RestTemplate template;
    private HttpHeaders httpHeaders;
    private String SPARQL_ENDPOINT = "http://opendata.cs.pub.ro/repo/sparql/select";

    @PostConstruct
    private void init() {
        this.template = new RestTemplate();
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.setContentType(MediaType.APPLICATION_XML);
        this.httpHeaders.setAccept(Arrays.asList(new MediaType("application", "sparql-results+json")));
    }

    public List<JsonNode> query() {
        List<JsonNode> jsonNodes = new ArrayList<>();
        jsonNodes.add(request(queryTopParties, null, null,      null));
        jsonNodes.add(request(queryCompaniesByYear, null, null, "2013"));
        jsonNodes.add(request(queryCompaniesByYear, null, null, "2014"));
        jsonNodes.add(request(queryCompaniesByYear, null, null, "2015"));
        jsonNodes.add(request(queryCompaniesByYear, null, null, "2016"));
        jsonNodes.add(request(queryCompaniesByYear, null, null, "2017"));
        System.out.println(jsonNodes);
        return jsonNodes;
    }


    private JsonNode request(String query, String location, String county, String year) {
        HttpEntity<String> httpEntity = new HttpEntity<>(String.format(query, year, year), httpHeaders);
        ResponseEntity<JsonNode> result = template.exchange(
                SPARQL_ENDPOINT,
                HttpMethod.POST,
                httpEntity,
                JsonNode.class
        );
        return result.getBody();
    }

    String queryTopParties =
                   "PREFIX er:  <http://od.cs.pub.ro/privatesector/election_result/> "
                 + "PREFIX com: <http://od.cs.pub.ro/privatesector/company/> "
                 + "PREFIX ps:  <http://od.cs.pub.ro/privatesector/> "
                 + " SELECT ?result ?party ?votes ?name" 
                 + " WHERE {"
                 + "     ?result com:locatedIn <http://od.cs.pub.ro/privatesector/location/ALBA_Zlatna> . "
                 + "     ?result er:forParty ?party . "
                 + "     ?party ps:name ?name ."
                 + "     ?result er:totalVotes ?votes ."
                 + " } ORDER BY DESC(xsd:integer(?votes)) LIMIT 3";

    String queryCompaniesByYear =
              "PREFIX e:  <http://od.cs.pub.ro/privatesector/election/> "
            + "PREFIX ps:  <http://od.cs.pub.ro/privatesector/> "
            + "PREFIX com: <http://od.cs.pub.ro/privatesector/company/> "
            + "SELECT (COUNT(*) AS ?count%s) "
            + "WHERE {"
            + "      ?company ps:location <http://od.cs.pub.ro/privatesector/location/ALBA_Zlatna> . "
            + "      ?company ps:location ?location . "
            + "      ?company e:year \"%s\" . "
            + "} GROUP BY ?location";
}
