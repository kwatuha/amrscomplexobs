package org.openmrs.module.amrscomplexobs.model;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Program;

import java.util.Date;
public class ComplexConceptFields extends BaseOpenmrsData{

private  Integer conceptId;
private  Integer formId;
private  String fieldName;

private Integer id;
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id=id;
		
	}
	
		public Integer   getConceptId() {
		
		// TODO Auto-generated method stub
		return  conceptId;
	    }
		public  void   setConceptId (Integer  conceptid ) {
		
		// TODO Auto-generated method stub
		 this.conceptId=conceptid;
		 
	    }
		public Integer   getFormId() {
		
		// TODO Auto-generated method stub
		return  formId;
	    }
		public  void   setFormId (Integer  formid ) {
		
		// TODO Auto-generated method stub
		 this.formId=formid;
		 
	    }
		public String   getFieldName() {
		
		// TODO Auto-generated method stub
		return  fieldName;
	    }
		public  void   setFieldName (String  fieldname ) {
		
		// TODO Auto-generated method stub
		 this.fieldName=fieldname;
		 
	    }
}