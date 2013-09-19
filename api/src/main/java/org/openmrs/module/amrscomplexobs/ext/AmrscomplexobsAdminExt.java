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
map.put("module/amrscomplexobs/complexConceptFields.form", "Enty Fields");
map.put("module/amrscomplexobs/amrscomplexconcepthandler.form", "Handlers");
map.put("module/amrscomplexobs/amrscomplexHandlerFields.form", "Handler Fields");
map.put("module/amrscomplexobs/fields.list", "List Fields");

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
