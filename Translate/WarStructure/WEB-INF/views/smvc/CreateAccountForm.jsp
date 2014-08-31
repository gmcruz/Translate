<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<title>Create a new Account</title>
				
		<style type="text/css">@import url("<c:url value="/resources/css/main.css"/>");</style>		
	</head>
	
	<body>

		<div id="mainPageDiv">
			
			<form:form commandName="user" action="CreateAccount" method="post">
			
				<fieldset>
				
					<legend>Create Account</legend>
						<%-- <p>
							<label for="fname">First Name: </label>
							<form:input id="fname" path="fname" cssErrorClass="errorBox" />
						</p>
						<p>
							<label for="lname">Last Name: </label>
							<form:input id="lname" path="lname" cssErrorClass="errorBox" />
						</p> --%>
						<p>
							<label for="username">Email Address: </label>
							<form:input id="username" path="username" cssErrorClass="errorBox" />
						</p>
						<p>
							<label for="password">Password: </label>
							<form:password id="password" path="password" cssErrorClass="errorBox" />
						</p>
						<%-- <p>
							<label for="reenterpassword">Re-enter Password: </label>
							<form:password id="reenterpassword" path="reenterpassword" cssErrorClass="errorBox" />
						</p> --%>
						<p id="buttons">
							<input id="reset" type="reset" tabindex="6">
							<input id="submit" type="submit" tabindex="7" value="Create Account">
						</p>
						
				</fieldset>
			
			</form:form>
	
		</div>
		
	</body>
	
</html>

