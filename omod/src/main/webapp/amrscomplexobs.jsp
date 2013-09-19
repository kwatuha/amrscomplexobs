<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<%@ taglib prefix="wgt" uri="/WEB-INF/view/module/htmlwidgets/resources/htmlwidgets.tld" %>
<openmrs:require privilege="Manage Complexobshandler" otherwise="/login.htm"
	redirect="/module/complexobshandler/amrscomplexobs.form" />
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/js/jquery.dataTables.min.js" />
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/js/jquery.tools.min.js" />
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/TableTools/js/TableTools.min.js" />
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/TableTools/js/ZeroClipboard.js" />


<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables.css" />
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/css/smoothness/jquery-ui-1.8.16.custom.css" />
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/css/dataTables_jui.css" />
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/TableTools/css/TableTools.css" />
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/TableTools/css/TableTools_JUI.css" />

<style>
	#layer1 
	{
		position: absolute;
		width:300px;
		background-color:#f0f5FF;
		border: 1px solid #000;
		z-index: 50;
		vertical-align:middle;
	}
	#layer1_handle 
	{
		background-color:#5588bb;
		padding:2px;
		text-align:center;
		font-weight:bold;
		color: #FFFFFF;
		vertical-align:middle;
	}
	#layer1_content 
	{
		padding:5px;
	}
	#close
	{
		float:right;
		text-decoration:none;
		color:#FFFFFF;
	}
</style>
<script type="text/javascript">
    var $j= jQuery.noConflict();
    $j(document).ready(function(){

        var ti = $j('#tablehistory').dataTable({
            "bJQueryUI":false,
            "sPaginationType": "full_numbers",
            "sDom": 'T<"clear">lfrtip',
            "oTableTools": {
                "sRowSelect": "single",
                "aButtons": [
                    "print"
                ]
            }
        });

        var columns = $j('#tablehistory thead tr th').map(function() {

            return $j(this).text();
        });
        columns.splice(0,1);

        $j('#tablehistory').delegate('tbody td #img','click', function() {
            var trow=this.parentNode.parentNode;
            var aData2 = ti.fnGetData(trow);

            $j("#dlgData").empty();
            generate_table(aData2,"dlgData",columns);

            $j("#dlgData").dialog("open");

            return false;
        });



        $j("#dlgData" ).dialog({
            autoOpen:false,
            modal: true,
            show: 'slide',
            height: 'auto',
            hide: 'slide',
            width:600,
            cache: false,
            position: 'middle',
            buttons: {
                "Close": function () { $j(this).dialog("close"); }
            }
        });


        $j('#xlsdownload').click(function() {
            window.open("downloadxls.htm?reportId=${report.id}", 'Download Excel File');
            return false;
        });
    });



    function generate_table(data,bodyDiv,columns) {

        var body = document.getElementById(bodyDiv);
        var tbl     = document.createElement("table");

        tbl.setAttribute('cellspacing','2');
        tbl.setAttribute('border','0');
        tbl.setAttribute('width','100%');
        tbl.setAttribute('class','tblformat');

        tbl.setAttribute('id','tblSummary');


        var tblBody = document.createElement("tbody");

        for(var i=0;i<columns.length;i++){
            tblBody.appendChild(buildRow(columns[i],data[i+1]));

        }

        tbl.appendChild(tblBody);

        body.appendChild(tbl);

    }

    function buildRow(label,tdvalue){

        var row = document.createElement("tr");
        var cell = document.createElement("th");
        cell.setAttribute('align','right');
        var cell2 = document.createElement("td");
        var celllabel = document.createTextNode(label+": ");
        var cellval = document.createTextNode(tdvalue);
        cell.appendChild(celllabel);
        cell2.appendChild(cellval);
        row.appendChild(cell);
        row.appendChild(cell2);
        return row;
    }

    function clearDataTable(){

        dwr.util.removeAllRows("tbodydata");
        var hidepic= document.getElementById("maindetails");
        var titleheader=document.getElementById("titleheader");
        hidepic.style.display='none';
        titleheader.style.display='none';



    }

    function opendialogbox(){
        $j("#dlgData").dialog("open");

    }

    // click event for individual checkboxes
    $j("input[name=selFields]").live("click", function () {
        //updateNumSelected();
        checkedElements();

    });

    function updateNumSelected(cntCheckedFields) {
        var numSelected = (typeof cntCheckedFields === "undefined") ? $j("input[name=selFields]:checked").length : cntCheckedFields;
         //alert(numSelected);
        $j("span.numSelected").html(numSelected);

        if (numSelected > 0)
            $j("#reprocessAll").removeAttr("disabled");
        else
            $j("#reprocessAll").attr("disabled", "disabled");


    }


        /*$j('#ShowSelectedFields').click(function(){
            checkedElements();

        });*/

    $j("input[name=ShowSelectedFields]").live("click", function () {
        //updateNumSelected();
        checkedElements();

    });


    function checkedElements(){
        getSelectedConcept();

        alert("Using the type==="+getValue('conceptSearch.value'));

        /*var textElements = document.getElementsByTagName("input");
        for (var i=0; i < textElements.length; i++) {
            if (textElements[i].type == 'checkbox') {
                if(textElements[i].checked){alert (textElements[i].value); }  else{alert("NNNNNNNNNNNNNNNNNNNNNN")}
                getSelectedConcept();
            }
        }*/
    }


    function getSelectedConcept() {
        alert("First hidden="+document.getElementById("conceptSearchTextField").value) ;
        alert("second ="+ $j("#conceptSearchTextField").val());

    }

    $j(function() {
        getField('conceptSearch.value').change(function() {
            window.alert('Provider is ' + getValue('conceptSearch.value'))
        });
    });
    $j(function() {
        getField('conceptSearch').change(function() {
            window.alert('Provider is 2==' + getValue('conceptSearch'))
        });
    });
