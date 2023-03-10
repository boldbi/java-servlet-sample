<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>BoldBI Embed - JAVA Servlet</title>
	<script type="text/javascript" src="ajax.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.boldbi.com/embedded-sdk/v6.1.8/boldbi-embed.js"></script>
</head>
<body>
	
	<div style="height:100%;width:100%;overflow: hidden !important;" id="sample_dashboard"></div>
	<script>
		//Rooturl of the BoldBI. This can be domain name/IP/Localhost along with the '/bi' at the end as below
		var rootUrl = "http://localhost:64503/bi";
		//This is by defaul 'site/site1'. If you have created the new tenant in your onpremise version, it might be vary based on your entries
		var siteIdentifier = "/site/site1";
		//Your Bold BI application environment. (If Cloud, you should use `cloud`, if Enterprise, you should use `enterprise`)
		var environment = "enterprise";
		//Unique Id of the Dashboard
		var dashboardId = "606badda-ffbe-41b8-90c4-b1e4ba367aa3";
		//Authorization endpoint which has encrypt the user context and send back to the Bold BI Server for authorization.
		var authorizationUrl = "BoldBI.do"
   		$(document).ready(function()  {
        	var boldbiEmbedInstance = BoldBI.create({
    		  serverUrl: rootUrl + siteIdentifier,
    	      dashboardId: dashboardId,
    	      embedContainerId: "sample_dashboard",
              embedType: BoldBI.EmbedType.Component,
			  environment: environment == "enterprise" ? BoldBI.Environment.Enterprise : BoldBI.Environment.Cloud,
    	      width:"100%",
    	      height: window.innerHeight + 'px',
    	      expirationTime:100000,
    	      authorizationServer: {
    	          url: authorizationUrl
    	      }
            });
        	boldbiEmbedInstance.loadDashboard();
		});
	</script>
</body>
</html>