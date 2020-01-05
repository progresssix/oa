<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
    <title>部门列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script>
        function deleteClick(did) {
            if(window.confirm("您确认要删除吗?")) {
                //请求删除的url
                window.location.href="${pageContext.request.contextPath}/department/delete.do?did="+did;
            }
        }
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="../style/images/title_arrow.gif"/> 部门管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">

        <thead>
        <tr align="center" valign=middle id=TableTitle>
            <th width="150px">部门名称</th>
            <th width="200px">职能说明</th>
            <th>相关操作</th>
        </tr>
        </thead>

        <!--显示数据列表-->
        <tbody id="TableData">
        <c:forEach items="${departments}" var="department">
            <tr align="center">
                <td>${department.dname}</td>
                <td>${department.description}</td>
                <td><a href="#" onclick="deleteClick('${department.did}')">删除</a>&nbsp;
                    <a href="#">修改</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="${pageContext.request.contextPath}/department/addPage.do"><img src="${pageContext.request.contextPath}/css/images/createNew.png"></a>
        </div>
    </div>
</div>



</body>
</html>
