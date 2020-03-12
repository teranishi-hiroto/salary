<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="job_date">日付</label><br />
<input type="date" name="job_date" value="<fmt:formatDate value='${salary.job_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">店名</label><br />
<input type="text" name="shop_name" value="${salary.shop_name}" />
<br /><br />

<label for="hour">勤務時間</label><br />
<input type="number" name="hour" value="${salary.hour}" />
<br /><br />

<label for="hour">時給</label><br />
<input type="number" name="wage" value="${salary.hourly_wage}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">追加</button>