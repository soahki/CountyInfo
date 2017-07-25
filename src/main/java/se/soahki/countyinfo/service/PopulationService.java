package se.soahki.countyinfo.service;

import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.model.Population;

import java.util.List;

public interface PopulationService {
    List<Population> findAll();
    List<Population> findByCounty(County county);
    List<Population> findByYear(Integer year);
    List<Population> findByMunicipality(Municipality municipality);
    Population findById(Long id);
    void save(Population population);
    void update(Population population);
    void delete(Population population);
}
