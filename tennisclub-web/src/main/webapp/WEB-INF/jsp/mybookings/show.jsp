<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="mybookings.show.title"><fmt:param value="${user.name}"/></fmt:message>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">

	<div class="btn-group" role="group" aria-label="filter">
        <my:a href="/mybookings/show/all/${authenticatedUser.id}" class="btn btn-default ${filter=='all'?'active':''}">All</my:a>
        <my:a href="/mybookings/show/lastweek/${authenticatedUser.id}" class="btn btn-default ${filter=='lastweek'?'active':''}">Last Week</my:a>
        <my:a href="/mybookings/show/lastmonth/${authenticatedUser.id}" class="btn btn-default ${filter=='lastmonth'?'active':''}">Last Month</my:a>
        <my:a href="/mybookings/show/lastyear/${authenticatedUser.id}" class="btn btn-default ${filter=='lastyear'?'active':''}">Last Year</my:a>
    </div>

    <div class="row">
        <table class="table">
	        <thead>
	        <tr>
	            <th>idBooking</th>
	            <th>idCourt</th>
	            <th>user1</th>
	            <th>user2</th>
	            <th>dateOfBooking</th>
	            <th>hourOfBooking</th>
	        </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${booking}" var="b">
	            <tr>
	                <td>${b.idBooking}</td>
	                <td><c:out value="${b.idCourt}"/></td>
	                <td><c:out value="${b.user1.name} ${b.user1.surname}"/></td>
	                <td><c:out value="${b.user2.name} ${b.user1.surname}"/></td>
	                <td><fmt:formatDate value="${b.dateOfBooking}" pattern="yyyy-MM-dd"/></td>
	                <td><c:out value="${b.hourOfBooking}"/></td>
	                <td>
	                    <my:a href="/booking/view/${b.idBooking}" class="btn btn-primary">View</my:a>
	                </td>
	                <td>
	                    <form method="post" action="${pageContext.request.contextPath}/booking/delete/${b.idBooking}">
	                        <button type="submit" class="btn btn-primary">Delete</button>
	                    </form>
	                </td>
	            </tr>
	        </c:forEach>
	        </tbody>
	    </table>
    </div>

</jsp:attribute>
</my:pagetemplate>