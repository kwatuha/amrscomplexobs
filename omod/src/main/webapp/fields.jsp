<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ taglib prefix="wgt" uri="/WEB-INF/view/module/htmlwidgets/resources/htmlwidgets.tld" %>
<openmrs:require privilege="View Mobile Form Errors" otherwise="/login.htm"
                 redirect="/module/amrscomplexobs/fields.list"/>



<openmrs:htmlInclude file="/scripts/calendar/calendar.js"/>
<openmrs:htmlInclude file="/scripts/dojoConfig.js"/>
<openmrs:htmlInclude file="/scripts/dojo/dojo.js"/>

<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/js/jquery.dataTables.min.js"/>
<openmrs:htmlInclude file="/dwr/interface/DWRAMRSComplexObsService.js"/>
<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables.css"/>
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/css/smoothness/jquery-ui-1.8.16.custom.css"/>
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/css/dataTables_jui.css"/>


<h2><spring:message code="amrscomplexobs.field.title"/></h2>
<style type="text/css">
    .tblformat tr:nth-child(odd) td {
        background-color: #eee;
    }

    .tblformat tr:nth-child(odd) th {
        background-color: #ddd;
    }

    .tblformat tr:nth-child(even) td {
        background-color: #d3d3d3;
    }

    .tblformat tr:nth-child(even) th {
        background-color: #bbb;
    }
</style>
<style type="text/css">
        /* comment and resolve buttons */
    button.action {
        border: 1px solid gray;
        font-size: .75em;
        color: black;
        width: 52px;
        margin: 2px;
        padding: 1px;
        cursor: pointer;
    }

    button.resolve {
        background-color: #E0E0F0;
    }

    button.comment {
        background-color: lightpink;
    }

        /* error table */
    #errors {
        margin: 1em;
    }

    #errors table {
        width: 100%;
    }

    #tools {
        margin: 1em;
    }

    .centered {
        text-align: center;
    }

        /* datatable stuff */
    .dataTables_info {
        font-weight: normal;
    }

    .dataTables_wrapper {
        padding-bottom: 0;
    }

    .ui-widget-header {
        font-weight: inherit;
    }

    .css_right {
        float: right;
    }

    .css_left {
        float: left;
    }

    .dataTables_length {
        width: auto;
    }


    #menulist
      {
          padding: 0 1px 1px;
          margin-left: 0;
          font: bold 12px Verdana, sans-serif;
          background: gray;
          width: 13em;
      }

    #menulist li
    {
        list-style: none;
        margin: 0;
        border-top: 1px solid gray;
        text-align: left;
    }

    #menulist li a
    {
        display: block;
        padding: 0.25em 0.5em 0.25em 0.75em;
        border-left: 1em solid #AAB;
        background: #CCD;
        text-decoration: none;
    }

    #menulist li a:link { color: #448; }
    #menulist li a:visited { color: #667; }

    #menulist li a:hover
    {
        border-color: #FE3;
        color: #FFF;
        background: #332;
    }

</style>

<script>
var eTable = null;


