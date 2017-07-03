package se.soahki.countyinfo.service;

import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.model.Population;

import java.util.List;

public interface MunicipalityService {
    List<Municipality> findAll();
    Municipality findById(Long id);
    void save(Municipality municipality);
    void update(Municipality municipality);
    void delete(Municipality municipality);
}
