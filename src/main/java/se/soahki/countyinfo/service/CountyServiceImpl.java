package se.soahki.countyinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.soahki.countyinfo.dao.CountyDao;
import se.soahki.countyinfo.dao.CountyDaoImpl;
import se.soahki.countyinfo.model.County;

import java.util.List;

@Service
public class CountyServiceImpl implements CountyService {

    @Autowired
    private CountyDao countyDao;

    @Override
    public List<County> findAll() {
        return countyDao.findAll();
    }

    @Override
    public County findById(Long id) {
        return countyDao.findById(id);
    }

    @Override
    public void save(County county) {
        countyDao.save(county);
    }

    @Override
    public void update(County county) {
        countyDao.update(county);
    }

    @Override
    public void delete(County county) {
        countyDao.delete(county);
    }
}
