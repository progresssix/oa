<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
    <title>用户列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script language="javascript" src="/js/privilege.js"></script>
    <script language="JavaScript">
        function go(p) {
            var total =${page.pages};
            if (p < 1) {
                p = 1;
            }else if (p>total){
                p = total;
            }
            location.href="${pageContext.request.contextPath}/user/list.do?currPage="+p;
        }
        $(function () {
            $("a:contains('删除')").click(function () {
                var uid = $(this).attr("uid");
                if (confirm("您确认要删除吗？")){
                    location.href="${pageContext.request.contextPath}/user/delete.do?uid="+uid;
                }
            });
        });
    </script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="css/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
        <tr align=center valign=middle id=TableTitle>
            <td width="100">用户名</td>
            <td width="100">所属部门</td>
            <td>岗位</td>
            <td>相关操作</td>
        </tr>
        </thead>

        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
        <!-- 数据存放的地方 -->
        <c:forEach items="${users}" var="user">
            <tr align="center">
                <td>${user.username}</td>
                <td>${user.department.dname}</td>
                <input type="hidden" name="uid" value="${user.uid}" >
                <td>
                    <c:forEach items="${user.posts}" var="post">
                        ${post.pname}&nbsp;&nbsp;
                    </c:forEach>
                </td>
                <td>
                    <a href="#" uid="${user.uid}">删除</a>&nbsp;
                    <a href="${pageContext.request.contextPath}/user/edit.do?uid=${user.uid}">修改</a>&nbsp;
                    <a href="#">设置权限</a>&nbsp;
                </td>
            </tr>
        </c:forEach>
        <tr align="center">
            <td colspan="4">
            <a href="javascript: go(1)">首页</a>
            <a href="javascript: go(${page.pageNum-1})">上一页</a>
            当前${page.pageNum}页/总${page.pages}页
            <a href="javascript: go(${page.pageNum+1})">下一页</a>
            <a href="javascript: go(${page.pages})">尾页</a>
            </td>
        </tr>
        </tbody>

    </table>
</div>

    <div id="TableTail">
        <div id="TableTail_inside">
            <a href="${pageContext.request.contextPath}/user/addPage.do"><img src="/css/images/createNew.png" /></a>
        </div>
    </div>

    <div class="ItemBlock_Title1" id="userTitle" style="display: none;"><!-- 信息说明 --><div class="ItemBlock_Title1">
        <img border="0" width="4" height="7" src="/css/blue/images/item_point.gif"/>
        <div id="userImage"></div>
    </div>
        <div class="ItemBlock_Title1" id="privilegeTitle" style="display: none;"><div class="ItemBlock_Title1">
            <img border="0" width="4" height="7" src="/css/blue/images/item_point.gif" />选择权限</div>
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder" style="display: none;" id="privilegeContent">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <!--表头-->
                    <thead>
                    <tr align="LEFT" valign="MIDDLE" id="TableTitle">
                        <td width="300px" style="padding-left: 7px;">
                            <!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
                            <input type="checkbox" id="allchecked" disabled="disabled"/>
                            <label for="cbSelectAll">全选</label>
                        </td>
                    </tr>
                    </thead>

                    <!--显示数据列表-->
                    <tbody id="TableData">
                    <tr class="TableDetail1">
                        <!-- 显示权限树 -->
                        <td>
                            <ul id='privilegeTree' class="tree"></ul>
                            <img src="/css/images/loading.gif" id="loading">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <img id="savePrivilege" src="/css/images/save.png"/>
        </div>
    </div>
<img src="../../../css/images/MenuIcon/FUNC20082.gif"/>
<img src="/css/images/MenuIcon/FUNC20082.gif"/>
</body>
</html>
