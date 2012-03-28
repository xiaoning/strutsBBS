<%@ page contentType="text/html;charset=utf-8" language="java"%>

<%@ include file="/common/taglibs.jsp"%>

<html:html>
<head>
	<title>
		用户登录
	</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/JS/validate.js" > </script>
	
<style type="text/css">

</style>
</head>

<link href="images/myStyleClass.css" rel="stylesheet" type="text/css">


<body style="background-color:#c0efff">
	<%@ include file="/common/header.jsp"%>


	<html:form action="userlogin.do" focus="username" onsubmit="return validate_form_login(this)" >
		<center>
			<table border="0" cellspacing="2" cellpadding="2" width="32%" style="background-color:#daf5ff">
			<tr height=30>
			<td colspan="2" class="header"><p>用户登录</p>
			</td>
			</tr>
				<tr>
					<td width="30%" align="right">
						用户名
						
					</td>
					<td width="70%">
						<html:text property="username" /><A href="registration.do">注册</A>
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
						<html:submit property="submit" >                     
		      				登录
		      			</html:submit>
					</td>
					<td align="left">
						<A href="userlogin.do?username=guest">
							匿名
						</A>
						
						<A href="admin/adminlogin.jsp">管理员入口</A>
					</td>
				</tr>
			</table>
		</center>
	</html:form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
