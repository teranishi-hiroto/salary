<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${salary != null}">
                <h2>バイト 詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${salary.job_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>店名</th>
                            <td>
                                <pre><c:out value="${salary.shop_name}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>勤務時間</th>
                            <td>
                                <pre><c:out value="${salary.hour}" />時間</pre>
                            </td>
                        </tr>
                        <tr>
                            <th>時給</th>
                            <td>
                                <pre><c:out value="${salary.hourly_wage}" />円</pre>
                            </td>
                        </tr>
                        <tr>
                            <th>給料</th>
                            <td>
                                <pre><c:out value="${salary.hour * salary.hourly_wage}" /></pre>
                            </td>
                        </tr>
                        <%-- <tr>
                            <th>メモ</th>
                            <td>
                                <pre><c:out value="${salary.content}" /></pre>
                            </td>
                        </tr> --%>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_employee.id == salary.employee.id}">
                    <p><a href="<c:url value="/salary/edit?id=${salary.id}" />">この内容を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/index.html' />">一覧に戻る</a></p>
    </c:param>
</c:import>