<%@ include file="/WEB-INF/template/include.jsp"%>
<openmrs:require privilege="Manage Amrscomplexobs" otherwise="/login.htm"
	redirect="/module/amrscomplexobs/amrscomplexobs.form" />
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
		$n('#amrscomplexobsdt').dataTable( {
			"sDom": 'T<"clear">lfrtip'
		});
		
	});
	
	
</script>
<h1>Amrscomplexobs </h1>
<form method="POST" id="amrscomplexobsform">
<table border="1">
<tr><th><tr><td>&nbsp;</td><td><input type="submit" id="amrscomplexobsformsubmit"name="amrscomplexobsformsubmit" value="Submit"></td></tr></table></form>