package org.openmrs.module.amrscomplexobs.web.controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.*;
import org.openmrs.api.*;
import org.openmrs.api.context.Context;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import  org.openmrs.module.amrscomplexobs.service.AmrscomplexobsService;
import  org.openmrs.module.amrscomplexobs.model.ComplexConceptFields;
import java.util.List;
import java.util.Date;

@Controller
public class ComplexConceptFieldsController {



@RequestMapping(method=RequestMethod.GET, value="module/amrscomplexobs/complexConceptFields")
public void pageLoad(ModelMap map){



    //Administrative service

    PersonService personService=Context.getPersonService() ;
    FormService formService=Context.getFormService() ;
    ConceptService cservice=Context.getConceptService();
    AmrscomplexobsService amrsComplexObsservice=Context.getService(AmrscomplexobsService.class);

    List<ComplexConceptFields> listComplexConceptFields=amrsComplexObsservice.getComplexConceptFields();
    List<FieldType> listFieldTypes=formService.getAllFieldTypes();
    List<Form> listForms=formService.getAllForms()  ;
    List<Concept> listConcepts=cservice.getAllConcepts();
    List<PersonAttributeType> listPersonAttributes=personService.getAllPersonAttributeTypes()  ;

		
		 map.addAttribute("listComplexConceptFields",listComplexConceptFields);
         map.addAttribute("listFieldTypes",listFieldTypes);
         map.addAttribute("listForms",listForms);
         map.addAttribute("listConcepts",listConcepts);
         map.addAttribute("listAttributes",listPersonAttributes);
	}

@RequestMapping(method=RequestMethod.POST, value="module/amrscomplexobs/complexConceptFields")
	public void savePage(ModelMap map,
@RequestParam(required=false, value="complexobshandlerFieldvoidform") String voidcomplexobshandlerField,
@RequestParam(required=false, value="EditcomplexobshandlerField") String  editbtn,
@RequestParam(required=false, value="voidcomplexobshandlerField") String  voidbtn,
@RequestParam(required=false, value="voidreason") String  voidReason,
@RequestParam(required=true, value="conceptid") Integer  conceptid,
@RequestParam(required=true, value="formid") Integer  formid,
@RequestParam(required=true, value="fieldname") String  fieldname){


    PersonService personService=Context.getPersonService() ;
    FormService formService=Context.getFormService() ;
    ConceptService cservice=Context.getConceptService();
    AmrscomplexobsService amrsComplexObsservice=Context.getService(AmrscomplexobsService.class);

ComplexConceptFields complexconceptfields=new ComplexConceptFields();
complexconceptfields.setConceptId(conceptid);

complexconceptfields.setFormId(formid);

complexconceptfields.setFieldName(fieldname);

         amrsComplexObsservice.saveComplexConceptFields(complexconceptfields);
	List<ComplexConceptFields> listComplexConceptFields=amrsComplexObsservice.getComplexConceptFields();
    List<FieldType> listFieldTypes=formService.getAllFieldTypes();
    List<Concept> listConcepts=cservice.getAllConcepts();
    List<PersonAttributeType> listPersonAttributes=personService.getAllPersonAttributeTypes()  ;

    List<Form> listForms=formService.getAllForms()  ;
	   map.addAttribute("listComplexConceptFields",listComplexConceptFields);
       map.addAttribute("listFieldTypes",listFieldTypes);
       map.addAttribute("listForms",listForms);
       map.addAttribute("listConcepts",listConcepts);
       map.addAttribute("listAttributes",listPersonAttributes);
	
	}
	
}