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
	int tid=(Integer)session.getAttribute(Constants.USERID);
 %>
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
	<html:form action="submitmodifyuser.do" onsubmit="return validate_userreg(this)">
		<table width="72%" border="0" align="center" style="background-color:#daf5ff">
		<tr>
		<td colspan="2" class="header" align="center">修改用户信息
		</td>
		</tr>
			<tr>
				<td width="21%" align="right">
					用户名:
				</td>
				<td width="79%">
					<%=tusername%>
				</td>
			</tr>
			<tr>
				<td align="right">旧密码:
				</td>
				<td>
					<INPUT class=input2 type=password maxLength=16 name=oldpassword>
				</td>
			</tr>
			<tr>
				<td align="right">新密码:
				</td>
				<td>
					<INPUT class=input2 type=password maxLength=16 name=password>
				</td>
			</tr>
			<tr>
				<td align="right">
					确认密码:
				</td>
				<td>
					<INPUT class=input2 type=password maxLength=16 name=password2>
				</td>
			</tr>
			<tr>
				<td align="right">
					性别：
				</td>
				<td>
					<INPUT type=radio value="男" name=sex>
					男
					<INPUT type=radio value="女" name=sex>
					女
				</td>
			</tr>
			<tr>
				<td align="right">
					电子邮箱：
				</td>
				<td>
					<INPUT class=input2 maxLength=40 name=email value=<%=temail%>>
				</td>
			</tr>
			<tr>
				<td align="right"> 
					QQ： 
				</td>
				<td>
					<INPUT class=input2 maxLength=40 name=icq value=<%=tqq %>>
				</td>
			</tr>
			<tr>
				<td align="right">
					签名档：
				</td>
				<td>
					<TEXTAREA class=input2 name=signature rows=10 cols=50><%=tsig%></TEXTAREA>
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
		<input type="hidden" name=username value=<%=tusername%>>
		<input type="hidden" name=id value=<%=tid%>>
	</html:form>
	<%@ include file="/common/footer.jsp"%>
</body>
</html:html>
