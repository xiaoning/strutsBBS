<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder" %>

<%
	String path = request.getContextPath();
	String basepath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()	+ path + "/";
	String ckeditorPath=basepath+"ckeditor/";
	String ckfinderPath=basepath+"ckfinder/";
	session.setAttribute(Constants.SEARCH_LIST, null);
	String ttitle="";
	int msgcount=(Integer)session.getAttribute(Constants.MESSAGE_Count);
%>
<html:html>
<head>
	<title>
		帖子列表
	</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/JS/validate.js" > </script>
	
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">
<body style="background-color:#c0efff">
	<%@ include file="/common/header.jsp"%>

	<html:errors />
	
	<%@ page import="bbs.*"%>
	<%@ page import="java.util.*"%>
	<%
		session.setAttribute(Constants.TALK_TYPE_KEY, "response");
		String username = (String) session.getAttribute(Constants.USERNAME_KEY);
		int userid=(Integer)session.getAttribute(Constants.USERIDKEY);
		Vector responseVector = (Vector) session
			.getAttribute(Constants.RESPONSE_LIST_KEY);
		Response resp = null;
		int forumid=(Integer)session.getAttribute(Constants.CUR_FORUMID_KEY);
		String forumname=(String)session.getAttribute(Constants.CUR_FORUMNAME_KEY);
		if (responseVector != null) {
			String content=null;
			String grade=null;
			String title=null;
			String type="topic";
			Response topic=(Response)responseVector.get(0);
			String topictitle=topic.getTitle();
			int id;
	%>
	
	<table width="80%" border="0" align="center">
		<tr>
			<td colspan="2">
				<table width="100%" border="0" align="center" style="background-color:#daf5ff">
					<tr>
						<td>
							<A href="<%=request.getContextPath()%>/backtoindex.do">首页</A>&nbsp;
							>
							<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>"><%=forumname%></A>
						</td>
						<td align="right">
							<A href="showmessage.do?type=recieve">收件箱(<%=msgcount%>)</A>	
							<A href="modifyuser.do?id=<%=userid%>">修改个人信息</A>&nbsp;													
							<A href="tosearch.do">搜索</A>
							<A href="logoff.do">注销登录</A>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr >
						<td colspan="2" class="header" align="left">
										标题：<%=topictitle%>
					  </td>
		</tr>
		<%
			for (int i = 0; i < responseVector.size(); i++) {
				resp = (Response) responseVector.get(i);
				content = resp.getContent();
				title=resp.getTitle();
				if(i==0) ttitle=title;
				String url="";
				if(i==0)
					id=resp.getTopicid();
				else
				 	id=resp.getResponseid();
				if (content == null)
					content = "";
				grade = resp.getGrade();
				if (grade == null)
					grade = "";
				if(i==0)
			  		type="topic";
			  	else type="response";
			  	url="modifytopic.do?modifytype="+type+"&id="+id;
			  	int x=(resp.getAuthorid()+1)%540;
			  	String faceurl=path+"/images/faces/face("+x+").jpg";
			  
				
		%>
		<tr>
			<td colspan="2">
				<table width="100%" border="0" style="background-color:#ffffff">
					
					<tr>
						<td width="15%" class="header2"><A href="viewuserinfo.do?id=<%=resp.getAuthorid()%>"><%=resp.getAuthor()%></A>
						</td>
						<td align="right" class="header2">
						<A href="viewuserinfo.do?id=<%=resp.getAuthorid()%>">查看资料</A>
						<A href="newmessage.do?id=<%=resp.getAuthorid()%>"> 发送消息</A>
						<font size="2" color="#575656" >
						发表时间:<%=resp.getSubmittime()%>编辑时间：<%=resp.getModifytime() %>
						</font>
						<%
							if(userid==resp.getAuthorid()){
							 %>
							<A href=<%=url%>>
							修改
							</A>
							<%} %>
						</td>
					</tr>
					<tr bgcolor="#ffffff" >
						<td width="15%"> 
												
							<img src="<%=faceurl%>" />
							
							
						 </td>
						<td align="left"" valign="middle">
							标题：<%=title%>
							<hr />
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

	<html:form action="submitarticle.do" onsubmit="return validate_article(this)">
		<table width="80%" border="0" align="center" >
			<tr align="center">
				<td colspan="2" class="header">
					回复
				</td>
			</tr>
			<tr>
				<td width="15%" align="left" class="header2"  >
					标题：
				</td>
				<td width="85%" >
					<INPUT class=input2  name=title  size="100" value="RE:<%=ttitle%>">
				</td>
			</tr>
			<tr>
				
				<td colspan="2" >
					<textarea class=input2 name=content></textarea>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<html:submit property="submit">
						发表
					</html:submit>
					<html:reset>                     
						重写
	      			</html:reset>
				</td>
			</tr>
			
		</table>
		<input type="hidden" name=type value=response>
	</html:form>
	<%@ include file="/common/footer.jsp"%>
	<ckfinder:setupCKEditor basePath="/ckfinder/" />
	<ckeditor:replace replace="content" basePath="<%=ckeditorPath%>" />
</body>
</html:html>
