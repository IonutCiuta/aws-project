package com.aws.privatesector.PrivateSector.service;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.sparql.resultset.SPARQLResult;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;

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
        this.httpHeaders.setAccept(Arrays.asList(new MediaType("application", "sparql-results+xml")));
    }

    public void query() {
        /*ParameterizedSparqlString queryString = new ParameterizedSparqlString();
        queryString.setNsPrefix("ps", "http://od.cs.pub.ro/privatesector/election_result");
        queryString.append("SELECT ?result ?count");
        queryString.append("{");
        queryString.append("?result ps:forParty <http://od.cs.pub.ro/privatesector/party/PSD> .");
        queryString.append("?result ps:totalVotes ?count");
        queryString.append("}");
        Query query = queryString.asQuery();*/

        /*String query = "PREFIX ps: <http://od.cs.pub.ro/privatesector/election_result/> "
                        + "SELECT ?result ?count "
                        + "WHERE {"
                        + "  ?result ps:forParty <http://od.cs.pub.ro/privatesector/party/PSD> . "
                        + "  ?result ps:totalVotes ?count "
                        + "} LIMIT 10";*/

        String query =
                  "PREFIX er:  <http://od.cs.pub.ro/privatesector/election_result/> "
                + "PREFIX com: <http://od.cs.pub.ro/privatesector/company/> "
                + "PREFIX ps:  <http://od.cs.pub.ro/privatesector/> "
                + "SELECT * WHERE { "
                + "{"
                + " SELECT ?result ?party ?votes ?name"
                + " WHERE {"
                + "     ?result com:locatedIn <http://od.cs.pub.ro/privatesector/location/ALBA_Zlatna> . "
                + "     ?result er:forParty ?party . "
                + "     ?party ps:name ?name ."
                + "     ?result er:totalVotes ?votes ."
                + " } ORDER BY DESC(xsd:integer(?votes)) LIMIT 1"
                + "} UNION {"
                + " SELECT (COUNT(*) AS ?count)"
                + " WHERE {"
                + "      ?company ps:location ?location"
                + " } GROUP BY ?location"
                + "}"
                + "}";

        HttpEntity<String> httpEntity = new HttpEntity<>(query, httpHeaders);

        ResponseEntity<String> result = template.exchange(
                SPARQL_ENDPOINT,
                HttpMethod.POST,
                httpEntity,
                String.class
        );
        System.out.println(result.getBody());
    }

}
