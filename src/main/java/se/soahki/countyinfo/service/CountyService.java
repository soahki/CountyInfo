package se.soahki.countyinfo.service;

import se.soahki.countyinfo.model.County;

import java.util.List;

public interface CountyService {
    List<County> findAll();
    County findById(Long id);
    void save(County county);
    void update(County county);
    void delete(County county);
}
