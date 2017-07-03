package se.soahki.countyinfo.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.model.Population;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PopulationDaoImpl implements PopulationDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Population> findAll() {
        Session session = sessionFactory.openSession();

        // Create query critera
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Population> criteria = builder.createQuery(Population.class);
        criteria.from(Population.class);

        // Execute criteria
        List<Population> populations = session.createQuery(criteria).getResultList();

        session.close();

        return populations;
    }

    @Override
    public List<Population> findByCounty(County county) {
        List<Population> populations = findAll();
        List<Population> countyPopulations = new ArrayList<>();
        for (Population population : populations) {
            if (population.getMunicipality().getCounty().getCode().equals(county.getCode())) {
                countyPopulations.add(population);
            }
        }
        return countyPopulations;
    }

    @Override
    public List<Population> findByMunicipality(Municipality municipality) {
        List<Population> populations = findAll();
        List<Population> municipalityPopulations = new ArrayList<>();
        for (Population population : populations) {
            if (population.getMunicipality().getCode().equals(municipality.getCode())) {
                municipalityPopulations.add(population);
            }
        }
        return municipalityPopulations;
    }

    @Override
    public Population findById(Long id) {
        Session session = sessionFactory.openSession();

        Population population = session.get(Population.class, id);
        session.close();
        return population;
    }

    @Override
    public void save(Population population) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.saveOrUpdate(population);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void update(Population population) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.saveOrUpdate(population);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void delete(Population population) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.delete(population);

        session.getTransaction().commit();

        session.close();
    }
}
