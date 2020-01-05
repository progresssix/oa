<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
    <title>用户信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script>
        $(function () {

            var flag=true;

            //1.根据属性选择器获取input标签
            $("input[name='username']").blur(function () {
                var usernameVal=$(this).val();

                //2.非空校验
                var regex=/^\s+$/;
                if(regex.test(usernameVal) || usernameVal.length==0) {
                    $("#message").html("用户名不能为空");
                    $("#message").css("color", "red");
                    return;
                }

                //3.ajax请求对用户名做重复的校验
                $.post(
                    "${pageContext.request.contextPath}/user/checkUpdateUserName.do",
                    {uid:${user.uid},username:usernameVal},
                    function (responseData) {
                        $("#message").html(responseData.message);

                        if(responseData.message=="用户名已重复") {
                            $("#message").css("color", "red");
                            flag=false;
                        }else {
                            $("#message").css("color", "green");
                            flag=true;
                        }
                    }
                );

            });

            //提交表单
            $("#saveUrl").click(function () {
                //1.非空校验
                var usernameVal = $("input[name='username']").val();

                var regex=/^\s+$/;
                if(regex.test(usernameVal) || usernameVal.length==0) {
                    $("#message").html("用户名不能为空");
                    $("#message").css("color", "red");
                    return;
                }

                //2.如果用户名不重复,就提交表单
                if(flag) {
                    $("#saveForm").submit();
                }
            });


            //回显岗位
            $("select[name='pids'] option").each(function () {
                var pid=$(this).val();//获取到当前遍历的option的value值(pid)
                <c:forEach items="${userPosts}" var="userPost">
                if("${userPost.postId}"==pid) {
                    $(this).attr("selected","selected");
                }
                </c:forEach>
            })
        })
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="css/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="${pageContext.request.contextPath}/user/update.do?uid=${user.uid}" method="post" id="saveForm">
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
            <img border="0" width="4" height="7" src="css/blue/images/item_point.gif" /> 用户信息 </div>
        </div>
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门</td>
                        <td><select name="departmentId">
                            <option>请选择部门</option>
                            <c:forEach items="${departments}" var="department">
                                <option value="${department.did}"
                                        <c:if test="${user.departmentId==department.did}">selected="selected"</c:if>
                                >${department.dname}</option>
                            </c:forEach>
                        </select></td>
                    <tr><td>登录名</td>
                        <td><input type="text" name="username" class="InputStyle" value="${user.username}"/> *<span id="message"></span>
                            （登录名要唯一）
                        </td>
                    </tr>
                    <tr><td>性别</td>
                        <td><input type="RADIO" <c:if test="${user.sex=='男'}">checked="checked"</c:if> name="sex" value="男" id="male"/><label for="male">男</label>
                            <input type="RADIO" <c:if test="${user.sex=='女'}">checked="checked"</c:if> name="sex" value="女" id="female"/><label for="female">女</label>
                        </td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><input type="email" name="email" class="InputStyle" value="${user.email}"/></td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
            <img border="0" width="4" height="7" src="css/blue/images/item_point.gif" /> 岗位设置 </div>
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">岗位</td>
                        <td>
                            <select  name="pids" multiple="true" size="10" class="SelectStyle">
                                <c:forEach items="${posts}" var="post">
                                    <option value="${post.pid}">${post.pname}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="#" id="saveUrl">
                <img src="/css/images/save.png"/>
            </a>
            <a href="javascript:history.go(-1);"><img src="/css/images/goBack.png"/></a>
        </div>
    </form>
</div>
</body>
</html>
