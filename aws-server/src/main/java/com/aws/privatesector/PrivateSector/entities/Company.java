package com.aws.privatesector.PrivateSector.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * ionutciuta24@gmail.com on 13.05.2017.
 */
@Document(collection = "privateSector2013")
public abstract class Company {
    @Id
    protected String id;

    @Field("DENUMIRE")
    protected String name;

    @Field("CUI")
    protected String cui;

    @Field("LOCALITATE")
    protected String location;

    @Field("JUDET")
    protected String county;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "Company" +
                "  name: "        + name      + '\n' +
                "  cui: "         + cui       + '\n' +
                "  location: "    + location  + '\n' +
                "  county: "      + county    + '\n' +
                "========================\n";
    }
}
