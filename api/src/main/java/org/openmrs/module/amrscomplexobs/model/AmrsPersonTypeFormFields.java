package org.openmrs.module.amrscomplexobs.model;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;
import org.openmrs.FieldType;
import org.openmrs.Form;

public class AmrsPersonTypeFormFields extends BaseOpenmrsData{



    private Integer id;
    private Form form;

    private AmrsPersonType amrsPersonType;

    public Integer getId() {
        // TODO Auto-generated method stub
        return id;
    }

    public void setId(Integer id) {
        // TODO Auto-generated method stub
        this.id=id;

    }
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public AmrsPersonType getAmrsPersonType() {
        return amrsPersonType;
    }

    public void setAmrsPersonType(AmrsPersonType amrsPersonType) {
        this.amrsPersonType = amrsPersonType;
    }





}