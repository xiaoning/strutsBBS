<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<html:html>
<head>
	<title>
		注册用户
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
	<html:form action="submitregistration.do" onsubmit="return validate_userreg(this)">
		<table width="72%" border="0" align="center" style="background-color:#daf5ff">
		<tr>
			<td colspan="2" class=header> 用户注册 
			</td>
		</tr>
			<tr>
				<td width="21%">
					用户名:
				</td>
				<td width="79%">
					<INPUT class=input2 maxLength=20 name=username>
					&nbsp;*&nbsp;（由英文字母、数字、汉字和下划线&quot;_&quot;组成）
				</td>
			</tr>
			<tr>
				<td>
					密码:
				</td>
				<td>
					<INPUT class=input2 type=password maxLength=16 name=password>
					&nbsp;*&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					确认密码:
				</td>
				<td>
					<INPUT class=input2 type=password maxLength=16 name=password2>
					&nbsp;*&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					性别：
				</td>
				<td>
					<INPUT type=radio CHECKED value=男 name=sex>
					男
					<INPUT type=radio value=女 name=sex>
					女
				</td>
			</tr>
			<tr>
				<td>
					电子邮箱：
				</td>
				<td>
					<INPUT class=input2 maxLength=40 name=email>
					&nbsp;*&nbsp;
				</td>
			</tr>
			<tr>
				<td> 
					QQ： 
				</td>
				<td>
					<INPUT class=input2 maxLength=40 name=icq>
				</td>
			</tr>
			<tr>
				<td>
					签名档：
				</td>
				<td>
					<TEXTAREA class=input2 name=signature rows=10 cols=50>这个人很懒，什么都没留下</TEXTAREA>
				</td>
			</tr>
			<tr>
				<td>
					<html:submit property="submit">
						确认
					</html:submit>
				</td>
				<td>
					<html:reset>重写</html:reset>
				</td>
			</tr>
		</table>
	</html:form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
