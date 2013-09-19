<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Amrscomplexobshandler" otherwise="/login.htm"
	redirect="/module/amrscomplexobs/amrsComplexHandler.form" />
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
		$n('#amrscomplexconcepthandlerdt').dataTable( {
			"sDom": 'T<"clear">lfrtip'
		});
		
	});
	
	
</script>
<h1>Amrscomplexconcepthandler </h1>
<form method="POST" id="amrscomplexconcepthandlerform">
<table border="1">
<tr><th><tr><td>Handler Name </td><td><input type="text" id="handlername" name="handlername"> </td></tr>
<tr><td>&nbsp;</td><td><input type="submit" id="amrscomplexconcepthandlerformsubmit"name="amrscomplexconcepthandlerformsubmit" value="Submit"></td></tr></table></form>
<table cellpadding="5" width="100%" id="amrscomplexconcepthandlerdt">
          <thead>
					<tr>
					<th class="tdClass">Num</th><th class="tbClass">Handler Name </th>

<th class="tdClass">Action</th>
</tr></thead>
<tbody>
					<c:forEach var="amrscomplexconcepthandlervar"  items="${listAmrsComplexHandler}" varStatus="encIndex" >
						<form method="POST" name="${amrscomplexconcepthandlervar.uuid}">
						<tr>
						<td class="tdClass">${encIndex.index + 1}</td><td class="tbClass">${amrscomplexconcepthandlervar.handlerName}</td><td class="tdClass"><input type="hidden" name="amrscomplexconcepthandlertbl" id="amrscomplexconcepthandlertbl" value="${amrscomplexconcepthandlervar.uuid}" />
								<input type="submit" name="Editamrscomplexconcepthandler" value="Edit" /> &nbsp; 
								<input type="submit" name="voidamrscomplexconcepthandler"  value="Void" />&nbsp; 
								
							</td>
						</tr>
						</form>
						
					</c:forEach>
					</tbody>
				</table>