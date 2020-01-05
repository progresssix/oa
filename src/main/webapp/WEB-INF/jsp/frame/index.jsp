<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<html>
<head>
    <title>whhpOA</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="100,*,45" framespacing="0" border="0" frameborder="0">
    <frame src="/page/top.do" name="TopMenu"  scrolling="no" noresize />
    <frameset cols="180,*" id="resize">
        <frame noresize name="menu" src="/page/left.do" scrolling="yes" />
        <frame noresize name="right" src="/page/right.do" scrolling="yes" />
    </frameset>
    <frame noresize name="status_bar" scrolling="no" src="/page/bottom.do" />
</frameset>
<noframes>
    <body>
    </body>
</noframes>
</html>
