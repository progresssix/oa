<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
    <title>岗位设置</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script>
        $(function () {
            var flag;
            //1.获取input标签,为input标签绑定一个失去焦点事件
            $("input[name='pname']").blur(function () {
                //2.获取文本框中的值
                var pnameVal = $(this).val();

                //3.校验岗位名称不能为空
                if(pnameVal=='') {
                    $("#message").html("<font color='red'>岗位名称不能为空</font>");
                    return;
                }
                //4.发出ajax请求去校验岗位名称
                $.post(
                    "${pageContext.request.contextPath}/post/checkPname.do",
                    {pname: pnameVal},
                    function (responseData) {
                        if (responseData.message == '岗位名称重复') {
                            $("#message").html("<font color='red'>" + responseData.message + "</font>");
                            flag=false;
                        } else {
                            $("#message").html("<font color='green'>" + responseData.message + "</font>");
                            flag=true;
                        }
                    }
                );
            });


            $("#saveUrl").click(function () {
                if($("input[name='pname']").val()=='') {
                    $("#message").html("<font color='red'>岗位名称不能为空</font>");
                    return;
                }

                if(flag){//flag为true代表岗位名称没有重复
                    $("#saveForm").submit();
                }
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
            <img border="0" width="13" height="13" src="/css/images/title_arrow.gif"/> 岗位信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="${pageContext.request.contextPath}/post/save.do" method="post" id="saveForm">
        <div class="ItemBlock_Title1">
        </div>

        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td>岗位名称</td>
                        <td><input type="text" name="pname" class="InputStyle"/> *<span id="message"></span></td>
                    </tr>
                    <tr>
                        <td>岗位说明</td>
                        <td><textarea name="description" class="TextareaStyle"></textarea></td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="#" id="saveUrl"><img src="/css/images/save.png"/></a>
            <a href="javascript:history.go(-1)"><img src="/css/images/goBack.png"/></a>
        </div>
    </form>
</div>
</body>
</html>
