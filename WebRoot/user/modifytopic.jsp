<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://ckfinder.com" prefix="ckfinder" %>

<%
	String path = request.getContextPath();
	String basepath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()	+ path + "/";
	String ckeditorPath=basepath+"ckeditor/";
	String ckfinderPath=basepath+"ckfinder/";
%>
	<%@ page import="java.util.*"%>
	<%@ page import="bbs.*" %>
	<%
		session.setAttribute(Constants.TALK_TYPE_KEY, "modify");
		String con=(String)session.getAttribute(Constants.OLD_CONTEND);
		String tit=(String)session.getAttribute(Constants.OLD_TITLE);
	%>
<html:html>
<head>
	<title>
		发表文章
	</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/JS/validate.js" > </script>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">
<body style="background-color:#c0efff">
	<%@ include file="/common/header.jsp"%>

	<html:errors />
	<html:form action="submitarticle.do" onsubmit="return validate_article(this)">
		<table width="80%" border="0" align="center" style="background-color:#daf5ff">
			<tr align="center">
				<td>
					<A href="javascript:history.go(-1);">
						返回主题
					</A>
				</td>
			</tr>
		</table>
		<table width="80%" border="0" align="center" style="background-color:#daf5ff">
			<tr align="center">
				<td colspan="2" class="header">
					修改文章
				</td>
			</tr>
			<tr>
				<td width="10%" align="right">
					标题：
				</td>
				<td width="90%" >
					<INPUT class=input2  size="100" name=title value=<%=tit %>>
				</td>
			</tr>
			<tr>
				
				<td colspan="2" >
					<textarea class=input2 name=content ><%=con%></textarea>
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
		<input type="hidden" name=type value=modify>
	</html:form>
	<%@ include file="/common/footer.jsp"%>
	<ckfinder:setupCKEditor basePath="/ckfinder/" />
	<ckeditor:replace replace="content" basePath="<%=ckeditorPath%>" />
</body>
</html:html>
