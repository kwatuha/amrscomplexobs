package org.openmrs.module.amrscomplexobs.web.controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import  org.openmrs.api.LocationService;
import  org.openmrs.Location;
import  org.openmrs.module.amrscomplexobs.service.AmrscomplexobsService;
import  org.openmrs.module.amrscomplexobs.model.Amrscomplexconcepthandler;
import java.util.List;
import java.util.Date;

@Controller
public class AmrscomplexconcepthandlerController {

@RequestMapping(method=RequestMethod.GET, value="module/amrscomplexobs/amrscomplexconcepthandler")
public void pageLoad(ModelMap map){
    AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);
List<Amrscomplexconcepthandler> listAmrscomplexconcepthandler=service.getAmrscomplexconcepthandler();
		
		map.addAttribute("listAmrscomplexconcepthandler",listAmrscomplexconcepthandler);
	}

@RequestMapping(method=RequestMethod.POST, value="module/amrscomplexobs/amrscomplexconcepthandler")
	public void savePage(ModelMap map,
@RequestParam(required=false, value="amrscomplexobshandlerHandlervoidform") String voidamrscomplexobshandlerHandler,
@RequestParam(required=false, value="EditamrscomplexobshandlerHandler") String  editbtn,
@RequestParam(required=false, value="voidamrscomplexobshandlerHandler") String  voidbtn,
@RequestParam(required=false, value="voidreason") String  voidReason,
@RequestParam(required=true, value="handlername") String  handlername){

    AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);

Amrscomplexconcepthandler amrscomplexconcepthandler=new Amrscomplexconcepthandler();
amrscomplexconcepthandler.setHandlerName(handlername);

		service.saveAmrscomplexconcepthandler(amrscomplexconcepthandler);
		List<Amrscomplexconcepthandler> listAmrscomplexconcepthandler=service.getAmrscomplexconcepthandler();
	
	map.addAttribute("listAmrscomplexconcepthandler",listAmrscomplexconcepthandler);
	
	}
	
}