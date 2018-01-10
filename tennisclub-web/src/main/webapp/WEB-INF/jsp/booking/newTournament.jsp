
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@ page import="java.text.SimpleDateFormat"%>

<my:pagetemplate title="New Tournament">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/booking/create"
               modelAttribute="bookingCreate" cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="idCourt" cssClass="col-sm-2 control-label">Which Court do you want to book ? </form:label>
            <div class="col-sm-10">
                <form:select path="idCourt" cssClass="form-control">
                    <c:forEach items="${courts}" var="c">
                        <form:option value="${c.idCourt}">${c.idCourt} ${c.type}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="idCourt" cssClass="error"/></p>
            </div>
        </div>
        <div class="form-group" hidden="hidden">
            <form:label path="hourOfBooking" cssClass="col-sm-2 control-label"> Hour of your booking ? (Each reservation is for one hour) </form:label>
            <div class="col-sm-10">
                <form:select path="hourOfBooking" cssClass="form-control">
                    <c:forEach items="${hour24}" var="h">
                        <form:option value="${h}">${h}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="hourOfBooking" cssClass="error"/>
            </div>
       </div>

       
       <div class="form-group ${dateOfBooking_error?'has-error':''}">
            <form:label path="dateOfBooking" cssClass="col-sm-2 control-label">Day of the booking ?</form:label>
            <div class="col-sm-10">
                <form:input type ="date" path="dateOfBooking" cssClass="form-control" placeholder="In format DD-MM-YYYY"/>
                <form:errors path="dateOfBooking" cssClass="help-block"/>
            </div>
       </div>

       <div class="form-group" hidden="hidden">
            <form:label path="idUser1" cssClass="col-sm-2 control-label">Teacher</form:label>
            <div class="col-sm-10">
                <form:select path="idUser1" cssClass="form-control">
                     <c:forEach items="${users}" var="u">
                        <c:if test="${(u.admin)}">
                            <form:option value="${u.id}">${u.surname} ${u.name}</form:option>
                        </c:if>
                     </c:forEach>
                </form:select>
                 <p class="help-block"><form:errors path="idUser1" cssClass="error"/></p>
            </div>
        </div>

        <div class="form-group" hidden="hidden">
            <form:label path="idUser2" cssClass="col-sm-2 control-label">Player 2</form:label>
            <div class="col-sm-10">
                <form:select path="idUser2" cssClass="form-control">
                    <c:forEach items="${users}" var="u">
                        <c:if test="${(u.admin)}">
                            <form:option value="${u.id}">${u.surname} ${u.name}</form:option>
                        </c:if>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="idUser2" cssClass="error"/></p>
            </div>
        </div>
        
        <div class="form-group" hidden="hidden">
             <form:checkbox path="tournament" value="tournament" checked="checked"/>Lesson
        </div>
        	    

        <button class="btn btn-primary" type="submit">Create Tournament</button>
    </form:form>
    

</jsp:attribute>
</my:pagetemplate>