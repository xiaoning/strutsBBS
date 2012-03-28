<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<html:html>
<head>
	<title>
		论坛主页
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body style="background-color:#c0efff">
	<%@ include file="/common/header2.jsp"%>

	<html:errors />

	<%@ page import="bbs.*"%>
	<%@ page import="java.util.*"%>
	<%
		Vector allForumsVector = (Vector) session
			.getAttribute(Constants.ALLFORUMS_KEY);
		Forum forum = null;
%>

	<form name=form1 method="post" action="edituser.do">
		<table width="80%" border="1" align="center" style="background-color:#daf5ff">
			<tr align="right">
				<td colspan="3">
					<A href="<%=request.getContextPath()%>/backtoadminindex.do">
						首页&nbsp;
					</A>
					<A href="adminlogoff.do">
						注销登录&nbsp;
					</A>
				</td>
			</tr>
			<tr align="center">
				<td colspan="3">
					修改用户权限
				</td>
			</tr>
			<tr align="center">
				<td width="26%">
					用户名：
					<%=request.getParameter("username")%>
					<INPUT type=hidden size=15 name=username value="<%=request.getParameter("username")%>">
				</td>
				<td width="37%">
					级别：
					<select size=1 name="grade">
						<option value="0">
							管理员
						</option>
						<option value="1">
							斑竹
						</option>
						<option value="2">
							普通用户
						</option>
					</select>
				</td>
				<td width="37%">
					所在论坛：
					<select size=1 name="forum">
						<%
							for (int i = 0; i < allForumsVector.size(); i++) {
								forum = (Forum) allForumsVector.get(i);
						%>
						<option value="<%=forum.getId()%>">
							<%=forum.getForumname()%>
						</option>
						<%
							}
						%>
					</select>
				</td>
			</tr>
			<tr align="center">
				<td colspan="3">
					<INPUT class=buttonface type=submit value=" 确 定 ">
					&nbsp;
					<INPUT class=buttonface type=reset value=" 清 除 ">
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>