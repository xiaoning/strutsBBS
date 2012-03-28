<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="bbs.*" %>
<%
	String tusername=(String)session.getAttribute(Constants.USERNAME);
	String tsex=(String)session.getAttribute(Constants.USERSEX);
	String temail=(String)session.getAttribute(Constants.USEREMAIL);
	String tqq=(String)session.getAttribute(Constants.USERQQ);
	String tsig=(String)session.getAttribute(Constants.userSig);
	int temptopicid=(Integer)session.getAttribute(Constants.USERTOPICNUM);
	int tid=(Integer)session.getAttribute(Constants.USERID);
 %>
<html:html>
<head>
	<title>
		用户信息
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body style="background-color:#c0efff">
	<%@ include file="/common/header.jsp"%>

	<html:errors />
	<table width="68%" border="0" align="center">
		<tr align="center" style="background-color:#daf5ff">
			<td>
				<A href="javascript:history.go(-1);">
					返回上一页
				</A>
			</td>
		</tr>
	</table>
		<table width="72%" border="0" align="center" style="background-color:#daf5ff">
		<tr>
			<td colspan="2" class=header align="center"> 用户信息 
			</td>
		</tr>
			<tr>
				<td width="21%">
					用户名:
				</td>
				<td width="79%">
					<%=tusername%>
				</td>
			</tr>
			
			<tr>
				<td>
					性别：
				</td>
				<td>
					<%=tsex %>
				</td>
			</tr>
			<tr>
				<td>
					电子邮箱：
				</td>
				<td>
					<%=temail %>
				</td>
			</tr>
			<tr>
				<td> 
					QQ： 
				</td>
				<td>
					<%=tqq %>
				</td>
			</tr>
			<tr>
				<td>
					签名档：
				</td>
				<td>
				<%=tsig %>
				</td>
			</tr>
			<tr>
				<td>发言数：
				</td>
				<td><A href="search.do?type=byauthorid&authorid=<%=tid%>"><%=temptopicid%></A>
				</td>
			</tr>
			<tr>
			<td>
			</td>
			<td>
			<A href="newmessage.do?id=<%=tid%>"> 发消息</A>
			</td>
			</tr>
		</table>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
