
/**
 * 
 */
package org.openmrs.module.amrscomplexobs.dao.hibernate;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Field;
import org.openmrs.Form;
import org.openmrs.module.amrscomplexobs.dao.AmrscomplexobsDAO;

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
 * @author Ampath Developers
 *
 */
public class HibernateAmrscomplexobsDAO implements AmrscomplexobsDAO {
	
	private SessionFactory sessionFactory;
	
	
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
    	return sessionFactory;
    }

	
    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
    	this.sessionFactory = sessionFactory;
    }

public Amrscomplexobs saveAmrscomplexobs(Amrscomplexobs amrscomplexobs) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().saveOrUpdate(amrscomplexobs);
		
		return amrscomplexobs;
	
	}


	@SuppressWarnings("unchecked")
	public List<Amrscomplexobs> getAmrscomplexobs() {
		// TODO Auto-generated method stub
		
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Amrscomplexobs.class);
		
		
		return criteria.list();
		
	}
		@SuppressWarnings("unchecked")
	public Amrscomplexobs getAmrscomplexobsByUuid(String uuid) {	
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Amrscomplexobs.class).add(
		Expression.eq("uuid", uuid));
		
		@SuppressWarnings("unchecked")
		
		List<Amrscomplexobs>amrscomplexobs=criteria.list();
		if (null==amrscomplexobs||amrscomplexobs.isEmpty()){
		return null;
		}
		return amrscomplexobs.get(0);
		}


    public ComplexConceptFields saveComplexConceptFields(ComplexConceptFields complexconceptfields) {
        // TODO Auto-generated method stub

        sessionFactory.getCurrentSession().saveOrUpdate(complexconceptfields);

        return complexconceptfields;

    }


    @SuppressWarnings("unchecked")
    public List<ComplexConceptFields> getComplexConceptFields() {
        // TODO Auto-generated method stub


        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ComplexConceptFields.class);


        return criteria.list();

    }
    @SuppressWarnings("unchecked")
    public ComplexConceptFields getComplexConceptFieldsByUuid(String uuid) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ComplexConceptFields.class).add(
                Expression.eq("uuid", uuid));

        @SuppressWarnings("unchecked")

        List<ComplexConceptFields>complexconceptfields=criteria.list();
        if (null==complexconceptfields||complexconceptfields.isEmpty()){
            return null;
        }
        return complexconceptfields.get(0);
    }

    public List<Field> getComplexConceptFieldUuids() {

        String hql = " FROM Field WHERE concept_id in (select  conceptId from ConceptComplex)";

        Query q = sessionFactory.getCurrentSession().createQuery(hql);

        List<Field> fieldUuids = q.list();
      return fieldUuids;
    }

    //New
    public AmrsPersonTypeFields saveAmrscomplexHandlerFields(AmrsPersonTypeFields amrscomplexhandlerfields) {
        // TODO Auto-generated method stub

        sessionFactory.getCurrentSession().saveOrUpdate(amrscomplexhandlerfields);

        return amrscomplexhandlerfields;

    }


    @SuppressWarnings("unchecked")
    public List<AmrsPersonTypeFields> getAmrscomplexHandlerFields() {
        // TODO Auto-generated method stub


        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersonTypeFields.class);


        return criteria.list();

    }
    @SuppressWarnings("unchecked")
    public AmrsPersonTypeFields getAmrscomplexHandlerFieldsByUuid(String uuid) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersonTypeFields.class).add(
                Expression.eq("uuid", uuid));

        @SuppressWarnings("unchecked")

        List<AmrsPersonTypeFields>amrscomplexhandlerfields=criteria.list();
        if (null==amrscomplexhandlerfields||amrscomplexhandlerfields.isEmpty()){
            return null;
        }
        return amrscomplexhandlerfields.get(0);
    }
    public AmrsComplexHandler saveAmrscomplexconcepthandler(AmrsComplexHandler amrsComplexHandler) {
        // TODO Auto-generated method stub

        sessionFactory.getCurrentSession().saveOrUpdate(amrsComplexHandler);

        return amrsComplexHandler;

    }


    @SuppressWarnings("unchecked")
    public List<AmrsComplexHandler> getAmrscomplexconcepthandler() {
        // TODO Auto-generated method stub


        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsComplexHandler.class);


        return criteria.list();

    }
    @SuppressWarnings("unchecked")
    public AmrsComplexHandler getAmrscomplexconcepthandlerByUuid(String uuid) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsComplexHandler.class).add(
                Expression.eq("uuid", uuid));

        @SuppressWarnings("unchecked")

        List<AmrsComplexHandler> amrsComplexHandler =criteria.list();
        if (null== amrsComplexHandler || amrsComplexHandler.isEmpty()){
            return null;
        }
        return amrsComplexHandler.get(0);
    }

    public List<AmrsPersonTypeFields> getFieldsByAmrscomplexHandlerId(AmrsComplexHandler handlerId){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersonTypeFields.class).add(
                Expression.eq("amrsHandler", handlerId));

        @SuppressWarnings("unchecked")

        List<AmrsPersonTypeFields>amrscomplexhandlerfields=criteria.list();
        if (null==amrscomplexhandlerfields||amrscomplexhandlerfields.isEmpty()){
            return null;
        }
        return amrscomplexhandlerfields;

    }


    public AmrsPersonType saveAmrsPersonType(AmrsPersonType amrspersontype) {
        // TODO Auto-generated method stub

        sessionFactory.getCurrentSession().saveOrUpdate(amrspersontype);

        return amrspersontype;

    }


    @SuppressWarnings("unchecked")
    public List<AmrsPersonType> getAmrsPersonType() {
        // TODO Auto-generated method stub


        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersonType.class);


        return criteria.list();

    }
    @SuppressWarnings("unchecked")
    public AmrsPersonType getAmrsPersonTypeByUuid(String uuid) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersonType.class).add(
                Expression.eq("uuid", uuid));

        @SuppressWarnings("unchecked")

        List<AmrsPersonType>amrspersontype=criteria.list();
        if (null==amrspersontype||amrspersontype.isEmpty()){
            return null;
        }
        return amrspersontype.get(0);
    }
    public AmrsPersontypeAttributes saveAmrsPersontypeAttributes(AmrsPersontypeAttributes amrspersontypeattributes) {
        // TODO Auto-generated method stub

        sessionFactory.getCurrentSession().saveOrUpdate(amrspersontypeattributes);

        return amrspersontypeattributes;

    }


    @SuppressWarnings("unchecked")
    public List<AmrsPersontypeAttributes> getAmrsPersontypeAttributes() {
        // TODO Auto-generated method stub


        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersontypeAttributes.class);


        return criteria.list();

    }
    @SuppressWarnings("unchecked")
    public AmrsPersontypeAttributes getAmrsPersontypeAttributesByUuid(String uuid) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersontypeAttributes.class).add(
                Expression.eq("uuid", uuid));

        @SuppressWarnings("unchecked")

        List<AmrsPersontypeAttributes>amrspersontypeattributes=criteria.list();
        if (null==amrspersontypeattributes||amrspersontypeattributes.isEmpty()){
            return null;
        }
        return amrspersontypeattributes.get(0);
    }
    public AmrsPersontypeConcept saveAmrsPersontypeConcept(AmrsPersontypeConcept amrspersontypeconcept) {
        // TODO Auto-generated method stub

        sessionFactory.getCurrentSession().saveOrUpdate(amrspersontypeconcept);

        return amrspersontypeconcept;

    }


    @SuppressWarnings("unchecked")
    public List<AmrsPersontypeConcept> getAmrsPersontypeConcept() {
        // TODO Auto-generated method stub


        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersontypeConcept.class);


        return criteria.list();

    }
    @SuppressWarnings("unchecked")
    public AmrsPersontypeConcept getAmrsPersontypeConceptByUuid(String uuid) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersontypeConcept.class).add(
                Expression.eq("uuid", uuid));

        @SuppressWarnings("unchecked")

        List<AmrsPersontypeConcept>amrspersontypeconcept=criteria.list();
        if (null==amrspersontypeconcept||amrspersontypeconcept.isEmpty()){
            return null;
        }
        return amrspersontypeconcept.get(0);
    }
    public AmrsPersontypeHandler saveAmrsPersontypeHandler(AmrsPersontypeHandler amrspersontypehandler) {
        // TODO Auto-generated method stub

        sessionFactory.getCurrentSession().saveOrUpdate(amrspersontypehandler);

        return amrspersontypehandler;

    }


    @SuppressWarnings("unchecked")
    public List<AmrsPersontypeHandler> getAmrsPersontypeHandler() {
        // TODO Auto-generated method stub


        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersontypeHandler.class);


        return criteria.list();

    }
    @SuppressWarnings("unchecked")
    public AmrsPersontypeHandler getAmrsPersontypeHandlerByUuid(String uuid) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersontypeHandler.class).add(
                Expression.eq("uuid", uuid));

        @SuppressWarnings("unchecked")

        List<AmrsPersontypeHandler>amrspersontypehandler=criteria.list();
        if (null==amrspersontypehandler||amrspersontypehandler.isEmpty()){
            return null;
        }
        return amrspersontypehandler.get(0);
    }


    public List<AmrsPersonTypeFields> getFieldsByAmrsPersonType(AmrsPersonType personType){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersonTypeFields.class).add(
                Expression.eq("amrsPersonType", personType));

        @SuppressWarnings("unchecked")

        List<AmrsPersonTypeFields>amrscomplexhandlerfields=criteria.list();
        if (null==amrscomplexhandlerfields||amrscomplexhandlerfields.isEmpty()){
            return null;
        }
        return amrscomplexhandlerfields;

    }




    public List<AmrsPersontypeHandler> getAmrsPersonTypeByHandler(AmrsComplexHandler handlerId){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AmrsPersontypeHandler.class).add(
                Expression.eq("handlerId", handlerId));

        @SuppressWarnings("unchecked")

        List<AmrsPersontypeHandler>personTypeHandlers=criteria.list();
        if (null==personTypeHandlers||personTypeHandlers.isEmpty()){
            return null;
        }
        return personTypeHandlers;

    }


    public AmrsPersonTypeFormFields saveAmrsPersonTypeFormFields(AmrsPersonTypeFormFields amrsPersonTypeFormFields){

        sessionFactory.getCurrentSession().saveOrUpdate(amrsPersonTypeFormFields);

        return amrsPersonTypeFormFields;

    }
    public List<AmrsPersonTypeFormFields> getAmrsPersonTypeFormFieldsByFormPersonType(AmrsPersonType personType, Form form){

       Criteria crit = sessionFactory.getCurrentSession().createCriteria(AmrsPersonTypeFormFields.class);


        if (form != null && personType!= null) {
            Criterion disjunction = Restrictions.disjunction()


                    .add(Restrictions.eq("amrsPersonType", personType))

                    .add(Restrictions.eq("form", form));

            crit.add(disjunction);
        }


        List<AmrsPersonTypeFormFields>personTypeFormFields=crit.list();
        if (null==personTypeFormFields||personTypeFormFields.isEmpty()){
            return null;
        }
        return personTypeFormFields;

    }

	}