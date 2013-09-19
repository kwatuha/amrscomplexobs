
package org.openmrs.module.amrscomplexobs.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Field;
import org.openmrs.Form;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.amrscomplexobs.dao.AmrscomplexobsDAO;
import org.openmrs.module.amrscomplexobs.model.AmrsPersonTypeFields;
import org.openmrs.module.amrscomplexobs.model.AmrsComplexHandler;
import org.openmrs.module.amrscomplexobs.model.AmrsPersonTypeFormFields;
import org.openmrs.module.amrscomplexobs.model.ComplexConceptFields;
import org.openmrs.module.amrscomplexobs.service.AmrscomplexobsService;

import org.openmrs.module.amrscomplexobs.model.Amrscomplexobs;

import org.openmrs.module.amrscomplexobs.model.AmrsPersonType;

import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeAttributes;

import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeConcept;

import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeHandler;

/**
 * @author Ampath developers
 *
 */
public class AmrscomplexobsServiceImpl extends BaseOpenmrsService implements AmrscomplexobsService {
protected static final Log log = LogFactory.getLog(AmrscomplexobsServiceImpl.class);
	
	

	
	
	private AmrscomplexobsDAO amrscomplexobsDAO;
	
	/**
	 * @param AmrscomplexobsDAO the amrscomplexobsDAO to set
	 */
	public void setAmrscomplexobsDAO(AmrscomplexobsDAO amrscomplexobsDAO) {
		this.amrscomplexobsDAO = amrscomplexobsDAO;
	}
public Amrscomplexobs saveAmrscomplexobs(Amrscomplexobs amrscomplexobs) {
		
		return amrscomplexobsDAO.saveAmrscomplexobs(amrscomplexobs);
		 
	}

	public List<Amrscomplexobs> getAmrscomplexobs() {
		
		return amrscomplexobsDAO.getAmrscomplexobs();
	}
	
	public  Amrscomplexobs getAmrscomplexobsByUuid(String uuid) {
		
		return amrscomplexobsDAO.getAmrscomplexobsByUuid(uuid);
	}


    public ComplexConceptFields saveComplexConceptFields(ComplexConceptFields complexconceptfields) {

        return amrscomplexobsDAO.saveComplexConceptFields(complexconceptfields);

    }

    public List<ComplexConceptFields> getComplexConceptFields() {

        return amrscomplexobsDAO.getComplexConceptFields();
    }

    public  ComplexConceptFields getComplexConceptFieldsByUuid(String uuid) {

        return amrscomplexobsDAO.getComplexConceptFieldsByUuid(uuid);
    }


    public List<Field> getComplexConceptFieldUuids(){
            return amrscomplexobsDAO.getComplexConceptFieldUuids();
    }
    
    //New
       public AmrsPersonTypeFields saveAmrscomplexHandlerFields(AmrsPersonTypeFields amrscomplexhandlerfields) {

        return amrscomplexobsDAO.saveAmrscomplexHandlerFields(amrscomplexhandlerfields);

    }

    public List<AmrsPersonTypeFields> getAmrscomplexHandlerFields() {

        return amrscomplexobsDAO.getAmrscomplexHandlerFields();
    }

    public AmrsPersonTypeFields getAmrscomplexHandlerFieldsByUuid(String uuid) {

        return amrscomplexobsDAO.getAmrscomplexHandlerFieldsByUuid(uuid);
    }
    public AmrsComplexHandler saveAmrscomplexconcepthandler(AmrsComplexHandler amrsComplexHandler) {

        return amrscomplexobsDAO.saveAmrscomplexconcepthandler(amrsComplexHandler);

    }

    public List<AmrsComplexHandler> getAmrscomplexconcepthandler() {

        return amrscomplexobsDAO.getAmrscomplexconcepthandler();
    }

    public AmrsComplexHandler getAmrscomplexconcepthandlerByUuid(String uuid) {

        return amrscomplexobsDAO.getAmrscomplexconcepthandlerByUuid(uuid);
    }

    public List<AmrsPersonTypeFields> getFieldsByAmrscomplexHandlerId(AmrsComplexHandler handlerId){

          return amrscomplexobsDAO.getFieldsByAmrscomplexHandlerId(handlerId);

    }

    public AmrsPersonType saveAmrsPersonType(AmrsPersonType amrspersontype) {

        return amrscomplexobsDAO.saveAmrsPersonType(amrspersontype);

    }

    public List<AmrsPersonType> getAmrsPersonType() {

        return amrscomplexobsDAO.getAmrsPersonType();
    }

    public  AmrsPersonType getAmrsPersonTypeByUuid(String uuid) {

        return amrscomplexobsDAO.getAmrsPersonTypeByUuid(uuid);
    }
    public AmrsPersontypeAttributes saveAmrsPersontypeAttributes(AmrsPersontypeAttributes amrspersontypeattributes) {

        return amrscomplexobsDAO.saveAmrsPersontypeAttributes(amrspersontypeattributes);

    }

    public List<AmrsPersontypeAttributes> getAmrsPersontypeAttributes() {

        return amrscomplexobsDAO.getAmrsPersontypeAttributes();
    }

    public  AmrsPersontypeAttributes getAmrsPersontypeAttributesByUuid(String uuid) {

        return amrscomplexobsDAO.getAmrsPersontypeAttributesByUuid(uuid);
    }
    public AmrsPersontypeConcept saveAmrsPersontypeConcept(AmrsPersontypeConcept amrspersontypeconcept) {

        return amrscomplexobsDAO.saveAmrsPersontypeConcept(amrspersontypeconcept);

    }

    public List<AmrsPersontypeConcept> getAmrsPersontypeConcept() {

        return amrscomplexobsDAO.getAmrsPersontypeConcept();
    }

    public  AmrsPersontypeConcept getAmrsPersontypeConceptByUuid(String uuid) {

        return amrscomplexobsDAO.getAmrsPersontypeConceptByUuid(uuid);
    }
    public AmrsPersontypeHandler saveAmrsPersontypeHandler(AmrsPersontypeHandler amrspersontypehandler) {

        return amrscomplexobsDAO.saveAmrsPersontypeHandler(amrspersontypehandler);

    }

    public List<AmrsPersontypeHandler> getAmrsPersontypeHandler() {

        return amrscomplexobsDAO.getAmrsPersontypeHandler();
    }

    public  AmrsPersontypeHandler getAmrsPersontypeHandlerByUuid(String uuid) {

        return amrscomplexobsDAO.getAmrsPersontypeHandlerByUuid(uuid);
    }

    public List<AmrsPersonTypeFields> getFieldsByAmrsPersonType(AmrsPersonType personType){
        return amrscomplexobsDAO.getFieldsByAmrsPersonType(personType);
    }

    public List<AmrsPersontypeHandler> getAmrsPersonTypeByHandler(AmrsComplexHandler handlerId){

        return amrscomplexobsDAO.getAmrsPersonTypeByHandler(handlerId);
    }

    public AmrsPersonTypeFormFields saveAmrsPersonTypeFormFields(AmrsPersonTypeFormFields amrsPersonTypeFormFields){
        return amrscomplexobsDAO.saveAmrsPersonTypeFormFields(amrsPersonTypeFormFields);

    }
    public List<AmrsPersonTypeFormFields> getAmrsPersonTypeFormFieldsByFormPersonType(AmrsPersonType personType, Form form){
        return amrscomplexobsDAO.getAmrsPersonTypeFormFieldsByFormPersonType(personType, form);

    }

}