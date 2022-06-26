<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
					<a href="/StudentManagementSpringMVC/menu"><h3>Student
							Registration</h3></a>
				</div>
				<div class="col-md-6">
					<p>User: ${userInfo.userid} ${userInfo.username}</p>
					<p>Current Date : ${date}</p>
				</div>
				<div class="col-md-1">
					<input type="button" class="btn-basic" id="lgnout-button"
						value="Log Out"
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
				<a href="/StudentManagementSpringMVC/setupaddclass">Course
					Registration</a> <a href="/StudentManagementSpringMVC/setupaddstudent">Student
					Registration </a> <a
					href="/StudentManagementSpringMVC/setupstudentsearch">Student
					Search </a>
			</div>
			<a href="/StudentManagementSpringMVC/setupusersearch">Users
				Management</a>
		</div>
		<div class="main_contents">
			<div id="sub_content">
				<form:form action="/StudentManagementSpringMVC/updatestudent" method="post" modelAttribute = "sbean">
					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Update</h2>
					<h3 style="color: red; text-align: center;">${error}</h3>
					<h3 style="color: green; text-align: center;">${success}</h3>
					<h3 style="color: green; text-align: center;">${message}</h3>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="name" class="col-md-2 col-form-label">Student
							ID</label>
						<div class="col-md-4">
							<form:input type="text" class="form-control" id="name" readonly="true" path="studentid"/>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="name" class="col-md-2 col-form-label">Name</label>
						<div class="col-md-4">
							<form:input type="text" class="form-control" id="name" path="studentname"/>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="dob" class="col-md-2 col-form-label">DOB</label>
						<div class="col-md-4">
							<form:input type="date" class="form-control" id="dob" path="dob"/>
						</div>
					</div>
					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">Gender</legend>
						<div class="col-md-4">
							<div class="form-check-inline">
								<form:radiobutton class="form-check-input" path="gender"
									id="gridRadios1" value="Male" checked="checked" />
								<label class="form-check-label" for="gridRadios1"> Male
								</label>
							</div>
							<div class="form-check-inline">
								<form:radiobutton class="form-check-input" path="gender"
									id="gridRadios2" value="Female" />
								<label class="form-check-label" for="gridRadios2">Female</label>
							</div>
						</div>
					</fieldset>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="phone" class="col-md-2 col-form-label">Phone</label>
						<div class="col-md-4">
							<form:input type="text" class="form-control" id="phone"
								path="phone" />
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="education" class="col-md-2 col-form-label">Education</label>
						<div class="col-md-4">
							<form:select class="form-select" aria-label="Education"
								id="education" path="education">
								<form:option value="Bachelor of Information Technology" />
								<form:option value="Diploma in IT" />
								<form:option value="Bachelor of Computer Science" />
							</form:select>
						</div>
					</div>
					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">Attend</legend>
						<div class="col-md-6 offset-md-4 mt-4">
							<c:forEach var="course" items="${courseList}">
								<div class="form-check-inline col-md-2">
									<form:checkbox class="form-check-input" path="attendCourses"
										id="gridRadios1" value="${course.classid}" />
									<label class="form-check-label" for="gridRadios1">${course.classname}</label>
								</div>
							</c:forEach>
						</div>
					</fieldset>
					<div class="row mb-4">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<button type="submit" class="btn btn-secondary col-md-2">
								Update</button>
							<button type="button" class="btn btn-danger"
								onclick="location.href = '/StudentManagementSpringMVC/deleteStudent/${data.studentid}'">
								<span>Delete</span>
							</button>
							
						</div>
					</div>
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

