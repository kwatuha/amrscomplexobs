package org.openmrs.module.amrscomplexobs.handler;

/**
 * Created with IntelliJ IDEA.
 * User: alfayo
 * Date: 3/26/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptComplex;
import org.openmrs.FieldType;
import org.openmrs.Form;
import org.openmrs.User;
import org.openmrs.module.amrscomplexobs.model.AmrsComplexHandler;
import org.openmrs.module.amrscomplexobs.model.AmrsPersonType;
import org.openmrs.module.amrscomplexobs.model.AmrsPersonTypeFields;
import org.openmrs.module.amrscomplexobs.model.AmrsPersonTypeFormFields;
import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeHandler;
import org.openmrs.module.amrscomplexobs.service.AmrscomplexobsService;

import java.io.CharArrayReader;
import java.io.Reader;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


import org.openmrs.api.APIException;
import org.openmrs.Field;
import org.openmrs.FormField;
import org.openmrs.Obs;

import org.openmrs.api.context.Context;

import org.openmrs.obs.ComplexData;
import org.openmrs.api.ObsService;
import org.openmrs.api.ConceptService;
import org.openmrs.api.AdministrationService;
import org.openmrs.obs.SerializableComplexObsHandler;

public class AMRSComplexObsHandler implements SerializableComplexObsHandler {

    private Log log = LogFactory.getLog(this.getClass());

    public Set<FormField> getFormFields(Concept complexConcept, Form form) {
        Set<FormField> formFields = new HashSet<FormField>();

        //Obtain fields associated with handler
        //To DO allow dymanic assignment of handlers, for a start the one handler is set

         AmrsComplexHandler handler=new AmrsComplexHandler();
         handler.setId(1);
         AmrscomplexobsService service=Context.getService(AmrscomplexobsService.class);
         List<AmrsPersontypeHandler>listPersonTypehandlers=service.getAmrsPersonTypeByHandler(handler);
        for (AmrsPersontypeHandler personTypeHandler : listPersonTypehandlers) {

            AmrsPersonType persontype=personTypeHandler.getPersonTypeId();

            List<AmrsPersonTypeFields> listComplexHandlerFields=service.getFieldsByAmrsPersonType(persontype);


            //Add persontype section
            FieldType sectionFieldType=new FieldType();
            sectionFieldType.setId(5);
            Field fieldSection = new Field();
            fieldSection.setConcept(null);
            fieldSection.setFieldType(sectionFieldType);
            fieldSection.setName(persontype.getUuid());
            fieldSection.setTableName(null);
            fieldSection.setAttributeName(null);

            fieldSection.setDescription(persontype.getDescription()) ;
            fieldSection.setDefaultValue(persontype.getUuid());

            FormField formFieldObjSection = getNewFormField();
            formFieldObjSection.setField(fieldSection);
            formFields.add(formFieldObjSection);



            //end of section

                for ( AmrsPersonTypeFields handlerField:listComplexHandlerFields){
                           Field fieldObj = new Field();


                        //save person type related form fields

                            String fieldUuid=UUID.randomUUID().toString();
                            AmrsPersonTypeFormFields amrsPersonTypeFormFields=new AmrsPersonTypeFormFields();
                            amrsPersonTypeFormFields.setAmrsPersonType(persontype);
                            amrsPersonTypeFormFields.setForm(form);
                            amrsPersonTypeFormFields.setUuid(fieldUuid);

                    String testStr= amrsPersonTypeFormFields.toString();

                            AmrsPersonTypeFormFields savedPersonTypeFormField=service.saveAmrsPersonTypeFormFields(amrsPersonTypeFormFields);
                            //Context.flushSession();
                    ////////////////////////////////////////

                           fieldObj.setConcept(handlerField.getConcept());
                           fieldObj.setFieldType(handlerField.getFieldType());
                           fieldObj.setName(handlerField.getFieldLocalName());
                           fieldObj.setTableName(handlerField.getTableName());
                           fieldObj.setAttributeName(handlerField.getAttributeName());
                           fieldObj.setDescription(handlerField.getDescription()) ;
                           fieldObj.setUuid(savedPersonTypeFormField.getUuid());

                   FormField formFieldObj = getNewFormField();
                   formFieldObj.setField(fieldObj);
                   formFieldObj.setParent(formFieldObjSection);

                   formFields.add(formFieldObj);



               }



        }

        return formFields;
    }

    /**
     * @should

     * @param obs
     * @return
     * @throws APIException
     */
    public Obs saveObs(Obs obs) throws APIException {
        Integer locId=obs.getLocation().getId();
        //Integer providerId=obs.getEncounter().getProviderI
        log.info("=========================Start Saving");
       ObsService os = Context.getObsService();
        ConceptService cs = Context.getConceptService();
        //obs.ConceptAnswer mycs=cs.getConceptAnswer(obs.getConcept().getId());
        AdministrationService as = Context.getAdministrationService();
        User user=Context.getAuthenticatedUser();
         // the complex data to put onto an obs that will be saved
         Reader input = new CharArrayReader("This is a string to save to a file".toCharArray());
        ComplexData complexData = new ComplexData("Wonderful councellor", input);
        obs.setCreator(user);
        obs.setComplexData(complexData);
        ConceptComplex conceptComplex = Context.getConceptService().getConceptComplex(obs.getConcept().getId());
       //;
        obs.setValueComplex("internal user by alfayo"+conceptComplex.serialize() );
        ComplexData savedComplex= obs.getComplexData();
        String savedcmpx=savedComplex.getData().toString();
        try {
            //os.saveObs(obs, null);
        }
        finally {
            // we always have to delete this inside the same unit test because it is outside the
            // database and hence can't be "rolled back" like everything else
            //createdFile.delete();
        }
        return obs;
    }

    public Obs getObs(Obs obs, String view) {
        return null;
    }

    public boolean purgeComplexData(Obs obs) {
        return false;
    }

    public String serializeFormData(String data) {
        System.out.println("This is is a more ") ;
       System.out.println(data);
        return null;
    }

    private static FormField getNewFormField(){
        FormField formField = new FormField();
        formField.setUuid(UUID.randomUUID().toString());
        formField.setCreator(Context.getAuthenticatedUser());
        formField.setSortWeight(1.0f);
        formField.setDateCreated(new Date());


        return formField;
    }

}
