<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js">
        </script>
<script >

	function registrationFormDisplay() {
	
		if (document.getElementById("registrationForm").style.display == "none") {
			document.getElementById("registrationForm").style.display = "block";
			document.getElementById("myForm").reset();
			document.getElementById("success").innerHTML = "";
			document.getElementById("loginForm").style.display = "none";
			
			
			

		} else {
			document.getElementById("registrationForm").style.display = "none"
		}

	}
	
	function loginFormDisplay(){
		
		if (document.getElementById("loginForm").style.display == "none") {
			document.getElementById("loginForm").style.display = "block";
			document.getElementById("myLogin").reset();
			document.getElementById("log").innerHTML = "";
			document.getElementById("registrationForm").style.display = "none";
			
			
			
		} else {
			document.getElementById("loginForm").style.display = "none"
		}
				
		
	}
	 //AJAX

    var xmlHttp;
	 
    xmlHttp = GetXmlHttpObject();
    
   
	
	function checkUser() {
		var userName,query;
		userName=$('#u').val();
        //alert("hello1");
			
        if (xmlHttp == null)
        {
            alert("Your browser does not support AJAX!");
            return;
        }
        


		query = "u="+userName;
        xmlHttp.onreadystatechange = function stateChanged()
        {
            if (xmlHttp.readyState == 4)
            {
                //alert(xmlHttp.responseText);
                var json = JSON.parse(xmlHttp.responseText);
                if(json.matchMsg!=null){
                document.getElementById("match").innerHTML = json.matchMsg;
                }
                if(json.nomatchMsg!=null){
                	
                	
                	document.getElementById("submit").disabled =false;
                	document.getElementById("check").disabled=false;
                	document.getElementById("u").readOnly=true;
                	document.getElementById("match").innerHTML = json.nomatchMsg;
                }
            }
        };
        xmlHttp.open("POST", "help.htm", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send(query);
        return false;
    }
	
	 function getUser(){
		 var userName,password,status,query1,Signin;
		 userName=$('#uName').val();
		 password=$('#pWord').val();
		 
		 status=$("input[type='radio'][name='status']:checked").val();
		 Signin=$('#Signin').val();
		 
		 
		if (xmlHttp == null)
        {
            alert("Your browser does not support AJAX!");
            return;
        }
		
		query1 = "action=login&userName=" + userName + "&password=" + password + "&status=" + status +"&Signin="+Signin;
		xmlHttp.onreadystatechange = function stateChanged()
        {
            if (xmlHttp.readyState == 4)
            {
                //console.log(xmlHttp.responseText);
                var json = JSON.parse(xmlHttp.responseText);
                      
                document.getElementById("log").innerHTML = json.Errormsg;
            }
        };
        xmlHttp.open("POST", "login.htm", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send(query1);
        return false; 
		
	} 
	
	
	function GetXmlHttpObject()
    {
        var xmlHttp = null;
        try
        {
            // Firefox, Opera 8.0+, Safari
            xmlHttp = new XMLHttpRequest();
        } catch (e)
        {
            // Internet Explorer
            try
            {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e)
            {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }
        return xmlHttp;
    }
</script>

<style>
#header {
	background-color: #99BFFF;
	color: black;
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
	</div>

	<div id="nav">
		<button onclick="registrationFormDisplay()" id="sign" style="height: 30px; width: 100px;">Sign Up</button>
		<br>
		<button onclick="loginFormDisplay()" id="login" style="height: 30px; width: 100px;">Login</button>
		<br>

	</div>
	
	<div id="section">
			
		<div id="registrationForm" style="display: none;">
			<h2>Registration Form</h2>
			<span id="success"></span>
			<form:form id="myForm" action="help.htm" method="POST" commandName="user" enctype="multipart/form-data" >
				<table id="tab">
					<tr>
						<td>First Name :</td>
						<td><form:input type="text" id="first" path="firstName" pattern ='^[a-zA-Z][a-zA-Z0-9]*$' title='Enter only a-z' required="true"/></td>
						<font color="red"><form:errors path="firstName"/></font>
						
					<tr>
						<td>Last Name :</td>
						<td><form:input path="lastName"  type="text"  id="last" pattern='^[a-zA-Z][a-zA-Z0-9]*$' title ='Enter only a-z' required="true"/></td>
						<font color="red"><form:errors path="lastName"/></font>
						
					</tr>
					<tr>
						<td>Age:</td>
						<td><form:input type="text" path="age" id ="age" pattern ='^(0?[1-9]|[1-9][0-9])$' title ='Enter age greater than 0'/>
						<font color="red"><form:errors path="age"/></font>
							
							
					</tr>
					<tr>
						<td>Email :</td>
						<td><form:input path="email" type="email" id="email" title='Enter proper email id' required="true"/></td>
						<font color="red"><form:errors path="email"/></font>
						
					</tr>
					<tr>
						<td>Mobile :</td>
						<td><form:input path="phoneNumber" type="text" id="phoneNumber" pattern="^(?:(?:\+?1\s*(?:[.-]\s*)?)?(?:\(\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\s*\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\s*(?:[.-]\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\s*(?:[.-]\s*)?([0-9]{4})(?:\s*(?:#|x\.?|ext\.?|extension)\s*(\d+))?$" title='Enter valid phone number US format' required="true" /></td>
						<font color="red"><form:errors path="phoneNumber"/></font>
						
					</tr>
					<tr>
						<td>Photo :</td>
						<td><form:input path="photo" type="file" id="photoName" required="true"/></td>
						<font color="red"><form:errors path="photo"/></font>
						
					</tr>
					<tr>
						<td>UserName :</td>
						<td><form:input type="text" path="userName" id="u" name="u" pattern ='^[a-z0-9]{3,15}$' title='Enter only a-z,0-9,_ min 3 char' required="true"/></td>
						<td><input type = "button" name="Check" id="check" onclick="checkUser()" value="CheckAvailablity"></td>
						<td><span id="match"></span></td>
						<font color="red"><form:errors path="userName"/></font>
						
					</tr>
					<tr>
						<td>Password :</td>
						<td><form:input type="password" path="password" pattern ='^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$' title='one number and one alphabet minimum required' required="true"/></td>
						<font color="red"><form:errors path="password"/></font>
						
					</tr>
					<tr>
						<td>Status :</td>
						<td><form:input type="text" path="status" value="user" readonly="true"/></td>
					
						
					</tr>
					
					<tr>
						<td colspan="2"><input type="submit" id="submit" name="submit" value="send" disabled></td>
					</tr>
				</table>
					
			</form:form>
		</div>
		
		<div id="loginForm" style="display: none;">
		
		<h3>Login Page</h3>
		<span id="log" style="color : red"></span>
		
		<form id="myLogin" action ="login.htm" method ="POST">
		<table>
		
			
		<tr><td>UserName : </td><td><input type="text"  id="uName" name="userName" required/></td></tr>
			
		<tr><td>Password : </td><td><input type ="password"  id="pWord" name ='password' required/></td></tr>
		
		
		<tr><td><input type = "radio" name ='status' id="state" value ='user' checked/>User</br>
            <input type = "radio"  name ='status' id="state"  value = 'manager'/>Manager</br>
            <input type = "radio" name ='status' id="state" value ='admin'/>Admin</br></td></tr>
			<tr><td><button name="Signin" id="Signin" onclick="getUser()" value="Signin">Signin</button></td></tr>
		
        </table>
		</form>
		
		</div>
	</div>
		
	<div id="footer">Copyright © BostonFootballClub</div>

</body>
</html>

