<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Bookings">
<jsp:attribute name="body">

    <my:a href="/booking/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New booking
    </my:a>

    <table class="table">
        <thread>
        <tr>
            <th>Number of Booking</th>
            <th>Number of Court</th>
            <th>Player 1</th>
            <th>Player 2</th>
            <th>Booking Date</th>
            <th>Booking Hour</th>
            <th>Lesson</th>
            <th>Tournament</th>
        </tr>
        </thread>
        <tbody>
        <c:forEach items="${bookings}" var="booking">
            <tr>
                <td>${booking.idBooking}</td>
                
                <td><c:out value="${booking.idCourt}"/></td>
                <td><c:out value="${booking.user1.name} ${booking.user1.surname}"/></td>
                <td><c:out value="${booking.user2.name} ${booking.user2.surname}"/></td>
                <td><fmt:formatDate value="${booking.dateOfBooking}" pattern="dd-MM-yyyy"/></td>
                <td><c:out value="${booking.hourOfBooking}"/></td>
                <td><c:out value="${booking.lesson}"/></td>
                <td><c:out value="${booking.tournament}"/></td>
                <td>
                    <my:a href="/booking/view/${booking.idBooking}" class="btn btn-primary">View</my:a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/booking/delete/${booking.idBooking}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>