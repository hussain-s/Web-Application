
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>Manager View</title>
<style>
#header {
	background-color: #99BFFF;
	color: black;
	height: 200px;
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js">
	
</script>
<script>
	var seatName, seatStatus, seatType, query;
	/* function addSeat() {

		if (document.getElementById("addSeatForm").style.display == "none") {
			document.getElementById("addSeatForm").style.display = "block";
			document.getElementById("seatForm").reset();
			document.getElementById("seatSuccess").innerHTML = "";
			document.getElementById("addMatchForm").style.display = "none" 

		} else {
			document.getElementById("addSeatForm").style.display = "none"
		}

	} */
	 /* 	function addMatch(){
	
	 if (document.getElementById("addMatchForm").style.display == "none") {
	 document.getElementById("addMatchForm").style.display = "block";
	 document.getElementById("matchForm").reset();
	 document.getElementById("matchSuccess").innerHTML = "";
	 document.getElementById("addSeatForm").style.display = "none"

	 } else {
	 document.getElementById("addMatchForm").style.display = "none"
	 }

	 } */
	 
	 
	var xmlHttp;

	xmlHttp = GetXmlHttpObject();

	function saveSeat() {

		var matchDate, price, venue;
	
		matchDate = $('#matchDate').val();
		price = $('#price').val();
		venue = $('#venue').val();
		seatName = $('#seatName').val();
		seatStatus = $("input[type='radio'][name='seatStatus']:checked").val();
		seatType = $("input[type='radio'][name='seatType']:checked").val();
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
			if((matchDate == null || matchDate=='') || (price == null || price =='') || (seatName == null || seatName=='')) {
				alert("Fields cannot be empty and no special characters allowed");
			}
			
			else{
				
		query = "action=seat&seatName=" + seatName + "&seatStatus="
				+ seatStatus + "&seatType=" + seatType + "&matchDate="
				+ matchDate + "&price=" + price + "&venue=" + venue;

		xmlHttp.onreadystatechange = function stateChanged() {
			if (xmlHttp.readyState == 4) {

				var json = JSON.parse(xmlHttp.responseText);
				document.getElementById("submit").disabled = true;
				document.getElementById("seatName").readOnly = false;
				document.getElementById("seatSuccess").innerHTML = json.successmsg;
			}
		};
		xmlHttp.open("POST", "seat.htm", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(query);
		return false;
	}
	}
	
	
	
	
	function insertMatch() {
		var matchDay,place, query;
		matchDay = $('#matchDay').val();
		place=$('#place').val();
		//alert("hello1");

		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
		
		query = "matchDay=" + matchDay +"&place=" + place;
		xmlHttp.onreadystatechange = function stateChanged() {
			if (xmlHttp.readyState == 4) {
				//alert(xmlHttp.responseText);
				var json = JSON.parse(xmlHttp.responseText);
				
			document.getElementById("matchSuccess").innerHTML = json.addmatchMsg;
				
				
			}
		};
		xmlHttp.open("POST", "match.htm", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(query);
		return false;
	}
	
	function onchangeDate(){
		var matchDate,query;
		matchDate = $('#matchDate').val();
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
		query="matchDate="+matchDate;
		xmlHttp.onreadystatechange = function stateChanged() {
			if (xmlHttp.readyState == 4) {
				//alert(xmlHttp.responseText);
				var json = JSON.parse(xmlHttp.responseText);
				if(json.addVenue != null){
			$('#venue').val(json.addVenue);
			document.getElementById("venue").readOnly = true;
				}
				if(json.noVenue!==null){
				document.getElementId('venueSuccess').innerHTML = json.noVenue;	
				}
				}
		};
		xmlHttp.open("POST", "venue.htm", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(query);
		return false;
	}
	

	function checkSeat() {
		var seatName1,check, query;
		seatName1 = $('#seatName').val();
		check=$('#check').val();
		//alert("hello1");
		

		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
		if(seatName1 == null || seatName1 ==''){
			alert("Seat Name cannot be empty");
			}
		else{
		query = "seatName=" + seatName1 +"&check=" + check;
		xmlHttp.onreadystatechange = function stateChanged() {
			if (xmlHttp.readyState == 4) {
				//alert(xmlHttp.responseText);
				var json = JSON.parse(xmlHttp.responseText);
				if (json.seatmatchMsg != null) {
					document.getElementById("seamatch").innerHTML = json.seatmatchMsg;
					
				}
				if (json.noseatmatchMsg != null) {

					document.getElementById("submit").disabled = false;
					document.getElementById("check").disabled = false;
					document.getElementById("seatName").readOnly = true;
					
					document.getElementById("seamatch").innerHTML = json.noseatmatchMsg;
				}
			}
		};
		xmlHttp.open("POST", "seat.htm", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(query);
		return false;
	}
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
		<table align="right">
			<tr>
				<td><h5>Welcome Manager! : ${sessionScope.userName}</h5> <img
					height="150" width="150" src='${u.getPhotoName()}'></td>
				<td>
					<form action="login.htm" method="post">
						<input type="hidden" name="Signin" value="logout" /> <input
							type="submit" name="logout" value="Logout" />
					</form>
				</td>
			</tr>

		</table>
	</div>
	<div id="nav"></div>
	
	<div id="addSeatForm"">
		<h2>Add Seats</h2>
		<span id="seatSuccess" style="color: green"></span>
		<span id="venueSuccess" style="color: green"></span>
		<c:choose> 
		<c:when test="${!empty requestScope.listOfMatch}"> 
		<form id="seatForm" action="seat.htm" method="POST" >
			<table id="seattab">
				
				<tr>
				
					
					<td>Match Date</td>
					<td><select id="matchDate" name="matchDate" onchange="onchangeDate()">
					<c:forEach var="matchdetails" items='${listOfMatch}'>
					<option value="${matchdetails.matchDate}">"${matchdetails.matchDate}"</option>
					</c:forEach>
					
					</select></td>					
				
				
				</tr>
				<tr>
					<td>Venue</td>
					<td><input type="text" id="venue" name="venue" pattern ='^[a-zA-Z][a-zA-Z0-9]*$' title='Enter valid venue name' required="true"></td>
				</tr>
				
				<tr>
					<td>Price</td>
					<td><input type="text" id="price" name="price" pattern='^[1-9][0-9]*$' title='Enter valid price' required="true"></td>
				</tr>
				
				<tr>
					<td>Seat Name :</td>
					<td><input type="text" id="seatName" name="seatName" pattern='^[A-Z][0-9]*$' title='Start with uppercase followed by number' required="true"></td>
					<td><input type="button" name="check" id="check" onclick="checkSeat()" value="CheckAvailablity"></td>
					
				</tr>
				<tr>
					<td style="color:blue"><span id="seamatch"></span></td>

				</tr>

				<tr>
					<td>Seat Status :</td>
					<td><input type="radio" id="seatStatus" name="seatStatus"
						value="Empty" checked="checked" /> New Seat</td>
					<td><input type="radio" id="seatStatus" name="seatStatus"
						value="Occupied" />New Occupied Seat</td>


				</tr>
				<tr>
					<td>Seat Type:</td>

					<td><input type="radio" id="seatType" name="seatType"
						value="Front" checked="checked" /> FrontSeat</td>
					<td><input type="radio" id="seatType" name="seatType"
						value="Middle" />MiddleSeat</td>
					<td><input type="radio" id="seatType" name="seatType"
						value="Top" />TopSeat</td>



				</tr>
				<tr>
					<td colspan="2"><input type="button" id="submit" name="seatButton"
						onclick="saveSeat()" value="send" disabled="disabled" /></td>
				</tr>

			</table>
		</form>
		 </c:when>
		</c:choose> 
	</div>
	

</body>
</html>