<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<spring:url value="/resources/css/test.css" var="testCss" />
<link href="${testCss}" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<title>Course Registration</title>
</head>

<body>
	  
    <div id="testheader">
        <div class="container">
            <div class=row>        
                <div class="col-md-5 ">
            <a href="/StudentManagementSpringMVC/menu"><h3>Student Registration</h3></a>
        </div>  
        <div class="col-md-6">
            <p>User: ${userInfo.userid} ${userInfo.username}</p>
                    <p>Current Date : ${date} </p>
        </div>  
        <div class="col-md-1">
                    <input type="button" class="btn-basic" id="lgnout-button" value="Log Out"
                        onclick="location.href='/StudentManagementSpringMVC/logout'">
                </div>   
			</div>
		</div>

	</div>
	<!-- <div id="testsidebar">Hello World </div> -->
	<div class="container">
		<div class="sidenav">

			<button class="dropdown-btn">
				Class Management <i class="fa fa-caret-down"></i>
			</button>

			<div class="dropdown-container">
				<a href="/StudentManagementSpringMVC/setupaddclass">Course Registration</a> 
				<a href="/StudentManagementSpringMVC/setupaddstudent">Student Registration </a> 
				<a href="/StudentManagementSpringMVC/setupstudentsearch">Student Search </a>
			</div>
			<a href="/StudentManagementSpringMVC/setupusersearch">Users Management</a>
		</div>
		<div class="main_contents">
			<div id="sub_content">
				<div style="color: red;">
					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">${error }</h2>
				</div>
				<div style="color: red;">
					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">${password }</h2>
				</div>
				<div style="color: blue;">
					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">${success }</h2>
				</div>

				<form:form action="/StudentManagementSpringMVC/updateuser" method="post" modelAttribute="bean">

					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">User Update</h2>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<form:label path="userid" class="col-md-2 col-form-label">Id</form:label>
						<div class="col-md-4">
							<form:input type="text" class="form-control" path="userid" readonly="true"/>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<form:label path="username" class="col-md-2 col-form-label">Name</form:label>
						<div class="col-md-4">
							<form:input type="text" class="form-control" path="username"/>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<form:label path="useremail" class="col-md-2 col-form-label">Email</form:label>
						<div class="col-md-4">
							<form:input type="email" class="form-control" id="email" path="useremail"/>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<form:label path="password" class="col-md-2 col-form-label">Password</form:label>
						<div class="col-md-4">
							<form:input type="password" class="form-control" id="name" path="password"/>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<form:label path="conpassword" class="col-md-2 col-form-label">Confirm Password</form:label>
						<div class="col-md-4">
							<form:input type="password" class="form-control" id="confirmPassword" path="conpassword"/>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<form:label path="role" class="col-md-2 col-form-label">User Role</form:label>
						<div class="col-md-4">
							<form:select path="role" class="form-select" aria-label="Education" id="userRole">
								<!-- <form:option value="${ubean.role}"/> -->
								<form:option value="Admin" selected="selected"/>
								<form:option value="User"/>
							</form:select>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-4"></div>

						<div class="col-md-6">


							<button type="submit" class="btn btn-success col-md-2"
								data-bs-toggle="modal" data-bs-target="#exampleModal">Update</button>
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">User
												Update</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">

											<h5 style="color: rgb(127, 209, 131);">Succesfully
												Updated !</h5>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-success col-md-2"
												data-bs-dismiss="modal">Ok</button>

										</div>
									</div>
								</div>
							</div>
							<button type="button" class="btn btn-secondary col-md-2 "
								onclick="location.href = '/StudentManagementSpringMVC/setupusersearch'">Back</button>


						</div>
					</div>
					<!-- added -->
				</form:form>
			</div>
		</div>
	</div>
	<div id="testfooter">
		<span>Copyright &#169; ACE Inspiration 2022</span>
	</div>
	<script>
		/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
		var dropdown = document.getElementsByClassName("dropdown-btn");
		var i;

		for (i = 0; i < dropdown.length; i++) {
			dropdown[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var dropdownContent = this.nextElementSibling;
				if (dropdownContent.style.display === "block") {
					dropdownContent.style.display = "none";
				} else {
					dropdownContent.style.display = "block";
				}
			});
		}
	</script>
</body>

</html>





