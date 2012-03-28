<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="bbs.*" %>
<%
	String tusername=(String)session.getAttribute(Constants.USERNAME);
	int tid=(Integer)session.getAttribute(Constants.USERID);
 %>
<html:html>
<head>
	<title>
		用户信息
	</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/JS/validate.js" > </script>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">

<body style="background-color:#c0efff">
	<%@ include file="/common/header.jsp"%>

	<html:errors />
	<table width="68%" border="0" align="center" style="background-color:#daf5ff">
		<tr align="center">
			<td>
				<A href="javascript:history.go(-1);">
					返回上一页
				</A>
			</td>
		</tr>
	</table>
	<html:form action="sendmessage.do" onsubmit="return validate_article(this)">
		<table width="72%" border="0" align="center" style="background-color:#daf5ff">
		<tr>
		<td colspan="2" class="header"> 新短消息 
		</td>
		</tr>
			<tr>
				<td width="21%">
					接收方:
				</td>
				<td width="79%">
					<%=tusername%>
				</td>
			</tr>
			<tr>
				<td>
				标题:
				</td>
				<td>
				<INPUT class=input2 size="100" name=title>
				</td>
			</tr>
			<tr>
			<td>
			内容
			</td>
			<td>
			<textarea class=input2 name=content cols="70" rows="5" ></textarea>
			</td>
			</tr>
			<tr>
			<td>
			</td>
			<td align="center">
			<html:submit property="submit">发送</html:submit>
			</td>
			</tr>
		</table>
		<input type="hidden" name=toid value=<%=tid%>>
		<input type="hidden" name=toname value=<%=tusername%>>
		</html:form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
