<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
<!-- <script >
	function bookTicket() {
	
		if (document.getElementById("buyTicketForm").style.display == "none") {
			document.getElementById("buyTicketForm").style.display = "block";
			/* document.getElementById("ticketForm").reset();  */
			document.getElementById("ticketStatus").innerHTML = "";
			
			
			

		} else {
			document.getElementById("buyTicketForm").style.display = "none"
		}

	}
	</script> -->
</head>
<body>
<div id="header">
		<h1>Boston Football Club</h1>
		<table>
		<tr><td><h5 >Welcome! : ${sessionScope.userName}</h5><img height="100" width="100" src="${user.photoName} }">
		<form action="login.htm" method ="post">
					<input type = "hidden" name ="Signin" value = "logout" />
					<input type="submit" name ="logout" value="Logout"/>
					</form>
		</td></tr>
		
	</table>
</div>
	
	<div id="nav">
	
	</div>	
	<div id="section">
	
	<form action = "ticket">
	
	
	</form>
	
	</div>
	
		

	
		
	
</body>
</html>