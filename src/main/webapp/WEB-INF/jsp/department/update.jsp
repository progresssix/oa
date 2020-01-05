<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
    <title>部门设置</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script>
        $(function () {
            $("#updateUrl").click(function () {
                //提交表单
                $("#updateForm").submit();
            });
        })
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="/oa/css/images/title_arrow.gif"/> 部门信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="${pageContext.request.contextPath}/department/update.do?did=${department.did}" method="post" id="updateForm">
        <div class="ItemBlock_Title1">
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td>部门名称</td>
                        <td><input type="text" name="dname" class="InputStyle" value="${department.dname}"/> *<span id="dnameMsg"></span></td>
                    </tr>
                    <tr>
                        <td>职能说明</td>
                        <td><textarea name="description" class="TextareaStyle">${department.description}</textarea></td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="#" id="updateUrl"><img src="/oa/css/images/save.png"/></a>
            <a href="javascript:history.go(-1)"><img src="/oa/css/images/goBack.png"/></a>
        </div>
    </form>
</div>
</body>
</html>