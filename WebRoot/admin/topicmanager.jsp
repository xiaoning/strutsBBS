<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<head>
	<title>
		主题列表
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<%@ include file="/common/header2.jsp"%>

<html:errors />
<body style="background-color:#c0efff">
	<%@ page import="bbs.*"%>
	<%@ page import="java.util.*"%>
	<%
		Vector topicVector = (Vector) session.getAttribute(Constants.SEARCH_LIST);
		Response topic = null;
	%>
	<table width="80%" border="1" align="center" style="background-color:#daf5ff">
		<tr bgcolor="#00CCFF">
			<td colspan="3" align="right">
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
		<tr align="center">
			<td colspan="3">
				主题管理
			</td>
		</tr>
		<tr align="center" bgcolor="#00CCFF">
			<td width="50%">
				文章标题
			</td>
			<td width="36%">
				作者
			</td>
			<td width="14%">
				删除
			</td>
		</tr>
		<%
			if (topicVector != null) {
				for (int i = 0; i < topicVector.size(); i++) {
					topic = (Response) topicVector.get(i);
					int type=topic.getType();
					String delUrl="";
					if(type==0)
					{
						delUrl="topicdelete.do?type=0&topicid="+topic.getTopicid();
					}
					else
					{
						delUrl="topicdelete.do?type=1&topicid="+topic.getResponseid();
					}
%>
		<tr align="center">
			<td>
				<A href="responselist.do?topicid=<%=topic.getTopicid() %>"><%=topic.getTitle()%></A>
			</td>
			<td>
				<A href="viewuserinfo.do?id=<%=topic.getAuthorid()%>"><%=topic.getAuthor()%></A>
			</td>
			<td>
				<a href="<%=delUrl%>">
					删除
				</a>
			</td>
		</tr>
		<%
				}
			}

		%>
		<tr align="center">
			<td colspan="3">
				<form method="post" action="topicsearch.do">
					<input type="text" name="title" size="15" maxlength="50">
					<input type="submit" name="Submit" value="查询">
					<input type="reset" name="Submit2" value="重置">
				</form>
			</td>
		</tr>
	</table>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>

