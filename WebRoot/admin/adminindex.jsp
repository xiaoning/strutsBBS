<%@ page contentType="text/html;charset=utf-8" language="java"%>

<%@ include file="/common/taglibs.jsp"%>

<html:html>
<head>
	<title>
		管理选项页面
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body style="background-color:#c0efff">
	<%@ include file="/common/header2.jsp"%>
		<table align="center">
			<tr>
				<td><A  href="usermanager.do" >用户管理</A>
				</td>
				<td><A href="forummanager.do">论坛管理</A>
				</td>
				<td><A href= "topicmanager.do">主题管理</A>
				</td>
			</tr>
		</table>
	

	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
