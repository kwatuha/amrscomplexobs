package org.openmrs.module.amrscomplexobs.model;
import org.openmrs.BaseOpenmrsData;

public class AmrsComplexHandler extends BaseOpenmrsData{

private  String handlerName;

private Integer id;
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id=id;
		
	}
	
		public String   getHandlerName() {
		
		// TODO Auto-generated method stub
		return  handlerName;
	    }
		public  void   setHandlerName (String  handlername ) {
		
		// TODO Auto-generated method stub
		 this.handlerName=handlername;
		 
	    }
}