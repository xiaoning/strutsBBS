<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<html:html>
<head>
	<title>
		消息页面
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body style="background-color:#c0efff">

	<%@ include file="/common/header.jsp"%>
	<%@ page import="bbs.*" %>
	<% String msg=(String)session.getAttribute(Constants.ERROR_MSG); %>
	<center>
	<ht  vc ml:errors />
	<h2><%=msg%></h2>
		<h2>
			<a href="javascript:history.go(-1)">
				返回上一页
			</a>
		</h2>
	</center>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
