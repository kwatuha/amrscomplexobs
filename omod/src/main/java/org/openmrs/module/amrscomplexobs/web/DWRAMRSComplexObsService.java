/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.amrscomplexobs.web;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.openmrs.Concept;
import org.openmrs.FieldType;
import org.openmrs.Form;
import org.openmrs.PersonAttributeType;
import org.openmrs.api.ConceptService;
import org.openmrs.api.FormService;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrscomplexobs.OpenMRSTableFields;
import org.openmrs.module.amrscomplexobs.model.AmrsComplexHandler;
import org.openmrs.module.amrscomplexobs.model.AmrsPersonType;
import org.openmrs.module.amrscomplexobs.model.AmrsPersonTypeFields;
import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeAttributes;
import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeConcept;
import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeHandler;
import org.openmrs.module.amrscomplexobs.model.ComplexConceptFields;
import org.openmrs.module.amrscomplexobs.service.AmrscomplexobsService;
import org.openmrs.module.amrscomplexobs.util.AMRSComplexObsUtil;

import java.util.List;


/**
 *DWR class for AMRS Complex Obs module
 */
public class DWRAMRSComplexObsService {

    private static final Log log = LogFactory.getLog(DWRAMRSComplexObsService.class);

    PersonService personService=Context.getPersonService() ;
    FormService formService=Context.getFormService() ;
    ConceptService cservice=Context.getConceptService();
    AmrscomplexobsService amrsComplexObsservice=Context.getService(AmrscomplexobsService.class);

    public List<PersonAttributeType> getPersonAttributes()  {


        List<ComplexConceptFields> listComplexConceptFields=amrsComplexObsservice.getComplexConceptFields();
        List<FieldType> listFieldTypes=formService.getAllFieldTypes();
        List<Concept> listConcepts=cservice.getAllConcepts();
        List<PersonAttributeType> listPersonAttributes=personService.getAllPersonAttributeTypes()  ;

        List<Form> listForms=formService.getAllForms()  ;
        /*map.addAttribute("listComplexConceptFields",listComplexConceptFields);
        map.addAttribute("listFieldTypes",listFieldTypes);
        map.addAttribute("listForms",listForms);
        map.addAttribute("listConcepts",listConcepts);
        map.addAttribute("listAttributes",listPersonAttributes);*/

        return listPersonAttributes;
    }

    public String saveFormFields(Integer fieldType,String conceptName,String tableName,String attributeName,String defaultValue,Boolean selectMultiple,String fieldName,String personTypeUuid)   {
        String results=null;
        ConceptService cservice=Context.getConceptService();
        FormService formService=Context.getFormService() ;
        Concept currentConcept= cservice.getConceptByName(conceptName);
        FieldType currentFieldType= formService.getFieldType(fieldType);

        AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);
        AmrsPersonTypeFields personTypeFields=new AmrsPersonTypeFields();

        AmrsPersonType persontype=service.getAmrsPersonTypeByUuid(personTypeUuid);

        personTypeFields.setConcept(currentConcept);
        personTypeFields.setTableName(tableName);
        personTypeFields.setFieldType(currentFieldType);
        personTypeFields.setFieldLocalName(fieldName);
        personTypeFields.setAmrsPersonType(persontype);
        personTypeFields.setAttributeName(attributeName);
        personTypeFields.setDefaultValue(defaultValue);
        personTypeFields.setSelectMultiple(selectMultiple);
        service.saveAmrscomplexHandlerFields(personTypeFields) ;

