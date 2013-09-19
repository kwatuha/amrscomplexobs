package org.openmrs.module.amrscomplexobs.aop;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.*;
import org.openmrs.api.FormService;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.amrscomplexobs.service.AmrscomplexobsService;
import org.openmrs.module.amrscomplexobs.util.AMRSComplexObsUtil;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import  org.openmrs.module.amrscomplexobs.AMRSComplexObsConstants;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.apache.commons.io.IOUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.openmrs.module.amrscomplexobs.model.Amrscomplexobs;
public class ProcessObs extends StaticMethodMatcherPointcutAdvisor implements Advisor {

    private static final long serialVersionUID = 3333L;
    private DocumentBuilder db;
       private static final Log log = LogFactory.getLog(ProcessObs.class);
    private static final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder docBuilder;
    private XPathFactory xPathFactory;


    public boolean matches(Method method, Class targetClass) {
        // only 'run' this advice on the getter methods
         if (method.getName().startsWith("getSubmittedXformDo"))
            return true;

        return false;
    }

    @Override
    public Advice getAdvice() {
        log.debug("Getting new around advice");
        return new PrintingAroundAdvice();
    }

    private class PrintingAroundAdvice implements MethodInterceptor {
        public Object invoke(MethodInvocation invocation) throws Throwable {

            log.debug("Before " + invocation.getMethod().getName() + ".");


            Map handlerDocumentMap = new HashMap<String, Document>();

           Object o=invocation.proceed();

            String formData = (String)o;
            docBuilder = docBuilderFactory.newDocumentBuilder();
            XPathFactory xpf = getXPathFactory();
            XPath xp = xpf.newXPath();
            Document doc = docBuilder.parse(IOUtils.toInputStream(formData));

            NodeList nodeList = doc.getElementsByTagName("obs");
            Integer ln=nodeList.getLength()  ;

            showResultsR(formData ) ;

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.hasAttributes()) {
                    Attr attr = (Attr) node.getAttributes().getNamedItem("openmrs_field_uuid");
                    if (attr != null) {
                        String attribute= attr.getValue();
                        System.out.println("attribute: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + attribute+"\n");
                    }
                }
            }
            /////////////////////

            //////////////////////////////////////////////////
            return o;
        }
    }



    /////////////////////////////////////////////////////////////

    public  void showResultsR(String xml ) throws UnsupportedEncodingException, ParserConfigurationException, SAXException, IOException, XPathExpressionException,ParseException {

       final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        final XPath xPath = XPathFactory.newInstance().newXPath();

        //c27b9488-7f91-4dd7-b354-379ec2c5d205

        AmrscomplexobsService amrscomplexobsService= Context.getService(AmrscomplexobsService.class) ;
        FormService formService=Context.getFormService();

        List<Field> listComplexConceptFields=amrscomplexobsService.getComplexConceptFieldUuids();
         Integer cml=listComplexConceptFields.size();
        String providerUsername;
        Integer providerId;
        String locationId;
        String encounterDate;

        if(listComplexConceptFields.size()>0) {
              for (int n = 0; n < listComplexConceptFields.size(); ++n) {
                 String fieldUuid = listComplexConceptFields.get(n).getUuid();
                  Concept complexConcept=listComplexConceptFields.get(n).getConcept();

                  //search form xml by field uuid
                  final XPathExpression expression = xPath.compile("//*[@openmrs_field_uuid='"+fieldUuid+"']");
                  final NodeList nodeListByFuid = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);


                  //Prepare fields for storage
                  Amrscomplexobs amrsComplexobs =new Amrscomplexobs();
                  String complexConceptData=null;
                  Provider provider =new Provider();
                  Location location =new Location();

                  ////

                  Node curNode=(Node)  xPath.evaluate(AMRSComplexObsConstants.ENCOUNTER_NODE, doc, XPathConstants.NODE);
                  providerUsername = xPath.evaluate(AMRSComplexObsConstants.ENCOUNTER_PROVIDER, curNode);
                  providerUsername=providerUsername.trim();
                  providerId=AMRSComplexObsUtil.getProviderId(providerUsername);

                  //Clean location id by removing decimal points
                  locationId= AMRSComplexObsUtil.cleanLocationEntry(xPath.evaluate(AMRSComplexObsConstants.ENCOUNTER_LOCATION, curNode)) ;
                  encounterDate=xPath.evaluate(AMRSComplexObsConstants.ENCOUNTER_ENCOUNTERDATETIME, curNode);

                  curNode=(Node)  xPath.evaluate(AMRSComplexObsConstants.PATIENT_NODE, doc, XPathConstants.NODE);
                  String patientIdentifier = xPath.evaluate(AMRSComplexObsConstants.PATIENT_ID, curNode);
                  String formIdStr=xPath.evaluate("/form/@id", doc);
                  String formName=xPath.evaluate("/form/@name", doc);
                  String formName2=xPath.evaluate("/form/@version", doc);


                  int dl=encounterDate.length();
                  Date convertedEncounterDate=AMRSComplexObsUtil.fromSubmitString2Date(encounterDate);
                  provider.setId(providerId);
                  location.setId(Integer.parseInt(locationId));
                  ///
                  amrsComplexobs.setConcept(complexConcept);
                  amrsComplexobs.setLocation(location);
                  amrsComplexobs.setProvider(provider);
                  amrsComplexobs.setFormId(formIdStr);
                  amrsComplexobs.setPatient( Context.getPatientService().getPatient(Integer.parseInt(patientIdentifier)));
                  amrsComplexobs.setEncounterDatetime(convertedEncounterDate);
                  Integer lnfu= nodeListByFuid.getLength()  ;
                  for (int i = 0; i < nodeListByFuid.getLength(); ++i) {
                      System.out.println(((Element)nodeListByFuid.item(i)).getAttribute("openmrs_field_uuid"+"\n"));

                      String subFieldUuid=((Element)nodeListByFuid.item(i)).getAttribute("openmrs_field_uuid");
                      Field subField=Context.getFormService().getFieldByUuid(subFieldUuid);
                      String subFieldValue=(nodeListByFuid.item(i)).getFirstChild().getNodeValue();


                      System.out.println((nodeListByFuid.item(i)).getFirstChild().getNodeValue()+"==OPSSSS");


                      NodeList NodeListChild= nodeListByFuid.item(i).getChildNodes();
                      Integer nchilds=  NodeListChild.getLength();
                      for (int x = 0; x < NodeListChild.getLength(); ++x) {
                          Node node = NodeListChild.item(x);

                          if (node.hasAttributes()) {
                              Attr attr = (Attr) node.getAttributes().getNamedItem("openmrs_field_uuid");
                              if (attr != null) {
                                  String attribute= attr.getValue();
                                  System.out.println("attribute: MMMMMM" + attribute+ "AND VALUD" +node.getTextContent()+ "\n");
                                  complexConceptData= node.getTextContent();
                                  amrsComplexobs.setConceptData(complexConceptData);
                                  amrscomplexobsService.saveAmrscomplexobs(amrsComplexobs);
                              }

                          }





                      }

               }


        }





        }
    }

    /**
     * @return XPathFactory to be used for obtaining data from the parsed XML
     */
    private XPathFactory getXPathFactory() {
        if (xPathFactory == null) {
            xPathFactory = XPathFactory.newInstance();
        }
        return xPathFactory;
    }

    private List<Amrscomplexobs> findAvailableComplexConcepts(String formdata){
            List<Amrscomplexobs>listHandlers=new ArrayList<Amrscomplexobs>()  ;


            return  listHandlers;

    }
}