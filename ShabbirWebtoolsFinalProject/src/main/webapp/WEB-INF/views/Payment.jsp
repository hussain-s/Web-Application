<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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


		<h2>Payment Gateway</h2>

		
		<form id="paymentForm" action="payment1.htm" method="POST">
			<table id="payTab" align="center" border="1">
		

				

				<tr>
					<td><input type="radio" name="paymentMethod" value="debitcard"checked/>Debit Card</td>
					<td><input type="radio" name="paymentMethod" value="creditcard"/> Credit Card</td>
					<td><input type="radio" name="paymentMethod" value="internetbanking"/>Internet Banking</td>
				</tr>
				<tr>
					<td colspan="3"><input type="text" name="amount" value='${param.price}' readonly></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" name="Pay" value="Pay"/></td>
				</tr>	



			</table>
			<input type="hidden" name="seatName" value='${param.seatName}'/> 
			<input type="hidden" name="ticketId" value='${param.ticketId}'/>
			<input type="hidden" name="userName" value='${sessionScope.userName}'/>
		</form>
	</div>



</body>
</html>