        return "Saved Successfully" ;
    }

    public String savePersonAttributes(String attributeUuid,String personTypeUuid){
        String msg=null;
        String fieldName=null;
        String defaultValues=null;
        Boolean selectMultiple=false;
        PersonService personService=Context.getPersonService() ;
        AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);
        AmrsPersonTypeFields personTypeFields=new AmrsPersonTypeFields();

        AmrsPersonType persontype=service.getAmrsPersonTypeByUuid(personTypeUuid);

        if(!StringUtils.isEmpty(attributeUuid)) {

           /* for (int i = 0; i < personAttributesList.size(); i++) {
                String uuidfound=(String)personAttributesList.get(i);*/


                FieldType defaultFieldType= formService.getFieldType(2);

                PersonAttributeType personAtr =personService.getPersonAttributeTypeByUuid(attributeUuid) ;
                defaultValues="$!{patient.getAttribute('"+personAtr.getName()+"').getValue()}" ;



                String attributeTypeName= personAtr.getName();
                attributeTypeName=attributeTypeName.replace(" ","_");
                fieldName= attributeTypeName.toUpperCase();
                personTypeFields.setId(null);
                personTypeFields.setTableName("patient");
                personTypeFields.setFieldType(defaultFieldType);
                personTypeFields.setFieldLocalName(fieldName);
                personTypeFields.setAmrsPersonType(persontype);
                personTypeFields.setAttributeName(personAtr.getName());
                personTypeFields.setDefaultValue(defaultValues);
                personTypeFields.setSelectMultiple(selectMultiple);
                service.saveAmrscomplexHandlerFields(personTypeFields) ;




           // }


        }


        return  "Saved Successfully" ;
    }

    //Save new person  fields

    public String saveNewPersonFields(String formOpeMRStag,String personTypeUuid){

        AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);
        AmrsPersonTypeFields personTypeFields=new AmrsPersonTypeFields();

        List<OpenMRSTableFields> tableFieldList= AMRSComplexObsUtil.getListDefaultNewPersonFields();
        FieldType defaultFieldType= formService.getFieldType(2);
        AmrsPersonType persontype=service.getAmrsPersonTypeByUuid(personTypeUuid);
       // if(openmrstag.size()>0) {

            for (int i = 0; i < tableFieldList.size(); i++) {
                personTypeFields=new AmrsPersonTypeFields();
                String openmrsTag=tableFieldList.get(i).getOpemrsTag();
                OpenMRSTableFields tableField=tableFieldList.get(i);

               if(StringUtils.equals(formOpeMRStag,openmrsTag)){

                   personTypeFields.setTableName(tableField.getOpemrsTable());
                   personTypeFields.setFieldType(defaultFieldType);
                   personTypeFields.setFieldLocalName(tableField.getOpenmrsAttribute());
                   personTypeFields.setAmrsPersonType(persontype);
                   personTypeFields.setAttributeName(tableField.getOpenmrsAttribute());
                   personTypeFields.setDefaultValue(tableField.getDefaultValue());
                   personTypeFields.setSelectMultiple(false);
                   service.saveAmrscomplexHandlerFields(personTypeFields);
                   Context.flushSession();
              }




           // }


        }

        return  "Saved Successfully" ;
    }

    public void saveAmrsPersontypeAttributes(Integer persontypeid,Integer  openmrsattributeid){

        AmrsPersonType persontype=new AmrsPersonType();
        persontype.setId(persontypeid);

        AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);

        AmrsPersontypeAttributes amrspersontypeattributes=new AmrsPersontypeAttributes();
        amrspersontypeattributes.setPersonTypeId(persontype);

        amrspersontypeattributes.setOpenmrsAttributeId(openmrsattributeid);
        service.saveAmrsPersontypeAttributes(amrspersontypeattributes);


    }

    public String saveAmrsPersontypeConcept(  Integer  persontypeid,Integer conceptid){
        Concept concept=Context.getConceptService().getConcept(conceptid);

        AmrsPersonType persontype=new AmrsPersonType();
        persontype.setId(persontypeid);

        AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);

        AmrsPersontypeConcept amrspersontypeconcept=new AmrsPersontypeConcept();
        amrspersontypeconcept.setPersonTypeId(persontype);

        amrspersontypeconcept.setConceptId(concept);

        service.saveAmrsPersontypeConcept(amrspersontypeconcept);
        return  "Saved Successfully" ;

    }

    public String saveAmrsPersonType(String  persontypename,String  description){

        AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);

        AmrsPersonType amrspersontype=new AmrsPersonType();
        amrspersontype.setPersonTypeName(persontypename);

        amrspersontype.setDescription(description);

        service.saveAmrsPersonType(amrspersontype);

        return  "Saved Successfully" ;
    }

    public String saveAmrsPersontypeHandler(String handleruuid, String personTypeUuid){





        AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);

        AmrsPersonType persontype=service.getAmrsPersonTypeByUuid(personTypeUuid);
        AmrsComplexHandler  handler=service.getAmrscomplexconcepthandlerByUuid(handleruuid);
        AmrsPersontypeHandler amrspersontypehandler=new AmrsPersontypeHandler();
        amrspersontypehandler.setHandlerId(handler);

        amrspersontypehandler.setPersonTypeId(persontype);

        service.saveAmrsPersontypeHandler(amrspersontypehandler);

        return  "Saved Successfully" ;
    }

    public String saveAmrscomplexconcepthandler(String  handlername){

        AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);

        AmrsComplexHandler amrsComplexHandler =new AmrsComplexHandler();
        amrsComplexHandler.setHandlerName(handlername);

        service.saveAmrscomplexconcepthandler(amrsComplexHandler);

        return  "Saved Successfully" ;
    }

    /*public void saveNewPersonTypeFields(String formOpeMRStag,Integer personTypeid){

        AmrsPersonType persontype=new AmrsPersonType();
        persontype.setId(personTypeid);

        AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);
        AmrsPersonTypeFields personTypeFields=new AmrsPersonTypeFields();

        List<OpenMRSTableFields> tableFieldList= AMRSComplexObsUtil.getListDefaultNewPersonFields();
        FieldType defaultFieldType= formService.getFieldType(2);

        // if(openmrstag.size()>0) {

        for (int i = 0; i < tableFieldList.size(); i++) {
            personTypeFields=new AmrsPersonTypeFields();
            String openmrsTag=tableFieldList.get(i).getOpemrsTag();
            OpenMRSTableFields tableField=tableFieldList.get(i);

            if(StringUtils.equals(formOpeMRStag,openmrsTag)){

                personTypeFields.setTableName(tableField.getOpemrsTable());
                personTypeFields.setFieldType(defaultFieldType);
                personTypeFields.setFieldLocalName(tableField.getOpenmrsAttribute());
                personTypeFields.setAmrsPersonType(persontype);
                personTypeFields.setAttributeName(tableField.getOpenmrsAttribute());
                personTypeFields.setDefaultValue(tableField.getDefaultValue());
                personTypeFields.setSelectMultiple(false);
                service.saveAmrscomplexPersonTypeFields(personTypeFields) ;
                Context.flushSession();
            }




            // }


        }
    }*/

}