<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User View</title>
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

<!--Load the AJAX API-->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	// Load the Visualization API and the corechart package.
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});

	// Set a callback to run when the Google Visualization API is loaded.
	google.charts.setOnLoadCallback(drawChart);

	// Callback that creates and populates a data table,
	// instantiates the pie chart, passes in the data and
	// draws it.
	function drawChart(Boston, NewYork, Seattle, SanDiego) {

		var p = Boston;
		var q = NewYork;
		var r = Seattle;
		var s = SanDiego;

		// Create the data table.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Cities');
		data.addColumn('number', 'price in K$');
		data.addRows([ [ 'Boston', p ], [ 'NewYork', q ], [ 'Seattle', r ],
				[ 'San Diego', s ],

		]);

		// Set chart options
		var options = {
			'title' : 'Revenue generated by playing in different cities',
			'width' : 700,
			'height' : 500
		};

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.BarChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
	}
</script>

<script>
	function addManager() {

		if (document.getElementById("addManagerForm").style.display == "none") {
			document.getElementById("addManagerForm").style.display = "block";
			document.getElementById("managerForm").reset();
			document.getElementById("viewSuccess").innerHTML = "";
			document.getElementById("graphsForm").style.display = "none";

		} else {
			document.getElementById("addManagerForm").style.display = "none"
		}

	}

	function viewGraphs() {

		if (document.getElementById("graphsForm").style.display == "none") {
			document.getElementById("graphsForm").style.display = "block";
			document.getElementById("myGraph").reset();
			document.getElementById("addgraphical").innerHTML = "";
			document.getElementById("addManagerForm").style.display = "none";

		} else {
			document.getElementById("graphsForm").style.display = "none"
		}

	}

	var xmlHttp;

	xmlHttp = GetXmlHttpObject();

	function setManager() {

		var userName, status, query;
		userName = $('#userName').val();
		status = $('#status').val();

		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}

		query = "action=updatemanager&userName=" + userName + "&status="
				+ status;

		xmlHttp.onreadystatechange = function stateChanged() {
			if (xmlHttp.readyState == 4) {

				var json = JSON.parse(xmlHttp.responseText);
				document.getElementById("addSuccess").innerHTML = json.msg;
			}
		};
		xmlHttp.open("POST", "updatemanager.htm", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send(query);
		return false;
	}

	function getGraph() {
		var query;
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}
		query = "action=graph";
		xmlHttp.onreadystatechange = function stateChanged() {
			if (xmlHttp.readyState == 4) {

				var json = JSON.parse(xmlHttp.responseText);
				var boston = json.boston;
				var newyork = json.newYork;
				var seattle = json.seattle;
				var sandiego = json.sanDiego;

				drawChart(boston, newyork, seattle, sandiego);
			}
		};
		xmlHttp.open("POST", "graph.htm", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
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
		<table align="right">
			<tr>
				<td><h5>Welcome Admin! : ${sessionScope.userName}</h5> <img
					height="100" width="100" src="${u.getPhotoName()}">
					<form action="login.htm" method="post">
						<input type="hidden" name="Signin" value="logout" /> <input
							type="submit" name="logout" value="Logout" />
					</form></td>

			</tr>

		</table>
	</div>
	<div id="nav">


		<button onclick="addManager()" id="add"
			style="height: 30px; width: 200px;">Add Manager</button>
		<button onclick="viewGraphs()" id="match"
			style="height: 30px; width: 200px;">Revenue Graphs</button>
		<br>
	</div>
	<div id="section">
		<div id="addManagerForm" style="display: none;">
			<h2>Search Username</h2>
			<span id="viewSuccess" style="color:"blue"></span>
			<form id="managerForm" action="addmanager.htm" method="POST">
				<table id="managertab">
					<tr>
						<td>User Name :</td>
						<td><input type="text" name="userName" pattern ='^[a-z0-9]{3,15}$' title='Username must not have special characters' required /></td>


						<td colspan="2"><input type="submit" value="Search"></td>
					</tr>
				</table>
			</form>

			<c:choose>
				<c:when test='${!empty requestScope.u}'>

					<%-- <form:form id="makeManagerForm" action="updatemanager.htm" method="POST" commandName="user"> --%>
					<%-- 	<table>
							<tr><td>User Name :</td>
					<td><form:input type="text" path="userName" id="userName" /></td></tr>
							<tr><td>First Name: </td><td><form:input type="text" id="firstName" name="firstName" path ="firstName"
								value='${u.getFirstName()}' readonly="true" /></td></tr>
							<tr><td>Last Name: </td><td><form:input type="text" id="lastName" name="lastName" path ="lastName"
								value='${u.getLastName()}' readonly="true" /></td></tr>
							<tr><td> Status: </td><td><form:input type="text" id="status" name="status" path="status"
								value='${u.getStatus()}' readonly="true"/></td></tr>
							<tr><td colspan="2"><input type='submit' name="button" onclick="setManager()" value='send' ></td>
						</tr>
					
				
				
			</table> --%>
					<form id="makeManagerForm" action="updatemanager.htm" method="POST">
						<span id="addSuccess" style="color:"blue"></span>
						<table>
							<tr>
								<td>User Name :</td>
								<td><input type="text" name="uName" id="userName"
									value='${u.getUserName()}' /></td>
							</tr>
							<tr>
								<td>First Name:</td>
								<td><input type="text" id="firstName" name="firstName"
									value='${u.getFirstName()}' readonly /></td>
							</tr>
							<tr>
								<td>Last Name:</td>
								<td><input type="text" id="lastName" name="lastName"
									value='${u.getLastName()}' readonly /></td>
							</tr>
							<%-- <tr>
								<td>Status:</td>
								<td><input type="text" id="status" name="status"
									value='${u.getStatus()}' /></td>
							</tr> --%>
							<tr>
								<td>Status:</td>
								<td><Select id="status" name="status">
								<option	value='${u.getStatus()}'>'${u.getStatus()}'</option>
								<option	value='manager'>manager</option>
								</Select></td>
							</tr>
							<tr>
								<td colspan="2"><input type='button' name="button"
									onclick="setManager()" value='send'></td>
							</tr>



						</table>

					</form>
				</c:when>

			</c:choose>


		</div>
		<div id="graphsForm" style="display: none;">
			<h3>Graphs Page</h3>
			<span id="addgraphical" style="color:"blue"></span>
			<form id="myGraph" action="graph.htm" method="post">
				<input type="button" name="button" onclick="getGraph()"
					value="Show Graph">
				<div id="chart_div"></div>
			</form>
		</div>
	</div>
</body>
</html>