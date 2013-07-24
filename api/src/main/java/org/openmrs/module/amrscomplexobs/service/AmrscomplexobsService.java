/**
 * 
 */
package org.openmrs.module.amrscomplexobs.service;

import java.util.List;

import org.openmrs.api.OpenmrsService;

import org.springframework.transaction.annotation.Transactional;

import org.openmrs.module.amrscomplexobs.model.Amrscomplexobs;

/**
 * @author Ampath Developers
 *
 */
@Transactional

public interface AmrscomplexobsService extends OpenmrsService{

/**
	 * 
	 * save Amrscomplexobs
	 * @param Amrscomplexobs to be saved
	 * @return Amrscomplexobs object
	 */
	
	public Amrscomplexobs saveAmrscomplexobs(Amrscomplexobs amrscomplexobs);
	/**
	 * @return all the Amrscomplexobs
	 * 
	 */
	public List<Amrscomplexobs> getAmrscomplexobs();
	public Amrscomplexobs getAmrscomplexobsByUuid(String uuid);
	
	}