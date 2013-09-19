package org.openmrs.module.amrscomplexobs.model;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.FieldType;

public class AmrsPersonTypeFields extends BaseOpenmrsData{

    private String attributeName;

    private String defaultValue;

    private Boolean selectMultiple = false;

    private Integer id;

    private  String fieldLocalName;

    private  String tableName;

    private FieldType fieldType;

    private Concept concept;

    private AmrsPersonType amrsPersonType;

    private  String description;

        public AmrsPersonType getAmrsPersonType() {
            return amrsPersonType;
        }

        public void setAmrsPersonType(AmrsPersonType amrsPersonType) {
            this.amrsPersonType = amrsPersonType;
        }

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