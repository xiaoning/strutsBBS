<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<head>
	<title>
		编辑论坛
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<%@ include file="/common/header2.jsp"%>

<html:errors />
<body style="background-color:#c0efff">
	<%@ page import="bbs.*"%>
	<%@ page import="java.util.*"%>
	<%
		Vector managerUserVector = (Vector) session
			.getAttribute(Constants.MANAGER_CANDIDATE_KEY);
		String strForumid = (String) session
			.getAttribute(Constants.EDIT_FORUMID_KEY);
		String forumname = (String) session
			.getAttribute(Constants.EDIT_FORUMNAME_KEY);
		User user = null;
		if (managerUserVector != null) {
	%>
	<form name=form1 method="post" action="forumeditsubmit.do">
		<INPUT type=hidden name=forumid value="<%=strForumid%>">
		<table width="80%" border="1" align="center" style="background-color:#daf5ff" >
			<tr>
				<td align="right">
					<A href="<%=request.getContextPath()%>/backtoadminindex.do">
						首页
					</A>
					&nbsp;
					<A href="adminlogoff.do">
						注销登录
					</A>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="center">
					修改论坛信息
				</td>
			</tr>
			<tr>
				<td align="center">
						论坛名：
						<INPUT size=15 name=forumname value="<%=forumname%>">
						版主：
						<select name=manager size=1>
							<option value=""></option>
							<%
								for (int i = 0; i < managerUserVector.size(); i++) {
									user = (User) managerUserVector.get(i);
							%>
							<option value="<%=user.getUsername()%>">
								<%=user.getUsername()%>
							</option>
							<%
								}
							%>
						</select>
						<%
							}
						%>
						<INPUT class=buttonface type=submit value=" 确 定 ">
						<INPUT class=buttonface type=reset value=" 清 除 ">
				</td>
			</tr>
		</table>
	</form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
