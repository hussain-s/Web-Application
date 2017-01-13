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
		<tr><td><h5 >Welcome! : ${sessionScope.userName}</h5><img height="100" width="100" src='${u.getPhotoName()}'>
		<form action="login.htm" method ="post">
					<input type = "hidden" name ="Signin" value = "logout" />
					<input type="submit" name ="logout" value="Logout"/>
					</form>
		</td></tr>
		
	</table>
</div>
	<form action="displayseat.htm" method = "POST">
	<div id="nav">
	
		<input type = "submit" name="sub" value ="Book Seat" id="sign" style="height: 30px; width: 100px;">
		<button name="CheckSeat" value ='${sessionScope.userName}' id="Check" style="height: 30px; width: 100px;">CheckSeat</button>
		<%-- <input type="hidden" name="CheckSeat" value='${sessionScope.userName}'> --%>
		<br>
	
	</div>	
	
	<div id = "section">
	 <c:choose>
    <c:when test="${!empty requestScope.listOfSeat}"> 
	<h2>Book Tickets</h2>

		<%-- 	<form:form id="ticketForm" action="ticket.htm" method="POST" commandName="seat"> --%>
				<table id="tickettab" align="center" border="1" cellpadding="5" cellspacing="5">
				<tr>	
					<th>Seat Name</th>
					<th>Seat Status</th>
					<th>Seat Type</th>
					<th>Match Date</th>
					<th>Price</th>
					<th>Venue</th>
					<th>Select</th>
				</tr>
				
				<c:forEach var="ticket" items="${listOfTicket}" >
					
				<tr>
				<c:forEach var="seat" items="${listOfSeat}">
				
				<c:if test="${ticket.getTicketId()==seat.getTicket()}">
				<td> ${seat.getSeatName()}</td>
				<c:set var="i" value="${seat.getSeatName()}"/>
				<td> ${seat.getSeatStatus()}</td>
				<td>${seat.getSeatType()}</td>
				  
				</c:if>
				</c:forEach>
				<td>${ticket.getDate()}</td>
					<td>${ticket.getPrice()}</td>
					<td>${ticket.getVenue()}</td>
					<td><a href='Payment.htm?price=${ticket.getPrice()}&seatName=${i} &ticketId=${ticket.getTicketId()}'>Select</a></td></tr>
					
				</c:forEach>
					</table>
					
	</c:when>
	<c:when test="${!empty requestScope.userTicketList}"> 
	<table id="viewticket" align="center" border="1" cellpadding="5" cellspacing="5" >
			
			<tr>	<th>Ticket Id</th>
					<th>Seat Name</th>
					<th>Match Date</th>
					<th>Price</th>
					<th>Venue</th>
					<th>Cancel</th>
					
				</tr>
				
				<c:forEach var="ticket" items='${userTicketList}'>
				
				<tr><td>${ticket.getTicketId()}</td>
					<td>${ticket.getSeatName()}</td>	
					<td>${ticket.getDate()}</td>
					<td>${ticket.getPrice()}</td>
					<td>${ticket.getVenue()}</td>
					<td><a href='Cancel.htm?ticketId=${ticket.getTicketId()}'>Cancel</a></td>
				
				</c:forEach>
	
	</table>
	</c:when>
	</c:choose>	
	
		
	</div>	
	</form> 	
		
	
</body>
</html>