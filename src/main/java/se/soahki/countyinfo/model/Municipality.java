package se.soahki.countyinfo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Entity class of municipalities.
 */
@Entity
public class Municipality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;

    @ManyToOne
    private County county;

    @OneToMany(mappedBy = "municipality", fetch = FetchType.EAGER)
    private List<Population> inhabitants;

    public Municipality() {
    }

    public Municipality(String name, String municipalityCode, County county) {
        this.name = name;
        this.code = municipalityCode;
        this.county = county;
    }

    public List<Population> getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(List<Population> inhabitants) {
        this.inhabitants = inhabitants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }
}
