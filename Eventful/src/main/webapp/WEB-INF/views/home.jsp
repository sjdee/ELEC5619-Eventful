<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Eventful</title>
	<%@ include file="include.jsp" %> 
	<meta http-equiv="refresh" content="6; url=/login" />
	<link href="<c:url value="/css/home.css" />" rel="stylesheet">
</head>
<body class="red lighten-2">
	<div class="center" style="margin-top:20vw">
		<h1 class="wobble-hor-bottom">
			<span class="white-text" style="font-weight:200; font-size:90px;"><i class="medium material-icons">all_inclusive</i> eventful</span>
		</h1>	
		<h5><span class="white-text" style="font-weight:200">What will you do today?</span></h5>
	</div>
</body>
</html>
