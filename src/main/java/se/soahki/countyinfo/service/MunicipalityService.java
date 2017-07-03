package se.soahki.countyinfo.service;

import se.soahki.countyinfo.model.Municipality;

import java.util.List;

public interface MunicipalityService {
    List<Municipality> findAll();
    Municipality findById(Long id);
    void save(Municipality municipality);
    void update(Municipality municipality);
    void delete(Municipality municipality);
}
