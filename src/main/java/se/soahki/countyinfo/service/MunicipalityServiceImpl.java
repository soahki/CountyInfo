package se.soahki.countyinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.soahki.countyinfo.dao.MunicipalityDao;
import se.soahki.countyinfo.model.Municipality;

import java.util.List;

@Service
public class MunicipalityServiceImpl implements MunicipalityService {

    @Autowired
    private MunicipalityDao municipalityDao;

    @Override
    public List<Municipality> findAll() {
        return municipalityDao.findAll();
    }

    @Override
    public Municipality findById(Long id) {
        return municipalityDao.findById(id);
    }

    @Override
    public void save(Municipality municipality) {
        municipalityDao.save(municipality);
    }

    @Override
    public void update(Municipality municipality) {
        municipalityDao.update(municipality);
    }

    @Override
    public void delete(Municipality municipality) {
        municipalityDao.delete(municipality);
    }
}
