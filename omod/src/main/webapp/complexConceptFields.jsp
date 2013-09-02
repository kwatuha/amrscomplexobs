<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<openmrs:require privilege="Manage Complexobshandler" otherwise="/login.htm"
	redirect="/module/amrscomplexobs/complexConceptFields.form" />
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
</script>
<h1>Complex Concept Fields </h1>
<form method="POST" id="complexConceptFieldsform">
<table border="1">
<tr><th><tr><td>Concept Id </td><td><input type="text" id="conceptid" name="conceptid"> </td></tr>
<tr><td>Form Id </td><td><input type="text" id="formid" name="formid"> </td></tr>
<tr><td>Field Name </td><td><input type="text" id="fieldname" name="fieldname"> </td></tr>




    <tr><td>Forms</td><td><select name="form">
        <c:forEach var="frm" items="${listForms}" varStatus="ind">
            <option id="${frm.uuid}" value="${frm.name}">${frm.name}</option>
        </c:forEach>
    </select></td></tr>

    <tr><td>Forms</td><td><select name="formFields">
        <c:forEach var="fFields" items="${listFieldTypes}" varStatus="ind">
            <option id="${fFields.uuid}" value="${fFields.name}">${fFields.name}</option>
        </c:forEach>
    </select></td></tr>

    <tr><td>Concepts </td><td><select name="concepts">
        <c:forEach var="concept" items="${listConcepts}" varStatus="ind">
            <option id="${concept.uuid}" value="${concept.name}">${concept.name}</option>
        </c:forEach>
    </select></td></tr>




<tr><td>&nbsp;</td><td><input type="submit" id="complexConceptFieldsformsubmit"name="complexConceptFieldsformsubmit" value="Submit"></td></tr>


</table></form>





<table cellpadding="5" width="100%" id="tablehistory">
          <thead>
					<tr>
 <th class="tdClass">view</th>
<th class="tdClass">Num</th>
<th class="tbClass">Concept Id </th>
<th class="tbClass">Form Id </th>
<th class="tbClass">Field Name </th>

<th class="tdClass">Action</th>
</tr></thead>
<tbody id="tbodydata">
					<c:forEach var="complexConceptFieldsvar"  items="${listComplexConceptFields}" varStatus="encIndex" >
						<form method="POST" name="${complexConceptFieldsvar.uuid}">
						<tr>
                        <td><img src="${pageContext.request.contextPath}/moduleResources/amrscomplexobs/images/format-indent-more.png" id="img" /></td>
						<td class="tdClass">${encIndex.index + 1}</td><td class="tbClass">${complexConceptFieldsvar.conceptId}</td>
                            <td class="tbClass">${complexConceptFieldsvar.formId}</td><td class="tbClass">${complexConceptFieldsvar.fieldName}</td>
                            <td class="tdClass"><input type="hidden" name="complexConceptFieldstbl" id="complexConceptFieldstbl" value="${complexConceptFieldsvar.uuid}" />
								<input type="submit" name="EditcomplexConceptFields" value="Edit" /> &nbsp; 
								<input type="submit" name="voidcomplexConceptFields"  value="Void" />&nbsp;




                            </td>
						</tr>
						</form>
						
					</c:forEach>
					</tbody>
				</table>
<div id="dlgData" title="Patient Information"></div>
<input type="button" onclick="opendialogbox()" value="Show div" />



<%@ include file="/WEB-INF/template/footer.jsp"%>