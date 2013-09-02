package org.openmrs.module.amrscomplexobs.web.controller;

import org.openmrs.Concept;
import org.openmrs.FieldType;
import org.openmrs.Form;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.ConceptService;
import org.openmrs.api.FormService;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrscomplexobs.OpenMRSTableFields;
import org.openmrs.module.amrscomplexobs.model.Amrscomplexconcepthandler;
import org.openmrs.module.amrscomplexobs.model.ComplexConceptFields;
import org.openmrs.module.amrscomplexobs.service.AmrscomplexobsService;
import org.openmrs.module.amrscomplexobs.util.AMRSComplexObsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FieldsController {

@RequestMapping(method=RequestMethod.GET, value="module/amrscomplexobs/fields")
public void pageLoad(ModelMap map){
    AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);
    FormService formService=Context.getFormService() ;
    ConceptService cservice=Context.getConceptService();

    List<ComplexConceptFields> listComplexConceptFields=service.getComplexConceptFields();
    List<FieldType> listFieldTypes=formService.getAllFieldTypes();

    List<Concept> listConcepts=cservice.getAllConcepts();
    List<OpenMRSTableFields>listNewPersonFields= AMRSComplexObsUtil.getListDefaultNewPersonFields();

    List<Amrscomplexconcepthandler> listAmrscomplexconcepthandler=service.getAmrscomplexconcepthandler();
         map.addAttribute("listAmrshandler",listAmrscomplexconcepthandler);
         map.addAttribute("listComplexConceptFields",listComplexConceptFields);
         map.addAttribute("listFieldTypes",listFieldTypes);

         map.addAttribute("listConcepts",listConcepts);
         map.addAttribute("listNewPersonFields",listNewPersonFields);


	}

@RequestMapping(method=RequestMethod.POST, value="module/amrscomplexobs/fields")
	public void savePage(ModelMap map,
@RequestParam(required=false, value="complexobshandlerFieldvoidform") String voidcomplexobshandlerField,
@RequestParam(required=false, value="EditcomplexobshandlerField") String  editbtn,
@RequestParam(required=false, value="voidcomplexobshandlerField") String  voidbtn,
@RequestParam(required=false, value="voidreason") String  voidReason,
@RequestParam(required=true, value="conceptid") Integer  conceptid,
@RequestParam(required=true, value="formid") Integer  formid,
@RequestParam(required=true, value="fieldname") String  fieldname){

        AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);
        ComplexConceptFields complexconceptfields=new ComplexConceptFields();
        complexconceptfields.setConceptId(conceptid);
        complexconceptfields.setFormId(formid);
        complexconceptfields.setFieldName(fieldname);
        service.saveComplexConceptFields(complexconceptfields);

        ConceptService cservice=Context.getConceptService();
        FormService formService=Context.getFormService() ;

        List<ComplexConceptFields> listComplexConceptFields=service.getComplexConceptFields();
        List<FieldType> listFieldTypes=formService.getAllFieldTypes();
        List<Concept> listConcepts=cservice.getAllConcepts();
        List<Amrscomplexconcepthandler> listAmrscomplexconcepthandler=service.getAmrscomplexconcepthandler();
        List<OpenMRSTableFields>listNewPersonFields= AMRSComplexObsUtil.getListDefaultNewPersonFields();

        map.addAttribute("listAmrshandler",listAmrscomplexconcepthandler);
        map.addAttribute("listComplexConceptFields",listComplexConceptFields);
        map.addAttribute("listFieldTypes",listFieldTypes);
        map.addAttribute("listConcepts",listConcepts);
        map.addAttribute("listNewPersonFields",listNewPersonFields);




}

    //
    @RequestMapping("/module/amrscomplexobs/fieldList.json")
    public @ResponseBody
    Map<String, Object> getErrorBatchAsJson(
            @RequestParam("iDisplayStart") int iDisplayStart,
            @RequestParam("iDisplayLength") int iDisplayLength,
            @RequestParam("sSearch") String sSearch,
            @RequestParam("sEcho") int sEcho) throws IOException {

        // get the data

        PersonService personService=Context.getPersonService() ;
        List<PersonAttributeType> listPersonAttributes=personService.getAllPersonAttributeTypes(false)  ;
        // form the results dataset
        List<Object> results = new ArrayList<Object>();
        for (PersonAttributeType attribute : listPersonAttributes) {
            results.add(generateObjectMap(attribute));
        }
        // build the response
        Map<String, Object> response;
        response = new HashMap<String, Object>();
        response.put("iTotalRecords", listPersonAttributes.size());
        response.put("iTotalDisplayRecords", listPersonAttributes.size());
        response.put("sEcho", sEcho);
        response.put("aaData", results.toArray());

        // send it
        return response;
    }


    private Map<String, Object> generateObjectMap(PersonAttributeType attribute) {
        // try to stick to basic types; String, Integer, etc (not Date)
        // JSP expects: [id, error, details, form name, comment]
         String myname=attribute.getName();
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("id", attribute.getPersonAttributeTypeId());
        result.put("name", attribute.getName());
        result.put("attributeTypeId", attribute.getPersonAttributeTypeId());
        result.put("description", attribute.getDescription());
        result.put("uuid", attribute.getUuid());
        return result;
    }
}

