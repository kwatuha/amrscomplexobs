
/**
 * 
 */
package org.openmrs.module.amrscomplexobs.dao.hibernate;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.SessionFactory;
import org.openmrs.module.amrscomplexobs.dao.AmrscomplexobsDAO;

import org.openmrs.module.amrscomplexobs.model.Amrscomplexobs;

/**
 * @author Ampath Developers
 *
 */
public class HibernateAmrscomplexobsDAO implements AmrscomplexobsDAO {
	
	private SessionFactory sessionFactory;
	
	
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
    	return sessionFactory;
    }

	
    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
    	this.sessionFactory = sessionFactory;
    }

public Amrscomplexobs saveAmrscomplexobs(Amrscomplexobs amrscomplexobs) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().saveOrUpdate(amrscomplexobs);
		
		return amrscomplexobs;
	
	}


	@SuppressWarnings("unchecked")
	public List<Amrscomplexobs> getAmrscomplexobs() {
		// TODO Auto-generated method stub
		
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Amrscomplexobs.class);
		
		
		return criteria.list();
		
	}
		@SuppressWarnings("unchecked")
	public Amrscomplexobs getAmrscomplexobsByUuid(String uuid) {	
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Amrscomplexobs.class).add(
		Expression.eq("uuid", uuid));
		
		@SuppressWarnings("unchecked")
		
		List<Amrscomplexobs>amrscomplexobs=criteria.list();
		if (null==amrscomplexobs||amrscomplexobs.isEmpty()){
		return null;
		}
		return amrscomplexobs.get(0);
		}
	}