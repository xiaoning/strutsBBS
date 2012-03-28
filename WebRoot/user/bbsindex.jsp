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
	<%@ include file="/common/header.jsp"%>

	<html:errors />

	<%@ page import="bbs.*"%>
	<%@ page import="java.util.*"%>
	<%
			Vector forumVector = (Vector) session
				.getAttribute(Constants.FORUM_LIST_KEY);
			Forum forum = null;
			String username = (String) session.getAttribute("username");
			int msgcount=(Integer)session.getAttribute(Constants.MESSAGE_Count);
			int userid=(Integer)session.getAttribute(Constants.USERIDKEY);
			if (forumVector != null) {
				String manager;
	%>
	<table width="80%" border="0" align="center" style="background-color:#daf5ff">
		<tr align="left">
			<td colspan="4">
				<table width="100%" border="0" align="center">
					<tr>
						<td style="color:blue;">
						</td>
						<td align="right">
						   	<A href="showmessage.do?type=recieve">短消息(<%=msgcount%>)</A>							
							<A href="modifyuser.do?id=<%=userid%>">修改个人信息</A>&nbsp;
							<A href="tosearch.do">搜索</A>
							<A href="logoff.do">注销登录</A>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr align="center" bgcolor="#FFFFFF">
			<td colspan="4" class="header">
				 MYHOUSE
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td  colspan="4" align="center">
				<p> 这是一个自由的论坛，您可以在这里畅所欲言</p>
			</td>
		</tr>
		<%for (int i = 0; i < forumVector.size(); i++) {
					forum = (Forum) forumVector.get(i);
					manager = forum.getManager();
					if (manager == null)
						manager = "";
		%>
		
		<tr bgcolor="#0efff" align="center">
			<td colspan="4">
			</td>
		</tr>
		<tr bgcolor="#FFFFFF" align="center">
			<td colspan="4" class="header"><font  color=#FFFFFF size="5" ><%=forum.getForumname()%></font>
			</td>
		</tr>
		<tr bgcolor="#FFFFFF">
			<td class="header2" width="40%">版块</td>
			<td class="header2" width="10%" align="center">主题</td>
			<td class="header2" width="35%" align="left">最后发表</td>
			<td class="header2" width="15%">版主</td>
		</tr>		
		<tr align="left" bgcolor="#FFFFFF">
			<td width="30%" valign="middle">
			<img src="<%=request.getContextPath()%>/images/begin.png" />
			
				<A href="topiclist.do?forumid=<%=forum.getId()%>&forumname=<%=forum.getForumname()%>" target=_self><font size="4" color="#3E3D3D" ><%=forum.getForumname()%></font></A>
			
			</td>
			<td width="10%" valign="middle" align="center">

				<%=forum.getTopicNum()%>
			</td>
			<td width="45%" align="left" valign="middle">
				&nbsp;
				<%if (forum.getTopicNum() != 0) {%>
				<A href="responselist.do?topicid=<%=forum.getLastTopicId()%>"><%=forum.getLastTopicTitle()%></A>
				<font size="2" >By <%=forum.getLastTopicAuthor()%> <%=forum.getLastTopicTime()%> </font>
				
				<%
					}
				%>
			</td>
			<td width="15%" valign="middle">
				<%=manager%>
			</td>
		</tr>
		<%		}
			}
		%>
	</table>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
