package se.soahki.countyinfo.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.model.Population;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class MunicipalityDaoImpl implements MunicipalityDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Municipality> findAll() {
        Session session = sessionFactory.openSession();

        // Create query critera
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Municipality> criteria = builder.createQuery(Municipality.class);
        criteria.from(Municipality.class);

        // Execute criteria
        List<Municipality> municipalities = session.createQuery(criteria).getResultList();

        session.close();

        return municipalities;
    }

    @Override
    public Municipality findById(Long id) {
        Session session = sessionFactory.openSession();

        Municipality municipality = session.get(Municipality.class, id);
        session.close();
        Hibernate.initialize(municipality.getCounty());
        return municipality;
    }

    @Override
    public void save(Municipality municipality) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.saveOrUpdate(municipality);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void update(Municipality municipality) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.saveOrUpdate(municipality);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void delete(Municipality municipality) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.delete(municipality);

        session.getTransaction().commit();

        session.close();
    }

}
