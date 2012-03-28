<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<head>
	<title>
		新增板块
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body style="background-color:#c0efff">
	<%@ include file="/common/header2.jsp"%>

	<html:errors />
	<html:form method="post" action="/forumcreate.do">
		<table width="80%" border="1" align="center" style="background-color:#daf5ff">
			<tr>
				<td align="center" bgcolor="#00CCFF">
					新增论坛
				</td>
			</tr>
			<tr>
				<td align="center">
					论坛名：
					<input type="text" name="forumname" size="20" maxlength="50">
					<input type="submit" name="Submit" value="提交">
				</td>
			</tr>
		</table>

	</html:form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
