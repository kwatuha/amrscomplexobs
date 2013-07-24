
/**
 * 
 */
package org.openmrs.module.amrscomplexobs.ext;

import org.openmrs.module.web.extension.PatientDashboardTabExt;

/**
 * @author Ampath Developers
 *
 */
public class AmrscomplexobsPatientDashboardTab extends PatientDashboardTabExt {

	/* (non-Javadoc)
	 * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getPortletUrl()
	 */
	@Override
	public String getPortletUrl() {
		// TODO Auto-generated method stub
		return "patientAmrscomplexobs";
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getRequiredPrivilege()
	 */
	@Override
	public String getRequiredPrivilege() {
		// TODO Auto-generated method stub
		return "Manage Amrscomplexobs";
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getTabId()
	 */
	@Override
	public String getTabId() {
		// TODO Auto-generated method stub
		return "PatientAmrscomplexobs";
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.web.extension.PatientDashboardTabExt#getTabName()
	 */
	@Override
	public String getTabName() {
		// TODO Auto-generated method stub
		return "Amrscomplexobs";
	}

}
