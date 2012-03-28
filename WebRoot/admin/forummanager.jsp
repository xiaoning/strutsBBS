<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<head>
	<title>
		论坛列表
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<%@ include file="/common/header2.jsp"%>

<html:errors />
<body style="background-color:#c0efff">
	<%@ page import="bbs.*"%>
	<%@ page import="java.util.*"%>
	<%
		Vector forumVector = (Vector) session
			.getAttribute(Constants.FORUM_LIST_KEY);
		Forum forum = null;
		if (forumVector != null) {
	%>

	<table width="80%" border="1" align="center" style="background-color:#daf5ff">
		<tr align="center" bgcolor="#00CCFF">
			<td width="33%">
				<a href="forumprecreate.do">
					新增论坛
				</a>
			</td>
			<td width="34%">
				<A href="<%=request.getContextPath()%>/backtoadminindex.do">
					首页&nbsp;
				</A>
			</td>
			<td width="33%">
				<A href="adminlogoff.do">
					注销登录&nbsp;
				</A>
			</td>
		</tr>
		<tr align="center">
			<td colspan="3">
				论坛管理
			</td>
		</tr>
		<tr align="center" bgcolor="#00CCFF">
			<td>
				论坛名
			</td>
			<td>
				更改论坛信息
			</td>
			<td>
				删除论坛
			</td>
		</tr>
		<%
			for (int i = 0; i < forumVector.size(); i++) {
				forum = (Forum) forumVector.get(i);
		%>
		<tr align="center">
			<td>
				<%=forum.getForumname()%>
			</td>
			<td>
				<a href="forumedit.do?forumid=<%=forum.getId()%>
						&forumname=<%=forum.getForumname()%>">
					编辑
				</a>
			</td>
			<td>
				<a href="forumdelete.do?forumid=<%=forum.getId()%>">
					删除
				</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>

	<%
		} else {
	%>
	无论坛分类信息!
	<%
		}
	%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
