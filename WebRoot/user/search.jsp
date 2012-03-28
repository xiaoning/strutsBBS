<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html:html>
<head>
	<title>
		搜索
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
	<table width="80%" border="0" align="center" style="background-color:#daf5ff">
		<tr >
			<td colspan="5" align="right">
				<A href="<%=request.getContextPath()%>/backtoindex.do">
					首页
				</A>
				&nbsp;
				<A href="logoff.do">
					注销登录
				</A>
				&nbsp;
			</td>
		</tr>
		<tr align="center">
			<td colspan="5" class="header">
				主题搜索
			</td>
		</tr>
		<tr align="center" bgcolor="#009999">
		<td width="20%" class="header2">标题</td>
		<td width="20%" class="header2">作者</td>
		<td width="20%" class="header2">位置</td>
		<td width="20%" class="header2">类型</td>
		<td width="20%" class="header2">点击/回复</td>
		</tr>
		<%
			if (topicVector != null) {
				for (int i = 0; i < topicVector.size(); i++) {
					topic = (Response) topicVector.get(i);
					int topicid=topic.getTopicid();
					int authorid=topic.getAuthorid();
					int forumid=topic.getForumid();
					int temptype=topic.getType();
					String type=null;
					if(temptype==1) type="Topic";
					else type="Response";
		%>
		<tr align="center">
			<td>
			<A href="responselist.do?topicid=<%=topicid%>"><%=topic.getTitle()%></A>
			</td>
			<td>
			<A href="viewuserinfo.do?id=<%=authorid%>"><%=topic.getAuthor()%></A>
			</td>
			<td>
				<A href="topiclist.do?forumid=<%=forumid%>" > <%=topic.getForumname()%></A>
			</td>
			<td><%=type%></td>
			<td>
			<%=topic.getViewNum()%>/<%=topic.getResponseNum()%>
			</td>
		</tr>
		<%
				}
			}

		%>
		</table>
		<form method="post" action="search.do">
		<table width="80%" border="0" align="center" style="background-color:#daf5ff">
			<tr align="center">
				
				<td>
					<input type="text" name=key size="15" maxlength="50">
					<input type="submit" name="Submit" value="查询">
					<input type="reset" value="重置">
				</td>
			
			</tr>
			<tr align="center">
			<td>
				<input type=radio CHECKED value=bytitle name=type>标题
				<input type=radio  value=byauthor name=type>作者
				<input type=radio  value=bycontent name=type>全文
			</td>
			</tr>
		</table> 
		</form>
	
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>

