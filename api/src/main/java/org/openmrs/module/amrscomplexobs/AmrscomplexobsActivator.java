
package org.openmrs.module.amrscomplexobs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.Activator;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrscomplexobs.handler.AMRSComplexObsHandler;


/**
 * 
 */

/**
 * @author Ampath Developers
 *
 */
public class AmrscomplexobsActivator implements Activator {
	private static Log log = LogFactory.getLog( AmrscomplexobsActivator.class);
	/* (non-Javadoc)
	 * @see org.openmrs.module.Activator#startup()
	 */
	@Override
        public void startup() {
		log.info("Starting Amrscomplexobs module");
        Context.getObsService().registerHandler("AMRSComplexObsHandler", new AMRSComplexObsHandler());
	}
	
	/* (non-Javadoc)
	 * @see org.openmrs.module.Activator#shutdown()
	 */
        @Override
	public void shutdown() {
		log.info("Stopping Amrscomplexobs module");
	}
	
}

