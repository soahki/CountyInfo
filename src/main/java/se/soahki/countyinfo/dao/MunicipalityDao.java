package se.soahki.countyinfo.dao;

import se.soahki.countyinfo.model.Municipality;

import java.util.List;

public interface MunicipalityDao {
    List<Municipality> findAll();
    Municipality findById(Long id);
    void save(Municipality municipality);
    void update(Municipality municipality);
    void delete(Municipality municipality);
}
