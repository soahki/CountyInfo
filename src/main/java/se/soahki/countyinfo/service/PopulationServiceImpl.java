package se.soahki.countyinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.soahki.countyinfo.dao.PopulationDao;
import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.model.Population;

import java.util.List;

@Service
public class PopulationServiceImpl implements PopulationService {
    @Autowired
    private PopulationDao populationDao;

    @Override
    public List<Population> findAll() {
        return populationDao.findAll();
    }

    @Override
    public List<Population> findByCounty(County county) {
        return populationDao.findByCounty(county);
    }

    @Override
    public List<Population> findByYear(Integer year) {
        return populationDao.findByYear(year);
    }

    @Override
    public List<Population> findByMunicipality(Municipality municipality) {
        return populationDao.findByMunicipality(municipality);
    }

    @Override
    public Population findById(Long id) {
        return populationDao.findById(id);
    }

    @Override
    public void save(Population population) {
        populationDao.save(population);
    }

    @Override
    public void update(Population population) {
        populationDao.update(population);
    }

    @Override
    public void delete(Population population) {
        populationDao.delete(population);
    }
}
