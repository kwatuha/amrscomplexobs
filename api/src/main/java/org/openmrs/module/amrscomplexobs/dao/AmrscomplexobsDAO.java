
/**
 * 
 */
package org.openmrs.module.amrscomplexobs.dao;

import java.util.List;
import org.openmrs.module.amrscomplexobs.model.Amrscomplexobs;

/**
 *  
 * @author Ampath Developers
 */
public interface AmrscomplexobsDAO {
	
  
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