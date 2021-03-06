package se.soahki.countyinfo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.soahki.countyinfo.model.County;
import se.soahki.countyinfo.model.Municipality;
import se.soahki.countyinfo.model.Population;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
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
    public List<Population> findByYear(Integer year) {
        Session session = sessionFactory.openSession();

        // Create query critera
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Population> query = builder.createQuery(Population.class);
        Root<Population> criteria = query.from(Population.class);
        ParameterExpression<Integer> paramYear = builder.parameter(Integer.class);
        query.select(criteria).where(builder.equal(criteria.get("year"), paramYear));

        TypedQuery<Population> typedQuery = session.createQuery(query);
        typedQuery.setParameter(paramYear, year);

        // Execute criteria
        List<Population> populations = typedQuery.getResultList();
                //session.createQuery(query).getResultList();

        session.close();

        return populations;
    }

    @Override
    public List<Population> findByMunicipality(Municipality municipality) {
        List<Population> populations = findAll();
        List<Population> municipalityPopulations = new ArrayList<>();
        for (Population population : populations) {
            Municipality popMun = population.getMunicipality();
            if (popMun.getCode().equals(municipality.getCode())) {
                System.out.println("\n\nADDED: " + population.getYear() + "\n\n");
                municipalityPopulations.add(population);
                if (population.getYear() == 2016) {
                    break;
                }
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
