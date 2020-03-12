<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>給料 一覧</h2>
        <table id="salary_list">
            <tbody>
                <tr>
                    <th class="salary_date">日付</th>
                    <th class="shop_name">店名</th>
                    <th class="salary_hour">勤務時間</th>
                    <th class="salary_wage">時給</th>
                    <th class="salary_result">給料</th>
                </tr>
                <c:forEach var="salary" items="${salaries}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="salary_date"><fmt:formatDate value='${salary.job_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="shop_name">${salary.shop_name}</td>
                        <td class="salary_hour">${salary.hour}時間</td>
                        <td class="salary_wage">${salary.hourly_wage}円</td>
                        <td class="salary_result">${salary.hour * salary.hourly_wage}円</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${salaries_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((salaries_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/salary/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/salary/new' />">出勤日の追加</a></p>

    </c:param>
</c:import>