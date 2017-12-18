<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Add a new court">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/court/create"
               modelAttribute="courtCreate" cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="courtType" cssClass="col-sm-2 control-label">Type</form:label>
            <div class="col-sm-10">
                <form:select path="courtType" cssClass="form-control">
                    <c:forEach items="${type}" var="c">
                        <form:option value="${c.id}">${c.type}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="courtType" cssClass="error"/></p>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create Court</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>