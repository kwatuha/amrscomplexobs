package org.openmrs.module.amrscomplexobs.model;
import org.openmrs.BaseOpenmrsData;
public class AmrsPersontypeAttributes extends BaseOpenmrsData{

    private  AmrsPersonType personTypeId;
    private  Integer openmrsAttributeId;

    private Integer id;

    public Integer getOpenmrsAttributeId() {
        return openmrsAttributeId;
    }

    public void setOpenmrsAttributeId(Integer openmrsAttributeId) {
        this.openmrsAttributeId = openmrsAttributeId;
    }

    public AmrsPersonType getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(AmrsPersonType personTypeId) {
        this.personTypeId = personTypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}