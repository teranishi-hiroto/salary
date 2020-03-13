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
        <h2>月別 一覧</h2>
        <table id="salary_list">
            <tbody>
                <tr>
                    <th class="month">日付</th>
                    <th class="shop_name">給料</th>
                </tr>
                <c:forEach var="salary" items="${salaries}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="month"><c:out value="${month}" /></td>
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
    </c:param>
</c:import>