$j(document).ready(function () {

    if (eTable == null) {

        eTable = $j("#openmrsAttributesTable").dataTable({
            bAutoWidth: false,
            bDeferRender: true,
            bJQueryUI: true,
            bPaginate: true,
            sPaginationType: "full_numbers",
            aoColumnDefs: [
                { aTargets: ["_all"], bSortable: false },
                {
                    aTargets: [0],
                    sClass: "centered",
                    mData: null,

                    mRender: function (data, type, full) {
                        var uuid= full.uuid;
                        return '<input name="checkedFieldIDs" type="checkbox" value="' + uuid + '"/>';
                    }
                },

                { aTargets: [1], mData: "id" },
                { aTargets: [2], mData: "name"},
                { aTargets: [3], mData: "attributeTypeId" },
                { aTargets: [4], mData: "description"}
            ],
            bProcessing: true,
            bServerSide: true,
            bStateSave: false,
            fnDrawCallback: function (oSettings) {
                if ($j("span.numSelected").html() == oSettings.fnRecordsDisplay()) {
                    $j("input[name=checkedFieldIDs]").attr("checked", "checked");
                } else {
                    $j("span.numDisplayed").html(oSettings.fnRecordsDisplay());
                    $j("#selectAll").removeAttr("checked");
                    $j("input[name=checkedFieldIDs]").removeAttr("checked");

                }
            },
            sAjaxSource: "<openmrs:contextPath/>/module/amrscomplexobs/fieldList.json"
        });

    }
    else {

        eTable.fnClearTable(0);
        eTable.fnDraw();

    }
    //hide default view
    $j("#addConcept").hide();
    $j("#addOtherAttributes").hide();
    // TODO revise the savePersonAttributes to receive list of attribute uuids to process
    $j("input[name=ShowSelectedFields]").live("click", function () {
        //DO Do
        var params = [];
        $j("input[name=checkedFieldIDs]:checked").each(function () {
            params.push( $j(this).val());
            var attributeTypeUuid=$j(this).val();
            var personTypeUuid=$j("#OtherAttriPersonTypeId").val();
            DWRAMRSComplexObsService.savePersonAttributes(attributeTypeUuid, personTypeUuid, showResponseAttr);

        });
        if(params.length>0) {
            var personTypeUuid=$j("#OtherAttriPersonTypeId").val();
            //DWRAMRSComplexObsService.savePersonAttributes(params, personTypeUuid, showResponse);

        }


    });

});



function clearElements(){

    var textElements = document.getElementsByTagName("input");
    for (var i=0; i < textElements.length; i++) {
        if (textElements[i].type == 'text') {
            textElements[i].value = '';
        }
    }

    var radioElements = document.getElementsByName("errorItemAction");
    var radioLength = radioElements.length;
    var lastRadioPosition = radioLength - 1;

    for (var i=0; i < radioElements.length; i++) {

        if (i != lastRadioPosition) {

            radioElements[i].checked = false;
        }
        radioElements[i].checked = true;
    }




}

    //show



    ////

