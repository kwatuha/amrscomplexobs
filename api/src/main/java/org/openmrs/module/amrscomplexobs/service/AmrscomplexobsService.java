/**
 * 
 */
package org.openmrs.module.amrscomplexobs.service;

import java.util.List;

import org.openmrs.Field;
import org.openmrs.Form;
import org.openmrs.api.OpenmrsService;

import org.openmrs.module.amrscomplexobs.model.AmrsPersonTypeFields;
import org.openmrs.module.amrscomplexobs.model.AmrsComplexHandler;
import org.openmrs.module.amrscomplexobs.model.AmrsPersonTypeFormFields;
import org.openmrs.module.amrscomplexobs.model.ComplexConceptFields;
import org.springframework.transaction.annotation.Transactional;

import org.openmrs.module.amrscomplexobs.model.Amrscomplexobs;


import org.openmrs.module.amrscomplexobs.model.AmrsPersonType;

import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeAttributes;

import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeConcept;

import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeHandler;

/**
 * @author Ampath Developers
 *
 */
@Transactional

public interface AmrscomplexobsService extends OpenmrsService{

/**
	 * 
	 * save Amrscomplexobs
	 * @param Amrscomplexobs to be saved
	 * @return Amrscomplexobs object
	 */
	
	public Amrscomplexobs saveAmrscomplexobs(Amrscomplexobs amrscomplexobs);
	/**
	 * @return all the Amrscomplexobs
	 * 
	 */
	public List<Amrscomplexobs> getAmrscomplexobs();
	public Amrscomplexobs getAmrscomplexobsByUuid(String uuid);



    public ComplexConceptFields saveComplexConceptFields(ComplexConceptFields complexconceptfields);
    /**
     * @return all the ComplexConceptFields
     *
     */
    public List<ComplexConceptFields> getComplexConceptFields();
    public ComplexConceptFields getComplexConceptFieldsByUuid(String uuid);
    public List<Field> getComplexConceptFieldUuids();

    //New
    public AmrsPersonTypeFields saveAmrscomplexHandlerFields(AmrsPersonTypeFields amrscomplexhandlerfields);
    /**
     * @return all the AmrsPersonTypeFields
     *
     */
    public List<AmrsPersonTypeFields> getAmrscomplexHandlerFields();
    public AmrsPersonTypeFields getAmrscomplexHandlerFieldsByUuid(String uuid);

    /**
     *
     * save AmrsComplexHandler
     * @param Amrscomplexconcepthandler to be saved
     * @return AmrsComplexHandler object
     */

    public AmrsComplexHandler saveAmrscomplexconcepthandler(AmrsComplexHandler amrsComplexHandler);
    /**
     * @return all the AmrsComplexHandler
     *
     */
    public List<AmrsComplexHandler> getAmrscomplexconcepthandler();
    public AmrsComplexHandler getAmrscomplexconcepthandlerByUuid(String uuid);
    public List<AmrsPersonTypeFields> getFieldsByAmrscomplexHandlerId(AmrsComplexHandler handlerId);

    public AmrsPersonType saveAmrsPersonType(AmrsPersonType amrspersontype);
    /**
     * @return all the AmrsPersonType
     *
     */
    public List<AmrsPersonType> getAmrsPersonType();
    public AmrsPersonType getAmrsPersonTypeByUuid(String uuid);

    /**
     *
     * save AmrsPersontypeAttributes
     * @param AmrsPersontypeAttributes to be saved
     * @return AmrsPersontypeAttributes object
     */

    public AmrsPersontypeAttributes saveAmrsPersontypeAttributes(AmrsPersontypeAttributes amrspersontypeattributes);
    /**
     * @return all the AmrsPersontypeAttributes
     *
     */
    public List<AmrsPersontypeAttributes> getAmrsPersontypeAttributes();
    public AmrsPersontypeAttributes getAmrsPersontypeAttributesByUuid(String uuid);

    /**
     *
     * save AmrsPersontypeConcept
     * @param AmrsPersontypeConcept to be saved
     * @return AmrsPersontypeConcept object
     */

    public AmrsPersontypeConcept saveAmrsPersontypeConcept(AmrsPersontypeConcept amrspersontypeconcept);
    /**
     * @return all the AmrsPersontypeConcept
     *
     */
    public List<AmrsPersontypeConcept> getAmrsPersontypeConcept();
    public AmrsPersontypeConcept getAmrsPersontypeConceptByUuid(String uuid);
    public AmrsPersontypeHandler saveAmrsPersontypeHandler(AmrsPersontypeHandler amrspersontypehandler);
    /**
     * @return all the AmrsPersontypeHandler
     *
     */
    public List<AmrsPersontypeHandler> getAmrsPersontypeHandler();
    public AmrsPersontypeHandler getAmrsPersontypeHandlerByUuid(String uuid);

    public List<AmrsPersonTypeFields> getFieldsByAmrsPersonType(AmrsPersonType personType);
    public List<AmrsPersontypeHandler> getAmrsPersonTypeByHandler(AmrsComplexHandler handlerId);

    public AmrsPersonTypeFormFields saveAmrsPersonTypeFormFields(AmrsPersonTypeFormFields amrsPersonTypeFormFields);
    public List<AmrsPersonTypeFormFields> getAmrsPersonTypeFormFieldsByFormPersonType(AmrsPersonType personType, Form form);

}