package org.openmrs.module.amrscomplexobs.util;

import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrscomplexobs.AMRSComplexObsConstants;
import org.openmrs.module.amrscomplexobs.OpenMRSTableFields;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: alfayo
 * Date: 8/28/13
 * Time: 10:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class AMRSComplexObsUtil {


    /**
     * Returns provider id given username
     *
     * @param userName
     * @return
     */
    public static Integer getProviderId(String userName) {
        User userProvider;
        Person personProvider;

        // assume its a normal user-name or systemId formatted with a dash
        userProvider = Context.getUserService().getUserByUsername(userName);
        if (userProvider != null) {
            return userProvider.getPerson().getPersonId();
        }

        // next assume it is a internal providerId (Note this is a person_id
        // not a user_id) and try again
        try {
            personProvider = Context.getPersonService().getPerson(Integer.parseInt(userName));
            if (personProvider != null) {
                return personProvider.getPersonId();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


        // now assume its a systemId without a dash: fix the dash and try again
        if (userName != null && userName.trim() != "") {
            if (userName.indexOf("-") == -1 && userName.length() > 1) {
                userName = userName.substring(0, userName.length() - 1) + "-" + userName.substring(userName.length() - 1);
                userProvider = Context.getUserService().getUserByUsername(userName);
                if (userProvider != null) {
                    return userProvider.getPerson().getPersonId();
                }
            }
        }

        return null;
    }

    public static String cleanLocationEntry(String householdLocation) {
        householdLocation=householdLocation.trim();
        String locationId=null;
        if(householdLocation.length()>0)  {

            if(householdLocation.length()>2) {
                String lastDec=householdLocation.substring(householdLocation.length()-2);
                if (lastDec.equals(".0")) {
                    locationId=householdLocation.substring(0,householdLocation.length()-2);
                } else {
                    locationId= householdLocation;
                }

            }
            else{
                locationId= householdLocation;
            }

        }

        return   locationId;
    }

    /**
     * Retrieves a patient identifier from a patient form
     *
     * @param doc
     * @return patientIdentifier
     */
    public static String getPatientIdentifier(Document doc) {
        NodeList elemList = doc.getDocumentElement().getElementsByTagName("patient");
        if (!(elemList != null && elemList.getLength() > 0)) {
            return null;
        }

        Element patientNode = (Element) elemList.item(0);

        NodeList children = patientNode.getChildNodes();
        int len = patientNode.getChildNodes().getLength();
        for (int index = 0; index < len; index++) {
            Node child = children.item(index);
            if (child.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if ("patient_identifier".equalsIgnoreCase(((Element) child).getAttribute("openmrs_table"))
                    && "identifier".equalsIgnoreCase(((Element) child).getAttribute("openmrs_attribute"))) {
                return child.getTextContent();
            }
        }

        return null;
    }

    public static Date fromSubmitString2DateTime(String dateTime) throws ParseException {
        String pattern = Context.getAdministrationService().getGlobalProperty(
                AMRSComplexObsConstants.GLOBAL_PROP_KEY_DATE_TIME_SUBMIT_FORMAT, AMRSComplexObsConstants.DEFAULT_DATE_TIME_SUBMIT_FORMAT);
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        if ("yyyy-MM-dd'T'HH:mm:ssZ".equals(pattern))
            dateTime = dateTime.substring(0, 22) + dateTime.substring(23);

        return dateFormat.parse(dateTime);
    }

    public static Date fromSubmitStringToDateTime(String dateTime) throws ParseException {

        dateTime=dateTime.replace("-","/");
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startDate=new Date();
        Date startDate2=new Date();
        try {
            startDate = df.parse(dateTime);
            String newDateString = df.format(startDate);

            startDate2 = df.parse("06/27/2007");

            String newDateString2 = df.format(startDate2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public static Date fromSubmitString2Date(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Context.getAdministrationService().getGlobalProperty(
                AMRSComplexObsConstants.GLOBAL_PROP_KEY_DATE_SUBMIT_FORMAT, AMRSComplexObsConstants.DEFAULT_DATE_SUBMIT_FORMAT));
        return dateFormat.parse(date);
    }

    public static ArrayList<OpenMRSTableFields> getListDefaultNewPersonFields() {
        //build fields for person
        ArrayList<OpenMRSTableFields> listFields=new ArrayList();
        OpenMRSTableFields   familyNameField=new OpenMRSTableFields();
        familyNameField.setOpemrsTable("patient_name");
        familyNameField.setOpemrsTag("otherPerson.family_name") ;
        familyNameField.setOpenmrsAttribute("family_name");
        familyNameField.setFieldCaption("Family Name");
        familyNameField.setUuid(UUID.randomUUID().toString());
        familyNameField.setDefaultValue("$!{lotherPerson.getFamilyName()}");


        OpenMRSTableFields   middleNameField=new OpenMRSTableFields();
        middleNameField.setOpemrsTable("patient_name");
        middleNameField.setOpemrsTag("otherPerson.middle_name") ;
        middleNameField.setOpenmrsAttribute("middle_name");
        middleNameField.setFieldCaption("Middle Name");
        middleNameField.setUuid(UUID.randomUUID().toString());
        middleNameField.setDefaultValue("$!{otherPerson.getMiddleName()}");


        OpenMRSTableFields   givenNameField=new OpenMRSTableFields();
        givenNameField.setOpemrsTable("patient_name");
        givenNameField.setOpemrsTag("otherPerson.given_name") ;
        givenNameField.setOpenmrsAttribute("given_name");
        givenNameField.setFieldCaption("Given Name");
        givenNameField.setUuid(UUID.randomUUID().toString());
        givenNameField.setDefaultValue("$!{otherPerson.getGivenName()}");

        OpenMRSTableFields   genderField=new OpenMRSTableFields();
        genderField.setOpemrsTable("patient");
        genderField.setOpemrsTag("otherPerson.sex ") ;
        genderField.setOpenmrsAttribute("gender");
        genderField.setFieldCaption("Gender");
        genderField.setUuid(UUID.randomUUID().toString());
        genderField.setDefaultValue("$!{otherPerson.getGender()}");

        OpenMRSTableFields   birthDateField=new OpenMRSTableFields();
        birthDateField.setOpemrsTable("patient");
        birthDateField.setOpemrsTag("otherPerson.birthdate") ;
        birthDateField.setOpenmrsAttribute("birthdate");
        birthDateField.setFieldCaption("Birth date");
        birthDateField.setUuid(UUID.randomUUID().toString());
        birthDateField.setDefaultValue("$!{date.format($otherPerson.getBirthdate())}");


        OpenMRSTableFields   phoneField=new OpenMRSTableFields();
        phoneField.setOpemrsTable("patient");
        phoneField.setOpemrsTag("otherPerson.contact_phone_number") ;
        phoneField.setOpenmrsAttribute("Contact Phone Number");
        phoneField.setFieldCaption("Phone Number");
        phoneField.setDefaultValue("$!{otherPerson.getAttribute('Contact Phone Number').getValue()}");
        phoneField.setUuid(UUID.randomUUID().toString());

        OpenMRSTableFields   relationshipField=new OpenMRSTableFields();
        relationshipField.setOpemrsTable("createnewrelationship");
        relationshipField.setOpemrsTag("otherPerson.relationship") ;
        relationshipField.setOpenmrsAttribute("patient_relative");
        relationshipField.setFieldCaption("Relationship");
        relationshipField.setUuid(UUID.randomUUID().toString());

        listFields.add(familyNameField) ;
        listFields.add(middleNameField) ;
        listFields.add(givenNameField) ;
        listFields.add(birthDateField) ;
        listFields.add(genderField) ;
        listFields.add(phoneField) ;

        listFields.add(relationshipField) ;
      return listFields;
    }
}