</script>
<h1>Amrscomplexobs </h1>
<form method="POST" id="amrscomplexobsform">
    <div id="tools">
        <input id="selectAll" type="checkbox"/> Select All <span class="numDisplayed"></span> in Search Results
        (including unseen)
        &nbsp; &nbsp;
        <button id="reprocessAll" disabled>Reprocess <span class="numSelected">0</span> Selected Errors</button>
        ShowSelectedFields
  </div>
    <div id="fieldSearchDiv">

        Find missing concepts<div dojoType="FieldSearch" widgetId="fieldSearch" searchLabel='Find Field Elements' showHeaderRow="false" alsoSearchConcepts="true"></div>

    </div>
    <table border="1">
        <tr><td>Handler Name </td><td><input type="text" id="handlername" name="handlername"> </td></tr>
        <tr><td>Description </td><td><textarea id="handlerdescription" name="handlerdescription"></textarea></td></tr>
        <tr><th><tr><td>&nbsp;</td><td><input type="submit" id="amrscomplexobsformsubmit"name="amrscomplexobsformsubmit" value="Submit"></td></tr></table></form>

<table cellpadding="5" width="100%" id="tablehistory">
    <thead>
    <tr>
        <th class="tdClass">view</th>
        <th class="tdClass">Name</th>
        <th class="tdClass">Select</th>
        <th class="tbClass">Description </th>
        <th class="tbClass">Action </th>

    </tr></thead>
    <tbody id="tbodydata">
    <c:forEach var="complexHandlerTypes"  items="${listAmrscomplexobs}" varStatus="encIndex" >
        <form method="POST" name="${complexHandlerTypes.uuid}">
            <tr>
                <td><img src="${pageContext.request.contextPath}/moduleResources/amrscomplexobs/images/format-indent-more.png" id="img" /></td>
                <td class="tbClass"><input value="${complexHandlerTypes.uuid}" type="checkbox" name="selFields"> </td>
                <td class="tbClass">${complexHandlerTypes.handlerName}</td>
                <td class="tbClass">${complexHandlerTypes.handlerDescription}</td>
                <td class="tdClass"><input type="hidden" name="amrscomplexobsbl" id="amrscomplexobsbl" value="${complexHandlerTypes.uuid}" />
                    <input type="submit" name="EditcomplexConceptFields" value="Edit" /> &nbsp;
                    <input type="submit" name="voidcomplexConceptFields"  value="Void" />&nbsp;
                 </td>
            </tr>
        </form>

    </c:forEach>
    </tbody>
</table>
</form>
<div id="dlgData" title="Patient Information"></div>
<input type="button" onclick="opendialogbox()" value="Show div" />
search <wgt:widget id="conceptSearch" name="concept" type="org.openmrs.Concept"/>
<%@ include file="/WEB-INF/template/footer.jsp"%>