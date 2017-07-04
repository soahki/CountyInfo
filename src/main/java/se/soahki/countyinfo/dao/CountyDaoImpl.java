package se.soahki.countyinfo.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.soahki.countyinfo.model.County;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class CountyDaoImpl implements CountyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<County> findAll() {
        Session session = sessionFactory.openSession();

        // Create query critera
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<County> criteria = builder.createQuery(County.class);
        criteria.from(County.class);

        // Execute criteria
        List<County> counties = session.createQuery(criteria).getResultList();

        session.close();

        return counties;
    }

    @Override
    public County findById(Long id) {
        Session session = sessionFactory.openSession();

        County county = session.get(County.class, id);
        Hibernate.initialize(county.getMunicipalities());
        session.close();
        return county;
    }

    @Override
    public void save(County county) {

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.saveOrUpdate(county);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void update(County county) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.saveOrUpdate(county);

        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void delete(County county) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.delete(county);

        session.getTransaction().commit();

        session.close();
    }
}
