<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Amrscomplexobshandler" otherwise="/login.htm"
	redirect="/module/amrscomplexobs/amrscomplexHandlerFields.form" />
	<%-- <%@ include file="localHeader.jsp"%> --%>
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/scripts/jquery.js" />
<openmrs:htmlInclude file="/scripts/jquery/dataTables/css/dataTables_jui.css"/>
<openmrs:htmlInclude file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js"/>
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/scripts/interface.js"/>
<openmrs:htmlInclude file="/moduleResources/amrscomplexobs/scripts/jquery.form.js"/>

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
	var $n = jQuery.noConflict();
	
	$n(document).ready( function() {
		$n('#amrscomplexHandlerFieldsdt').dataTable( {
			"sDom": 'T<"clear">lfrtip'
		});
		
	});
	
//jesse

    var peron_name = [
        "preferred"
        ,"prefix"
        ,"given_name"
        ,"middle_name"
        ,"family_name_prefix"
        ,"family_name"
        ,"family_name2"
        ,"family_name_suffix"];
    var person_attribute="person_attribute_type_id";



</script>
<h1>Amrscomplex Handler Fields </h1>
<form method="POST" id="amrscomplexHandlerFieldsform">
<table border="1">

    <tr><td>Handlers</td><td><select  id="handlerid" name="handlerid">
        <c:forEach var="handler" items="${listAmrscomplexHandlers}" varStatus="ind">
            <option id="${handler.uuid}" value="${handler.id}">${handler.handlerName}</option>
        </c:forEach>
    </select></td></tr>

    <tr><td>Handlers</td><td><select  id="fiedtypeid" name="fiedtypeid">
        <c:forEach var="fieldType" items="${listFieldTypes}" varStatus="ind">
            <option id="${fieldType.uuid}" value="${fieldType.id}">${fieldType.name}</option>
        </c:forEach>
    </select></td></tr>
<tr><td>Concept Id </td><td><input type="text" id="conceptid" name="conceptid"> </td></tr>
<tr><td>Field Local Name </td><td><input type="text" id="fieldlocalname" name="fieldlocalname"> </td></tr>
<tr><td>Table Name </td><td><input type="text" id="tablename" name="tablename"> </td></tr>
<tr><td>&nbsp;</td><td><input type="submit" id="amrscomplexHandlerFieldsformsubmit"name="amrscomplexHandlerFieldsformsubmit" value="Submit"></td></tr></table></form>
<table cellpadding="5" width="100%" id="amrscomplexHandlerFieldsdt">
          <thead>
					<tr>
					<th class="tdClass">Num</th><th class="tbClass">Handler Id </th>
<th class="tbClass">Fied Type Id </th>
<th class="tbClass">Concept Id </th>
<th class="tbClass">Field Local Name </th>
<th class="tbClass">Table Name </th>

<th class="tdClass">Action</th>
</tr></thead>
<tbody>
					<c:forEach var="amrscomplexHandlerFieldsvar"  items="${listAmrscomplexHandlerFields}" varStatus="encIndex" >
						<form method="POST" name="${amrscomplexHandlerFieldsvar.uuid}">
						<tr>
						<td class="tdClass">${encIndex.index + 1}</td><td class="tbClass">${amrscomplexHandlerFieldsvar.handlerId}</td><td class="tbClass">${amrscomplexHandlerFieldsvar.fiedTypeId}</td><td class="tbClass">${amrscomplexHandlerFieldsvar.conceptId}</td><td class="tbClass">${amrscomplexHandlerFieldsvar.fieldLocalName}</td><td class="tbClass">${amrscomplexHandlerFieldsvar.tableName}</td><td class="tdClass"><input type="hidden" name="amrscomplexHandlerFieldstbl" id="amrscomplexHandlerFieldstbl" value="${amrscomplexHandlerFieldsvar.uuid}" />
								<input type="submit" name="EditamrscomplexHandlerFields" value="Edit" /> &nbsp; 
								<input type="submit" name="voidamrscomplexHandlerFields"  value="Void" />&nbsp; 
								
							</td>
						</tr>
						</form>
						
					</c:forEach>
					</tbody>
				</table>