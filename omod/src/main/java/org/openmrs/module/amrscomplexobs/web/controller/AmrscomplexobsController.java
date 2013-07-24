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
import  org.openmrs.module.amrscomplexobs.model.Amrscomplexobs;
import java.util.List;
import java.util.Date;

@Controller
public class AmrscomplexobsController {

@RequestMapping(method=RequestMethod.GET, value="module/amrscomplexobs/amrscomplexobs")
public void pageLoad(ModelMap map){
AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);
List<Amrscomplexobs> listAmrscomplexobs=service.getAmrscomplexobs();
		
		map.addAttribute("listAmrscomplexobs",listAmrscomplexobs);
	}

@RequestMapping(method=RequestMethod.POST, value="module/amrscomplexobs/amrscomplexobs")
	public void savePage(
ModelMap map,
@RequestParam(required=false, value="voidform") String vvoid,
@RequestParam(required=false, value="Edit") String  editbtn,
@RequestParam(required=false, value="void") String  voidbtn,
@RequestParam(required=false, value="voidreason") String  voidReason){

AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);

       Amrscomplexobs amrscomplexobs=new Amrscomplexobs();
		service.saveAmrscomplexobs(amrscomplexobs);
		List<Amrscomplexobs> listAmrscomplexobs=service.getAmrscomplexobs();
	
	map.addAttribute("listAmrscomplexobs",listAmrscomplexobs);
	
	}
	
}

