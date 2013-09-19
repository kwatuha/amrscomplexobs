package org.openmrs.module.amrscomplexobs.model;
import org.openmrs.BaseOpenmrsData;
import org.openmrs.Concept;

import java.util.Date;
public class AmrsPersontypeConcept extends BaseOpenmrsData{



private AmrsPersonType personTypeId;
private Concept conceptId;
private Integer id;

	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id=id;
		
	}

    public AmrsPersonType getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(AmrsPersonType personTypeId) {
        this.personTypeId = personTypeId;
    }

    public Concept getConceptId() {
        return conceptId;
    }

    public void setConceptId(Concept conceptId) {
        this.conceptId = conceptId;
    }
	

}