
/**
 * 
 */
package org.openmrs.module.amrscomplexobs.dao;

import java.util.List;

import org.openmrs.Field;
import org.openmrs.Form;
import org.openmrs.module.amrscomplexobs.model.AmrsComplexHandler;
import org.openmrs.module.amrscomplexobs.model.AmrsPersonTypeFields;
import org.openmrs.module.amrscomplexobs.model.AmrsPersonTypeFormFields;
import org.openmrs.module.amrscomplexobs.model.Amrscomplexobs;
import org.openmrs.module.amrscomplexobs.model.ComplexConceptFields;

import org.openmrs.module.amrscomplexobs.model.AmrsPersonType;

import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeAttributes;

import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeConcept;

import org.openmrs.module.amrscomplexobs.model.AmrsPersontypeHandler;

/**
 *
 * @author Ampath Developers
 */
public interface AmrscomplexobsDAO {


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
    public List<Field> getComplexConceptFieldUuids() ;

    /**
     *
     * save ComplexConceptFields
     * @param ComplexConceptFields to be saved
     * @return ComplexConceptFields object
     */

    public ComplexConceptFields saveComplexConceptFields(ComplexConceptFields complexconceptfields);
    public List<ComplexConceptFields> getComplexConceptFields();
    public ComplexConceptFields getComplexConceptFieldsByUuid(String uuid);


    //New
    public AmrsPersonTypeFields saveAmrscomplexHandlerFields(AmrsPersonTypeFields amrscomplexhandlerfields);
    public List<AmrsPersonTypeFields> getAmrscomplexHandlerFields();
    public AmrsPersonTypeFields getAmrscomplexHandlerFieldsByUuid(String uuid);
    public List<AmrsPersonTypeFields> getFieldsByAmrscomplexHandlerId(AmrsComplexHandler handlerId);
    public AmrsComplexHandler saveAmrscomplexconcepthandler(AmrsComplexHandler amrsComplexHandler);
    public List<AmrsComplexHandler> getAmrscomplexconcepthandler();
    public AmrsComplexHandler getAmrscomplexconcepthandlerByUuid(String uuid);

	//
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
    /**
     *
     * save AmrsPersontypeHandler
     * @param AmrsPersontypeHandler to be saved
     * @return AmrsPersontypeHandler object
     */

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