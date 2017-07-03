package se.soahki.countyinfo.dao;

import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Municipality;

import java.util.List;

public interface CountyDao {
    List<County> findAll();
    County findById(Long id);
    void save(County county);
    void update(County county);
    void delete(County county);
}
