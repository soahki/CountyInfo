package se.soahki.countyinfo.dao;

import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.model.Population;

import java.util.List;

public interface PopulationDao {
    List<Population> findAll();
    List<Population> findByCounty(County county);
    List<Population> findByMunicipality(Municipality municipality);
    Population findById(Long id);
    void save(Population population);
    void update(Population population);
    void delete(Population population);
}
