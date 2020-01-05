<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
    <title>岗位设置</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script>
        $(function () {
            var flag;

            $("input[name='pname']").blur(function () {

                var pnameVal = $(this).val();
                var regex = /^\s+$/;
                if (regex.test(pnameVal) || pnameVal.length == 0) {
                    $("#message").html("岗位名称不能为空");
                    $("#message").css("color","red");
                    return;
                }

                $.post(
                    "${pageContext.request.contextPath}/post/checkUpdatePname.do",
                    {"pid":${post.pid},"pname":pnameVal},
                    function (data) {
                        $("#message").html(data.message);
                        if (data.message=="岗位名称已重复"){
                            $("#message").css("color","red");
                            flag=false;
                        }else {
                            $("#message").css("color","green");
                            flag=true;
                        }
                    }
                );
            });

            $("#updateUrl").click(function () {
                var pnameVal=$("input[name='pname']").val();
                var regex=/^\s+$/;
                if (regex.test(pnameVal) || pnameVal.length == 0) {
                    $("#message").html("岗位名称不能为空");
                    $("#message").css("color","red");
                    return;
                }
                if (flag){
                    $.post(
                        "${pageContext.request.contextPath}/post/update.do",
                        {}
                    )
                }
            });
        });
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="/css/images/title_arrow.gif"/> 岗位信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="#" method="post" id="updateForm">
        <div class="ItemBlock_Title1">
            <input type="hidden" name="pid" value="${post.pid}">
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td>岗位名称</td>
                        <td><input type="text" name="pname" class="InputStyle" value="${post.pname}"/> *<span id="dnameMsg"></span></td>
                    </tr>
                    <tr>
                        <td>岗位说明</td>
                        <td><textarea name="description" class="TextareaStyle">${post.description}</textarea></td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="#" id="updateUrl"><img src="/css/images/save.png"/></a>
            <a href="javascript:history.go(-1)"><img src="/css/images/goBack.png"/></a>
        </div>
    </form>
</div>
</body>
</html>