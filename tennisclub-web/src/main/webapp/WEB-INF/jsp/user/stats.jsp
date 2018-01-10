<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:choose>
    <c:when test="${user.id == authenticatedUser.id}">
        <c:set var="pageTitle" value="My profile" scope="page"/>
    </c:when>
    <c:otherwise>
        <c:set var="pageTitle" value="Profile of ${user.name} ${user.surname}" scope="page"/>
    </c:otherwise>
</c:choose>


<my:pagetemplate title="${pageTitle}">
<jsp:attribute name="body">
    <c:set var="end" value="users"/>
    <script>
        function openModal(suffix) {
            var modal = $("#modal_" + suffix);
            if (modal)
                modal.modal('show');
        }

        function closeModal(suffix) {
            var modal = $("#modal_" + suffix);
            if (modal)
                modal.modal('hide');
        }
    </script>

    <table class="table"> 

        <thead>
        <tr>
            <th>Name</th>
            <th>Admin?</th>
            <th>Teacher?</th>
            <th>mail</th>
            <th>phone</th>
            <th>dateOfBirth</th>
        </tr>
        </thead>

        <tbody>
            <td>
                <my:a href="/user/stats/${user.id}"><c:out value="${user.name} "/><c:out value="${user.surname}"/> </my:a>
            </td>
            <td>
                 <c:choose>
                    <c:when test="${user.admin}">
                        <span class="glyphicon glyphicon-ok"> </span>
                    </c:when>
                    <c:otherwise>
                        <span class="glyphicon glyphicon-remove"> </span>
                    </c:otherwise>
                    </c:choose>
            </td>
            <td>
                 <c:choose>
                    <c:when test="${user.teacher}">
                        <span class="glyphicon glyphicon-ok"> </span>
                    </c:when>
                    <c:otherwise>
                        <span class="glyphicon glyphicon-remove"> </span>
                    </c:otherwise>
                    </c:choose>
            </td>
            <td><c:out value="${user.mail}"/></td>
            <td><c:out value="${user.phone}"/></td>
            <td><fmt:formatDate value="${user.dateOfBirth}" pattern="yyyy-MM-dd"/></td>

        </tr>
        </tbody>
    </table>

    <%-- create booking button --%>
    <c:if test="${authenticatedUser.id == user.id}">
        <button class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/booking/new'">
            New Booking
        </button>
   	</c:if>
   	<h1>Number of hours played</h1>
    <table class="table" tableName="Number of hours played"> 

        <thead>
        <tr>
            <th>All</th>
            <th>LastWeek</th>
            <th>LastMonth</th>
            <th>LastYear</th>
        </tr>
        </thead>

        <tbody>
            <td><c:out value="${nbBookingsAll}"/></td>
            <td><c:out value="${nbBookingsLastWeek}"/></td>
            <td><c:out value="${nbBookingsLastMonth}"/></td>
			<td><c:out value="${nbBookingsLastYear}"/></td>
        </tr>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>