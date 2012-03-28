<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<html:html>
<head>
	<title>
		用户管理
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body style="background-color:#c0efff">
	<%@ include file="/common/header2.jsp"%>

	<html:errors />

	<%@ page import="bbs.*"%>
	<%@ page import="java.util.*"%>
	<%
		Vector userVector = (Vector) session
			.getAttribute(Constants.USER_LIST_KEY);
		User user = null;
		if (userVector != null) {
	%>
	<div align="center">
		<table width="80%" border="1" align="center" style="background-color:#daf5ff">
			<tr align="right">
				<td colspan="4">
					<A href="<%=request.getContextPath()%>/backtoadminindex.do">
						首页&nbsp;
					</A>
					<A href="adminlogoff.do">
						注销登录&nbsp;
					</A>
				</td>
			</tr>
			<tr align="center">
				<td colspan="4">
					用户管理
				</td>
			</tr>
			<tr align="center" bgcolor="#0099CC">
				<td width="25%">
					用户名
				</td>
				<td width="25%">
					级别
				</td>
				<td width="25%">
					编辑
				</td>
				<td width="25%">
					删除
				</td>
			</tr>
			<%for (int i = 0; i < userVector.size(); i++) {
					user = (User) userVector.get(i);
			%>
			<tr align="center">
				<td>
					<%=user.getUsername()%>
				</td>
				<td>
					<%=user.getGrade()%>
				</td>
				<td>
					<a href="usereditjsp.do?username=<%=user.getUsername()%>">
						编辑
					</a>
				</td>
				<td>
					<a href="userdelete.do?username=<%=user.getUsername()%>">
						删除
					</a>
				</td>
			</tr>
			<%}
			%>
			<tr bgcolor="#0099CC">
				<td colspan="4">
					<form method="post" action="searchuser.do">
						请输入您要搜索的用户名：
						<input type="text" name="username" size="15" maxlength="50">
						<input type="submit" name="Submit" value="提交">
					</form>
				</td>
			</tr>
		</table>
	</div>
		<%} else {
		%>
			无用户信息!
		<%}
		%>
		<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