</script>
<script type="text/javascript">

    (function($j){
        $jcountForms = 1;
        $j.fn.addFormpersonTypeHandler= function(){
            var myform = "<fieldset class='visualPadding'>"+
                    "<legend>Define Person Type Handler</legend><table>"+
                    "<tr><td>Person Type</td>"+
                    "<td><select name='ptPersonTypeId' id='ptPersonTypeId'>"+
                    "<c:forEach var='amrspersonType' items='${listAmrsPersonTypes}' varStatus='ind'>"+
                    "<option id='${amrspersonType.uuid}' value='${amrspersonType.uuid}'>${amrspersonType.personTypeName}</option>"+
                    "</c:forEach>"+
                    "</select></td>"+
                    "</tr>"+
                    "<tr><td>AMRS Handler</td><td><select name='amrsHandlerId' id='ptamrsHandlerId'>"+
                    "<c:forEach var='amrsHandler' items='${listAmrshandler}' varStatus='ind'> "+
                    "<option id='${amrsHandler.uuid}' value='${amrsHandler.uuid}'>${amrsHandler.handlerName}</option> "+
                    "</c:forEach> "+
                    "</select></td></tr>" +
                    "  <tr>"+
                    "     <td>&nbsp</td>"+
                    "     <td><input type='button' value='Save' onclick='savePersonTypeHandler()'/></td>"+
                    "</tr>" +
                    "</table></fieldset>";

            myform = $j("<div>"+myform+"</div>");
            $j("button", $j(myform)).click(function(){ $j(this).parent().parent().remove(); });

            $j(this).append(myform);
            $jcountForms++;
        };
    })(jQuery);

    $j(function(){
        $j("#personTypeHandler").bind("click", function(){
            $j("#addConcept").hide();
            $j("#addOtherAttributes").hide();
            $j("#showHtml").empty();
            $j("#showHtml").addFormpersonTypeHandler();
        });
    });

    (function($j){
        $jcountForms = 1;
        $j.fn.addFormsRelatedPerson = function(){
            var myform = "<fieldset class='visualPadding'>"+
                    "<legend>Define Related Person</legend>"+
                    "<table>"+
                    "<tr><td>Person Type</td>"+
                    "<td><select name='paPersonTypeId' id='paPersonTypeId'>"+
                    "<c:forEach var='amrspersonType' items='${listAmrsPersonTypes}' varStatus='ind'>"+
                    "<option id='${amrspersonType.uuid}' value='${amrspersonType.uuid}'>${amrspersonType.personTypeName}</option>"+
                    "</c:forEach>"+
                    "</select></td>"+
                   "</tr>"+


                            "<c:forEach var='personField' items='${listNewPersonFields}' varStatus='ind'> "+
                            "<tr>"+
                                "<td><input checked='true' type='checkbox' name='personfieldcheckbox' value='${personField.opemrsTag}' />${personField.fieldCaption}</td>"+
                                "<td><input type='text' readonly='true' id='${personField.opemrsTable}' value='${personField.opemrsTable}' /></td>"+
                                "<td><input type='text' readonly='true' id='${personField.opemrsTag}' value='${personField.opemrsTag}' /></td>"+
                                 "<td><input type='text' readonly='true' id='${personField.openmrsAttribute}' value='${personField.openmrsAttribute}' /></td>"+
                                "<td><textarea   readonly='true' id='${personField.defaultValue}'>${personField.defaultValue}</textarea></td>"+
                            "</tr>" +

                           "</c:forEach>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td></td>"+
                                "<td></td>"+
                                "<td></td>"+
                                "<td></td>"+
                                "</tr>"+

                   "</table> "+
                   "</fieldset>";
            myform = $j("<div>"+myform+"</div>");
            $j("button", $j(myform)).click(function(){ $j(this).parent().parent().remove(); });

            $j(this).append(myform);
            $jcountForms++;
        };
    })(jQuery);

    $j(function(){
        $j("#newRelatedPersonAttributes").bind("click", function(){
            $j("#addConcept").hide();
            $j("#showHtml").empty();
            $j("#showHtml").addFormsRelatedPerson();
        });
    });


    //new person fields

     //"<tr><td><input type='button' id='submitpersonfields' value='Save New Person Fields' /></td><td></td><td></td><td></td><td></td></tr>"+

    function saveHandlerFields() {
        var conceptName=$j("#conceptSearchTextField").val();
        var selectMultiple=$j("#select_multiple").val();
        var description=$j("#field_description").val();
        var defaultValue=$j("#default_value").val();
        var attributeName=$j("#attribute_name").val();
        var fieldType=$j('input[name=fieldTypeName]:checked', '#complexConceptFieldsform').val();
        var fieldName=$j("#field_name").val();
        var tableName=$j("#tableName").val();
        var personType=$j("#cmpPersonTypeId").val();


        if(fieldType>0){

         DWRAMRSComplexObsService.saveFormFields(fieldType,conceptName,tableName,attributeName,defaultValue,selectMultiple,fieldName,personType,description, showResponse);
        }
   }

    function showResponse(responseStr){
     alert(responseStr) ;
     }

    function showResponseAttr(responseStr){

    }


    $j(function(){
        $j("#personTypeConcepts").bind("click", function(){
            $j("#showHtml").empty();
            $j("#addOtherAttributes").hide();
             $j("#addConcept").show();
        });
    });

    $j(function(){
        $j("#personTypeOtherAttributes").bind("click", function(){
            $j("#showHtml").empty();
            $j("#addConcept").hide();
            $j("#addOtherAttributes").show();
        });
    });

    $j(function(){
        $j("#submitnewpersonfields").bind("click", function(){


             var tableFieldsList = [];
            var personTypeUuid=$j("#paPersonTypeId").val();
             $j("input[name=personfieldcheckbox]:checked").each(function(){
             tableFieldsList.push($j(this).val());
                // alert($j(this).val());
                 DWRAMRSComplexObsService.saveNewPersonFields($j(this).val(), personTypeUuid, showResponse);
             });


             var personType='Neighbor' ;
             if(tableFieldsList.length>0){
               //  alert("Here in") ;
            // DWRAMRSComplexObsService.saveNewPersonFields(tableFieldsList, handlerName, showResponse);
             }
             //


        });
    });





    //New person type

    (function($j){
        $jcountForms = 1;
        $j.fn.addFormsPersonType = function(){
            var myform = "<fieldset class='visualPadding'>"+
                    "<legend>Define Person Type</legend><table>"+
                    "  <tr>"+
                    "     <td>Person Type Name</td>"+
                    "     <td><input type='text' id='amrs_person_type'></td>"+
                    "</tr>" +
                    "  <tr>"+
                    "     <td>Description</td>"+
                    "     <td><textarea id='amrs_person_type_description'/></td>"+
                    "</tr>" +

                    "  <tr>"+
                    "     <td>&nbsp</td>"+
                    "     <td><input type='button' value='Save PersonType' onclick='savePersonType()'/></td>"+
                    "</tr>" +

                    "</table></fieldset>";

            myform = $j("<div>"+myform+"</div>");
            $j("button", $j(myform)).click(function(){ $j(this).parent().parent().remove(); });

            $j(this).append(myform);
            $jcountForms++;
        };
    })(jQuery);

    $j(function(){
        $j("#newPersonTypeLink").bind("click", function(){
            $j("#addConcept").hide();
            $j("#showHtml").empty();
            $j("#addOtherAttributes").hide();
            $j("#showHtml").addFormsPersonType();
        });
    });

