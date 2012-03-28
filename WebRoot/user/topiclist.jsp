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
	
%>
<html:html>
<head>
	<title>
		主题列表
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
		session.setAttribute(Constants.TALK_TYPE_KEY, "topic");
		int pageid = ((Integer) session
			.getAttribute(Constants.CUR_PAGEID_KEY)).intValue();
		int pagecount=(Integer)session.getAttribute(Constants.CUR_PAGECOUNT_KEY);
		int minpage=pageid-3;
		int maxpage=pageid+3;
		if(minpage<0) minpage=0;
		if(maxpage>=pagecount-1) maxpage=pagecount-1;
		int forumid = ((Integer) session
			.getAttribute(Constants.CUR_FORUMID_KEY)).intValue();
		String forumname = (String) session
			.getAttribute(Constants.CUR_FORUMNAME_KEY);
		String username = (String) session.getAttribute(Constants.USERNAME_KEY);
		int userid=(Integer)session.getAttribute(Constants.USERIDKEY);
		TopicDisp topicDisp = null;
		Vector topicVector = (Vector) session.getAttribute(Constants.TOPIC_LIST_KEY);
		int msgcount=(Integer)session.getAttribute(Constants.MESSAGE_Count);
	%>
	<table width="80%" border="0" align="center" style="background-color:#daf5ff">
		<tr align="left">
			<td colspan="5">
			<table width="97%" border="0" align="center">
					<tr>
						<td>
							<A href="<%=request.getContextPath()%>/backtoindex.do">首页</A>
						</td>
						<td align="right">	
							<A href="showmessage.do?type=recieve">收件箱(<%=msgcount%>)</A>&nbsp;
							<A href="modifyuser.do?id=<%=userid%>">修改个人信息</A>&nbsp;
							<A href="<%=request.getContextPath()%>/newtopic.do">发帖</A>&nbsp;
							<A href="tosearch.do">搜索</A>&nbsp;
							<A href="logoff.do">退出</A>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr align="center" >
			<td colspan="5" class="header">
				<%=forumname%>
			</td>
		</tr>
		<tr align="right">
			<td align="right" colspan="5" class="header2">
			<%if(pageid!=0){ %>
			<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=0">|&lsaquo;</A>			
			<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=<%=pageid-1%>">&lsaquo;&lsaquo;</A><%} %>
				<%for(int i=minpage;i<=maxpage;i++){%>
					<%if(i==pageid){%>
					<%=i+1%>
					<%continue;} %>
				 	<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=<%=i%>"><%=i+1%></A>
				<%}%>
				<%if(pageid!=pagecount-1){ %>
				<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=<%=pageid+1%>">&rsaquo;&rsaquo;</A>
				<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=<%=pagecount-1%>">&rsaquo;|</A><%} %>
			</td>
		</tr>
		
		<tr align="left">
			<td width="40%" class="header2">
				话题
			</td>
			<td width="12%" class="header2">
				作者
			</td>
			<td width="15%" class="header2">
				发表时间
			</td>
			<td width="12%" class="header2">
				查看/回复
			</td>
			<td width="15%" class="header2">
				最后回复时间
			</td>
		</tr>
		<tr>
		<td colspan="5" >
				<hr/>
			</td>
		</tr>
		<%for (int i = 0; i < topicVector.size(); i++) {
			topicDisp = (TopicDisp) topicVector.get(i);
		%>
		<tr align="left">
			<td>
				<A href="responselist.do?topicid=<%=topicDisp.getTopicid()%>">
					<%=topicDisp.getTitle()%>
				</A>
			</td>
			<td>
				<A href="viewuserinfo.do?id=<%=topicDisp.getAuthorid()%>">
				<%=topicDisp.getAuthor()%>
				</A>
			</td>
			<td>
				<Font size=2><%=topicDisp.getSubmittime()%></Font>
			</td>
			<td>
				<%=topicDisp.getViewNum()%>/<%= topicDisp.getResponseNum()%> 
			</td>
			<td>
				<Font size=2><%=topicDisp.getLastTalk()%></Font>
			</td>
		</tr>
		<tr>
		<td colspan="5" >
				<hr/>
			</td>
		</tr>
		<%}%>
		<tr align="right" >
			<td colspan="5" class="header2">
				<%if(pageid!=0){ %>
			<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=0">|&lsaquo;</A>			
			<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=<%=pageid-1%>">&lsaquo;&lsaquo;</A><%} %>
				<%for(int i=minpage;i<=maxpage;i++){%>
					<%if(i==pageid){%>
					<%=i+1%>
					<%continue;} %>
				 	<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=<%=i%>"><%=i+1%></A>
				<%}%>
				<%if(pageid!=pagecount-1){ %>
				<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=<%=pageid+1%>">&rsaquo;&rsaquo;</A>
				<A href="topiclist.do?forumid=<%=forumid%>&forumname=<%=forumname%>&pageid=<%=pagecount-1%>">&rsaquo;|</A><%} %>

			</td>
		</tr>
	</table>
	<html:form action="submitarticle.do" onsubmit="return validate_article(this)">
		<table width="80%" border="0" align="center" style="background-color:#daf5ff">
			<tr align="center">
				<td colspan="2" class="header">
					发贴
				</td>
			</tr>
			<tr>
				<td width="15%" align="left" class="header2"  >
					标题：
				</td>
				<td width="85%" >
					<INPUT class=input2  name=title  size="100">
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
		<input type="hidden" name=type value=topic>
	</html:form>
	
	<%@ include file="/common/footer.jsp"%>
	<ckfinder:setupCKEditor basePath="/ckfinder/" />
	<ckeditor:replace replace="content" basePath="<%=ckeditorPath%>" />
</body>
</html:html>
