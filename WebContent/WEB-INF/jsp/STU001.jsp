<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<a href="/StudentManagementSpringMVC/setupaddclass">Course Registration</a> 
				<a href="/StudentManagementSpringMVC/setupaddstudent">Student Registration </a> 
				<a href="/StudentManagementSpringMVC/setupstudentsearch">Student Search </a>
			</div>
			<a href="/StudentManagementSpringMVC/setupusersearch">Users Management</a>
		</div>
		<div class="main_contents">
			<div id="sub_content">
				<form action="RegisterStudentServlet" method="post">
					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student
						Registration</h2>
					<h3 style="color: red; text-align: center;">${error}</h3>
					<h3 style="color: green; text-align: center;">${message}</h3>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="name" class="col-md-2 col-form-label">Name</label>
						<div class="col-md-4">
							<input type="text" class="form-control" id="name"
								value="${data.name}" name="name">
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="dob" class="col-md-2 col-form-label">DOB</label>
						<div class="col-md-4">
							<input type="date" class="form-control" id="dob"
								value="${data.dob}" name="dob">
						</div>
					</div>
					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">Gender</legend>
						<div class="col-md-4">
							<c:choose>
								<c:when test="${empty data.gender}">
									<div class="form-check-inline">
										<input class="form-check-input" type="radio" name="gender"
											id="gridRadios1" value="Male" checked="checked"> <label
											class="form-check-label" for="gridRadios1"> Male </label>
									</div>
									<div class="form-check-inline">
										<input class="form-check-input" type="radio" name="gender"
											id="gridRadios2" value="Female"> <label
											class="form-check-label" for="gridRadios2">Female</label>
									</div>
								</c:when>
								<c:otherwise>
									<c:if test="${data.gender eq \"Male\"}">
										<div class="form-check-inline">
											<input class="form-check-input" type="radio" name="gender"
												id="gridRadios1" value="Male" checked="checked"> <label
												class="form-check-label" for="gridRadios1"> Male </label>
										</div>
										<div class="form-check-inline">
											<input class="form-check-input" type="radio" name="gender"
												id="gridRadios2" value="Female"> <label
												class="form-check-label" for="gridRadios2">Female</label>
										</div>
									</c:if>
									<c:if test="${data.gender eq \"Female\"}">
										<div class="form-check-inline">
											<input class="form-check-input" type="radio" name="gender"
												id="gridRadios1" value="Male"> <label
												class="form-check-label" for="gridRadios1"> Male </label>
										</div>
										<div class="form-check-inline">
											<input class="form-check-input" type="radio" name="gender"
												id="gridRadios2" value="Female" checked="checked"> <label
												class="form-check-label" for="gridRadios2">Female</label>
										</div>
									</c:if>
								</c:otherwise>
							</c:choose>
						</div>
					</fieldset>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="phone" class="col-md-2 col-form-label">Phone</label>
						<div class="col-md-4">
							<input type="text" class="form-control" id="phone"
								<c:if test="${empty data.phone}"><c:out value="value=+95 "/></c:if>
								value="${data.phone}" name="phone">
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="education" class="col-md-2 col-form-label">Education</label>
						<div class="col-md-4">
							<select class="form-select" aria-label="Education" id="education"
								name="education">
								<c:if test="${not empty data.education}">
									<option value="${data.education}">${data.education}</option>
								</c:if>
								<c:if
									test="${data.education != \"Bachelor of Information Technology\"}">
									<option value="Bachelor of Information Technology">Bachelor
										of Information Technology</option>
								</c:if>
								<c:if test="${data.education != \"Diploma in IT\"}">
									<option value="Diploma in IT">Diploma in IT</option>
								</c:if>
								<c:if
									test="${data.education != \"Bachelor of Computer Science\"}">
									<option value="Bachelor of Computer Science">Bachelor
										of Computer Science</option>
								</c:if>
							</select>
						</div>
					</div>
					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">Attend</legend>
						<div class="col-md-6 offset-md-4 mt-4">
							<c:forEach var="course" items="${courseList}">
								<div class="form-check-inline col-md-2">
									<input class="form-check-input" type="checkbox"
										${data.attendCourses.contains(course.classid) ? 'checked' : '' }
										name="attendCourses" id="gridRadios1" value="${course.classid}">
									<label class="form-check-label" for="gridRadios1">${course.classname}</label>
								</div>
							</c:forEach>
						</div>
					</fieldset>
					<div class="row mb-4">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<input type="reset" value="Reset" class="btn btn-danger">
							<button type="submit" class="btn btn-secondary col-md-2">
								Add</button>
							<!-- 							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Student
												Registration</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<h5 style="color: rgb(127, 209, 131);">Registered
												Succesfully !</h5>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-success col-md-2"
												data-bs-dismiss="modal">Ok</button>
										</div>
									</div>
								</div>
							</div> -->
						</div>
					</div>
				</form>
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

