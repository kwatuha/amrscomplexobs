/**
 * 
 */
package org.openmrs.module.amrscomplexobs.ext;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.web.extension.AdministrationSectionExt;

/**
 * @author Ampath Developers
 * 
 */
public class AmrscomplexobsAdminExt extends AdministrationSectionExt {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getLinks()
	 */
	@Override
	public Map<String, String> getLinks() {
		Map<String, String> map = new HashMap<String, String>();
		
		
map.put("module/amrscomplexobs/amrscomplexobs.form", "Amrscomplexobs ");

return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getTitle()
	 */
	@Override
	public String getTitle() {
		return "Amrscomplexobs Module";
	}

}
