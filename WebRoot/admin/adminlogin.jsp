<%@ page contentType="text/html;charset=utf-8" language="java"%>

<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basepath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()	+ path + "/"; 
%>
<html:html>
<head>
	<title>
		管理员登录
	</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/JS/validate.js" > </script>
</head>

<link href="<%=request.getContextPath()%>/images/myStyleClass.css" rel="stylesheet" type="text/css">

<body style="background-color:#c0efff">
	<%@ include file="/common/header.jsp"%>

	<html:form action="adminlogin.do" focus="username" onsubmit="return validate_form_login(this)">
		<center>
			<table border="0" cellspacing="2" cellpadding="2" width="30%" style="background-color:#daf5ff">
				<tr>
					<td width="40%" align="right">
						用户名
					</td>
					<td width="60%">
						<html:text property="username" />
					</td>
				</tr>
				<tr>
					<td align="right">
						密码
					</td>
					<td width="60%">
						<html:password property="password" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<html:submit property="submit">                     
		      	登录
		      </html:submit>
					</td>
					<td align="left">
						<html:reset>
							重置
						</html:reset>
						<A href="<%=basepath%>login.jsp"> 用户入口</A>
					</td>
				</tr>
			</table>
		</center>
	</html:form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