function showpersontype(){
    alert('Cliscked') ;
}

    //New handler

    (function($j){
        $jcountForms = 1;

        $j.fn.addFormsHandler = function(){
            var myform = "<fieldset class='visualPadding'>"+
                    "<legend>Define Handler</legend><table>"+
                    "  <tr>"+
                    "     <td>Handler Name</td>"+
                    "     <td><input type='text' id='amrscomplexhandler'></td>"+
                    "</tr>" +
                    "  <tr>"+
                    "     <td>&nbsp</td>"+
                    "     <td><input type='button' value='Save' onclick='saveAMRSHandler()'></td>"+
                    "</tr>" +
                    "</table></fieldset>";

            myform = $j("<div>"+myform+"</div>");
            $j("button", $j(myform)).click(function(){ $j(this).parent().parent().remove(); });

            $j(this).append(myform);
            $jcountForms++;
        };
    })(jQuery);

    $j(function(){
        $j("#newhandlerLink").bind("click", function(){
            $j("#addConcept").hide();
            $j("#showHtml").empty();
            $j("#addOtherAttributes").hide();
            $j("#showHtml").addFormsHandler();
        });
    });

    function saveAMRSHandler(){

        var amrscomplexhandler=$j("#amrscomplexhandler").val();
        if(amrscomplexhandler){

            DWRAMRSComplexObsService.saveAmrscomplexconcepthandler(amrscomplexhandler, showResponse);
        }

    }

    function savePersonType(){

        var persontypename=$j("#amrs_person_type").val();
        var description=$j("#amrs_person_type_description").val();
        if(persontypename){

            DWRAMRSComplexObsService.saveAmrsPersonType(persontypename,description,showResponse);
        }

    }

    function savePersonTypeHandler(){

        var handlerUuid=$j("#ptamrsHandlerId").val();
        var ptPersonTypeUuid=$j("#ptPersonTypeId").val();
        if(ptPersonTypeId){

            DWRAMRSComplexObsService.saveAmrsPersontypeHandler(handlerUuid,ptPersonTypeUuid,showResponse);
        }

    }
</script>
</head>


