<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/test.css" var="testCss" />
<link href="${testCss}" rel="stylesheet" />
<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p>${error}</p>
          </div>
        </div>
        <form class="login-form" action="/StudentManagementSpringMVC/welcomepage" method="post" name="confirm">
          <input type="email" placeholder="Email" name="email"/>
          <input type="password" placeholder="Password" name="password"/>
          <button>login</button>
        </form>
      </div>
    </div>
</body>

</html>