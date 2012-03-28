<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<%
	String path = request.getContextPath();
	String basepath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()	+ path + "/";
	session.setAttribute(Constants.SEARCH_LIST, null);
	String ttitle="";
	String type=(String)session.getAttribute(Constants.MESSAGE_SHOWTYPE);
%>
<html:html>
<head>
	<title>
		短消息
	</title>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">
<body style="background-color:#c0efff">
	<%@ include file="/common/header.jsp"%>

	<html:errors />
	
	<%@ page import="bbs.*"%>
	<%@ page import="java.util.*"%>
	<%
		String username = (String) session.getAttribute("username");
		int userid=(Integer)session.getAttribute(Constants.USERIDKEY);
		Vector msgVector = (Vector) session
			.getAttribute(Constants.MESSAGE_LIST);
		Message resp = null;
		if (msgVector != null) {
			String content=null;
			String grade=null;
			String title=null;
			int id;
	%>
	
	<table width="97%" border="0" align="center" style="background-color:#daf5ff">
		<tr>
			<td colspan="2">
				<table width="97%" border="0" align="center">
					<tr>
						<td>
							欢迎你访问论坛：<A href="viewuserinfo.do?id=<%=userid%>">
							<%=username%>
							</A>
						</td>
						<td align="right">
							<A href="<%=request.getContextPath()%>/backtoindex.do">
								首页
							</A>&nbsp;
							<A href="modifyuser.do?id=<%=userid%>">修改个人信息</A>&nbsp;
							<A href="logoff.do">
								注销登录
							</A>&nbsp;
							<A href="showmessage.do?type=send">
								发件箱
							</A>
							<A href="showmessage.do?type=recieve">
								收件箱
							</A>
							<A href="tosearch.do">搜索</A>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="header" align="center" > 短消息
			</td>
		</tr>
		<%
			for (int i = 0; i < msgVector.size(); i++) {
				resp = (Message) msgVector.get(i);
				content = resp.getContent();
				title=resp.getTitle();
				
				String delUrl="deletemessage.do?id="+resp.getId()+"&type="+type;
			  
				
		%>
		<tr>
			<td colspan="2">
				<table width="100%" border="0" bordercolor="#c0efff">
					<tr bgcolor="#009999">
						<td width="22%" class="header2">
							作者：<A href="viewuserinfo.do?id=<%=resp.getFromid()%>"><%=resp.getFromname()%></A>
						</td>
						<td width="78%" class="header2">
							标题：<%=resp.getTitle()%>
							 发送时间:<%=resp.getSubmittime()%>
							 <A href="newmessage.do?id=<%=resp.getFromid()%>">回复</A>
							 <A href="<%=delUrl%>">删除</A>
					  </td>
					</tr>
					<tr  >
						<td colspan="2">
							<%=content%>
					  </td>
				  </tr>
			  </table>
			  
			  
			</td>
		</tr>
		
		<%}
			%>
			
		<%} 
		%>
</table>

	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
