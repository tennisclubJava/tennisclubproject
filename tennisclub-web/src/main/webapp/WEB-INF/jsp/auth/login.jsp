<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:pagetemplate>
    <jsp:attribute name="body">
       <form:form method="post" action="${pageContext.request.contextPath}/auth/login/"
                  modelAttribute="userLogin" cssClass="form-horizontal">

       <div class="form-group ${username_error?'has-error':''}">
         <form:label path="username" cssClass="col-sm-2 control-label">Mail:</form:label>
         <div class="col-sm-10">
           <form:input path="username" cssClass="form-control"/>
           <form:errors path="username" cssClass="help-block"/>
         </div>
       </div>
        <div class="form-group ${password_error?'has-error':''}">
          <form:label path="password" cssClass="col-sm-2 control-label">Password:</form:label>
          <div class="col-sm-10">
            <form:password path="password" cssClass="form-control"/>
            <form:errors path="password" cssClass="help-block"/>
          </div>
        </div>
      <button class="btn btn-primary" type="submit">Login</button>
    </form:form>
    	 
        <table class="table">
        <thead>
        <tr>
 		<th>Available Password & Login </th>
        </tr>
        </thead>
        <tbody>
            <tr>
            	<td>As Simple User : </td>
                <td>Login:  millenium@falcon.com</td>
                <td>Pwd : hanshotsfirst</td>
            </tr>
            <tr>
                <td>As Teacher : </td>
                <td>Login:  love@porgs.com</td>
                <td>Pwd : heismyfather</td>
            </tr>
            <tr>
            	<td>As Admin : </td>
                <td>Login:  admin@admin.com</td>
                <td>Pwd : admin </td>
            </tr>
        </tbody>
    </table>


    </jsp:attribute>
</my:pagetemplate>