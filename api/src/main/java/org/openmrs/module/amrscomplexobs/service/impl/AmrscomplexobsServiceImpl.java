
package org.openmrs.module.amrscomplexobs.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.amrscomplexobs.dao.AmrscomplexobsDAO;
import org.openmrs.module.amrscomplexobs.service.AmrscomplexobsService;

import org.openmrs.module.amrscomplexobs.model.Amrscomplexobs;

/**
 * @author Ampath developers
 *
 */
public class AmrscomplexobsServiceImpl extends BaseOpenmrsService implements AmrscomplexobsService {
protected static final Log log = LogFactory.getLog(AmrscomplexobsServiceImpl.class);
	
	

	
	
	private AmrscomplexobsDAO amrscomplexobsDAO;
	
	/**
	 * @param AmrscomplexobsDAO the amrscomplexobsDAO to set
	 */
	public void setAmrscomplexobsDAO(AmrscomplexobsDAO amrscomplexobsDAO) {
		this.amrscomplexobsDAO = amrscomplexobsDAO;
	}
public Amrscomplexobs saveAmrscomplexobs(Amrscomplexobs amrscomplexobs) {
		
		return amrscomplexobsDAO.saveAmrscomplexobs(amrscomplexobs);
		 
	}

	public List<Amrscomplexobs> getAmrscomplexobs() {
		
		return amrscomplexobsDAO.getAmrscomplexobs();
	}
	
	public  Amrscomplexobs getAmrscomplexobsByUuid(String uuid) {
		
		return amrscomplexobsDAO.getAmrscomplexobsByUuid(uuid);
	}
	}