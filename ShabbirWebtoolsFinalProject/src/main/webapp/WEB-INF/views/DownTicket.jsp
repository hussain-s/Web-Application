<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User View</title>
<style>
#header {
	background-color: #99BFFF;
	color: black;
	height: 300px;
	text-align: center;
	padding: 5px;
}

#nav {
	line-height: 30px;
	background-color: #eeeeee;
	height: 700px;
	width: 200px;
	float: left;
	padding: 5px;
}

td {
	text-align: justify;
}

#section {
	width: 350px;
	float: left;
	padding: 10px;
}

#myForm {a
	
}

#footer {
	background-color: black;
	color: white;
	clear: both;
	text-align: center;
	padding: 5px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js">


	
</script>
<script>
window.location.hash="no-back-button";
window.location.hash="Again-No-back-button";//again because google chrome don't insert first hash into history
window.onhashchange=function(){window.location.hash="no-back-button";}
</script>
<script>
var xmlHttp;

xmlHttp = GetXmlHttpObject();
function sendEmail() {

	var userName,ticketId,query;
	userName=$('#userName').val();
	ticketId=$('#ticketId').val();
	
	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}

	query = "action=email&userName=" + userName +"&ticketId=" +ticketId;

	xmlHttp.onreadystatechange = function stateChanged() {
		if (xmlHttp.readyState == 4) {

			var json = JSON.parse(xmlHttp.responseText);
			document.getElementById("seatSuccess").innerHTML = json.successmsg;
		}
	};
	xmlHttp.open("POST", "email.htm", true);
	xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlHttp.send(query);
	return false;
}
 
function GetXmlHttpObject() {
	var xmlHttp = null;
	try {
		// Firefox, Opera 8.0+, Safari
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		// Internet Explorer
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xmlHttp;
	
	
}
</script>

</head>
<body>
	<div id="header">
		<h1>Boston Football Club</h1>


		<form action="login.htm" method ="post">
					<input type = "hidden" name ="Signin" value = "logout" />
					<input type="submit" name ="logout" value="Logout"/>
					</form>

	</div>

	<div id="nav"></div>

	<div id="section">


		<h2 align="center">Payment Completed</h2>
		<span id="seatSuccess" style="color: green"></span>
		
		
			<button onclick="sendEmail()" id="sign" style="height: 30px; width: 200px;">Email Ticket</button>
			<input type="hidden" id="userName" value='${sessionScope.userName}'>
			<input type="hidden" id="ticketId" value='${param.ticketId}'>
	

		
		
	</div>



</body>
</html>