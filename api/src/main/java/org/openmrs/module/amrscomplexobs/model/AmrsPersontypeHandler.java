package org.openmrs.module.amrscomplexobs.model;
import org.openmrs.BaseOpenmrsData;

import java.util.Date;
public class AmrsPersontypeHandler extends BaseOpenmrsData{

private  AmrsComplexHandler handlerId;
private  AmrsPersonType personTypeId;
private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AmrsComplexHandler getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(AmrsComplexHandler handlerId) {
        this.handlerId = handlerId;
    }

    public AmrsPersonType getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(AmrsPersonType personTypeId) {
        this.personTypeId = personTypeId;
    }



}