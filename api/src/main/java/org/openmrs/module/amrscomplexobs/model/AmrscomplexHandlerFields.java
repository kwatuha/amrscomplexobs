package org.openmrs.module.amrscomplexobs.model;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.FieldType;
import org.openmrs.Program;

import java.util.Date;
public class AmrscomplexHandlerFields extends BaseOpenmrsData{

    private String attributeName;

    private String defaultValue;

    private Boolean selectMultiple = false;
    private int id;

    private  String fieldLocalName;
    private  String tableName;

    private FieldType fieldType;

    private Concept concept;

    private Amrscomplexconcepthandler amrsHandler;

    private  String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }


							public Integer getId() {
            // TODO Auto-generated method stub
            return id;
        }

        public void setId(Integer id) {
            // TODO Auto-generated method stub
            this.id=id;

        }
        public Amrscomplexconcepthandler getAmrsHandler() {
            return amrsHandler;
        }

        public void setAmrsHandler(Amrscomplexconcepthandler amrsHandler) {
            this.amrsHandler = amrsHandler;
        }


        public Boolean getSelectMultiple() {
            return selectMultiple;
        }

        public void setSelectMultiple(Boolean selectMultiple) {
            this.selectMultiple = selectMultiple;
        }

        public FieldType getFieldType() {
            return fieldType;
        }

        public void setFieldType(FieldType fieldType) {
            this.fieldType = fieldType;
        }

        public Concept getConcept() {
            return concept;
        }

        public void setConcept(Concept concept) {
            this.concept = concept;
        }

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }


        
        
        public String   getFieldLocalName() {
		
		// TODO Auto-generated method stub
		return  fieldLocalName;
	    }
		public  void   setFieldLocalName (String  fieldlocalname ) {
		
		// TODO Auto-generated method stub
		 this.fieldLocalName=fieldlocalname;
		 
	    }
		public String   getTableName() {
		
		// TODO Auto-generated method stub
		return  tableName;
	    }
		public  void   setTableName (String  tablename ) {
		
		// TODO Auto-generated method stub
		 this.tableName=tablename;
		 
	    }
}