<form method="POST" id="complexConceptFieldsform">
<input name="submitFields" type="button" value="Submit fields"/>

<input name="handlerPerson" type="button" value="Show person table"/>
<input name="ShowSelectedFields" type="button" value="Save this Person Attributes"/>
<input type='button' id='submitnewpersonfields' value='Save New Person Fields' />
<table>

    <tr><td>
        <div id="navcontainer">
            <ul id="menulist">

                <li><a href="#" id="newhandlerLink">New Handler</a></li>
                <li><a href="#" id="newPersonTypeLink">Add Person Type</a></li>
                <li><a href="#" id="newRelatedPersonAttributes">Person Type OpenMRS Attributes</a></li>
                <li><a href="#" id="personTypeConcepts">Person Type Concepts</a></li>
                <li><a href="#" id="personTypeHandler">Person Type Handler</a></li>
                <li><a href="#" id="personTypeOtherAttributes">Other Attributes</a></li>

            </ul>
        </div>
    </td><td>

        <div id="addConcept">
            <fieldset class="visualPadding">
                <legend>Add Concept</legend>


                <table>

                    <tr><td>Field Type</td><td><c:forEach var="fieldTypes" items="${listFieldTypes}">

                        <input type="radio" name="fieldTypeName" value="${fieldTypes.id}" /> ${fieldTypes.name}

                    </c:forEach></td></tr>
                    <tr><td>AMRS Handler</td><td><select name="amrsHandlerId" id="amrsHandlerId">
                        <c:forEach var="amrsHandler" items="${listAmrshandler}" varStatus="ind">
                            <option id="${amrsHandler.uuid}" value="${amrsHandler.uuid}">${amrsHandler.handlerName}</option>
                        </c:forEach>
                    </select></td></tr>

                    <tr><td>Person Type</td>
                        <td><select name="cmpPersonTypeId" id="cmpPersonTypeId">
                        <c:forEach var="amrspersonType" items="${listAmrsPersonTypes}" varStatus="ind">
                            <option id="${amrspersonType.uuid}" value="${amrspersonType.uuid}">${amrspersonType.personTypeName}</option>
                        </c:forEach>
                        </select></td>
                    </tr>

                    <tr><td>Select Concept</td><td><wgt:widget id="conceptSearch" name="concept" type="org.openmrs.Concept"/></td></tr>
                    <tr><td>Field Name</td><td><input type="text" id="field_name" name="field_name"/></td></tr>
                    <tr><td>Attribute Name</td><td><input type="text" id="attribute_name" name="attribute_name"/></td></tr>
                    <tr><td>Default Value</td><td><input type="text" id="default_value" name="default_value"/></td></tr>
                    <tr><td>Multiple</td><td><input type="checkbox" id="select_multiple" name="select_multiple" /></td></tr>
                    <tr><td>Description</td><td><textarea id="field_description" name="description"> </textarea></td></tr>


                    <tr><td>&nbsp</td><td><input id="savedata" type="button" value="Save" onclick="saveHandlerFields()"></td></tr>
                </table>


            </fieldset>

        </div>

        <div id="addOtherAttributes">
            <div><b class="boxHeader">Field Attributes</b>

                <div class="box">
                    <div id="tools">
                        Person Type <select name="personTypeId" id="OtherAttriPersonTypeId">
                        <c:forEach var="amrspersonType" items="${listAmrsPersonTypes}" varStatus="ind">
                            <option id="${amrspersonType.uuid}" value="${amrspersonType.uuid}">${amrspersonType.personTypeName}</option>
                        </c:forEach>
                    </select>
                    </div>

                    <div id="errors">
                        <form method="post">

                            <table id="openmrsAttributesTable" cellpadding="8" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Select</th>

                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Attribute Type Id</th>
                                    <th>Description</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div style="left:100 " id="showHtml">
              </div></td></tr>
</table>

</form>

<%@ include file="/WEB-INF/template/footer.jsp" %>
