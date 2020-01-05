<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
    <title>岗位列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script>
        $(function () {
            //1.获取所有的 删除链接 的a标签,遍历所有的a标签
            $("a[pid]").each(function () {
                var aObj=$(this);
                //2.为每一个a标签绑定一个click事件,$(this)代表遍历的每一个删除连接的a标签对象
                $(this).click(function () {
                    //3.弹出一个确认框
                    if(confirm("您确认要删除吗?")) {
                        //4.发出ajax请求
                        $.get(
                            "${pageContext.request.contextPath}/post/delete.do",
                            {"pid":aObj.attr("pid")},
                            function (responseData) {
                                if (responseData.message!=null){
                                    alert(responseData.message);
                                    aObj.parents("tr").remove();
                                } else{
                                    alert("删除失败！");
                                }
                            }
                        );
                    }
                });
            });
        })
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="/css/images/title_arrow.gif"/> 岗位管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">

        <thead>
        <tr align="center" valign=middle id=TableTitle>
            <th width="150px">岗位名称</th>
            <th width="200px">岗位说明</th>
            <th>相关操作</th>
        </tr>
        </thead>

        <!--显示数据列表-->
        <tbody id="TableData">
        <c:forEach items="${posts}" var="post">
            <tr align="center">
                <td>${post.pname}</td>
                <td>${post.description}</td>
                <td><a href="${pageContext.request.contextPath}/post/edit.do?pid=${post.pid}">修改</a>&nbsp;<a href="#" pid="${post.pid}">删除</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>

    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="${pageContext.request.contextPath}/post/addPage.do"><img src="${pageContext.request.contextPath}/css/images/createNew.png"/></a>
        </div>
    </div>
</div>
</body>
</html>
