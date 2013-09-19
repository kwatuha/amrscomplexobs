package org.openmrs.module.amrscomplexobs.web.controller;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrscomplexobs.model.AmrsComplexHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import  org.openmrs.module.amrscomplexobs.service.AmrscomplexobsService;
import java.util.List;

@Controller
public class AmrscomplexconcepthandlerController {

@RequestMapping(method=RequestMethod.GET, value="module/amrscomplexobs/amrscomplexconcepthandler")
public void pageLoad(ModelMap map){
    AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);
List<AmrsComplexHandler> listAmrsComplexHandler =service.getAmrscomplexconcepthandler();
		
		map.addAttribute("listAmrscomplexconcepthandler", listAmrsComplexHandler);
	}

@RequestMapping(method=RequestMethod.POST, value="module/amrscomplexobs/amrscomplexconcepthandler")
	public void savePage(ModelMap map,
@RequestParam(required=false, value="amrscomplexobshandlerHandlervoidform") String voidamrscomplexobshandlerHandler,
@RequestParam(required=false, value="EditamrscomplexobshandlerHandler") String  editbtn,
@RequestParam(required=false, value="voidamrscomplexobshandlerHandler") String  voidbtn,
@RequestParam(required=false, value="voidreason") String  voidReason,
@RequestParam(required=true, value="handlername") String  handlername){

    AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);

AmrsComplexHandler amrsComplexHandler =new AmrsComplexHandler();
amrsComplexHandler.setHandlerName(handlername);

		service.saveAmrscomplexconcepthandler(amrsComplexHandler);
		List<AmrsComplexHandler> listAmrsComplexHandler =service.getAmrscomplexconcepthandler();
	
	map.addAttribute("listAmrscomplexconcepthandler", listAmrsComplexHandler);
	
	}
	
}