<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    

<c:url var="home" value="/home" />
<c:url var="teachers" value="/teachers" />
<c:url var="classes" value="/classes" />
<c:url var="students" value="/students" />
<c:url var="leaves" value="/leaves" />
<c:url var="signout" value="/signout" />

<c:url var="signoutJs" value="/resources/logout.js" />

    
<nav class="navbar navbar-expand-lg bg-success navbar-dark ">

	<div class="container">
		<a href="${ home }" class="navbar-brand">Leave Management System</a>

		<ul class="navbar-nav">
			
			<sec:authorize access="hasAuthority('Admin')">
				<li class="nav-item">
					<a href="${ teachers }" class="nav-link ${ param.view eq 'teachers' ? 'active' : '' }">
						<i class="bi bi-people"></i> Teachers
					</a>
				</li>
			</sec:authorize>
			
			<sec:authorize access="hasAnyAuthority('Admin', 'Teacher')">
				<li class="nav-item">
					<a href="${ classes }" class="nav-link ${ param.view eq 'classes' ? 'active' : '' }">
						<i class="bi bi-mortarboard"></i> Classes
					</a>
				</li>
				<li class="nav-item">
					<a href="${ students }" class="nav-link ${ param.view eq 'students' ? 'active' : '' }">
						<i class="bi bi-people-fill"></i> Students
					</a>
				</li>
			</sec:authorize>
			
			<sec:authorize access="hasAuthority('Student')">
				<li class="nav-item">
					<a href="${ leaves }" class="nav-link ${ param.view eq 'leaves' ? 'active' : '' }">
						<i class="bi bi-inbox"></i> Leaves
					</a>
				</li>
			</sec:authorize>
			<li class="nav-item">
				<a id="logoutMenu" class="nav-link">
					<i class="bi bi-lock"></i> Sign Out
				</a>
			</li>
		</ul>

	</div>
	
</nav>

<sf:form action="${ signout }" id="logoutForm" method="post" cssClass="d-none"></sf:form>

<script src="${ signoutJs }"></script>
   